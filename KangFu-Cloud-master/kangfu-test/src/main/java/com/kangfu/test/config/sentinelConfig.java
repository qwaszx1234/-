package com.kangfu.test.config;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.kangfu.test.sentinel.HeaderOriginParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试服务 限流规则
 */
@Configuration
public class sentinelConfig {



    /**
     * 构造方法执行完毕后执行该方法
     */
    //@PostConstruct
    public void init(){
        // 定义授权规则
        String origin = "gateway";
        // orderquery
        List<AuthorityRule> authorityRuleList=new ArrayList<>();// 定义限流规则集合
        AuthorityRule authorityRule = new AuthorityRule();
        authorityRule.setResource("orderquery");
        authorityRule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        authorityRule.setLimitApp(origin);
        authorityRuleList.add(authorityRule);
        // userlist
        AuthorityRule authorityRule1 = new AuthorityRule();
        authorityRule1.setResource("userlist");
        authorityRule1.setStrategy(RuleConstant.AUTHORITY_WHITE);
        authorityRule1.setLimitApp(origin);
        authorityRuleList.add(authorityRule1);
        AuthorityRuleManager.loadRules(authorityRuleList);
        // 定义限流规则
        List<FlowRule> folwRules=new ArrayList<>();// 定义限流规则集合
        FlowRule flowRule=new FlowRule();// 定义限流规则
        flowRule.setResource("userlist");// 定义限流资源
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);// 定义限流规则类型
        flowRule.setCount(2);// 定义QPS阀值，即每秒最多处理的请求个数
        folwRules.add(flowRule);// 添加规则到集合

        FlowRuleManager.loadRules(folwRules);// 加载规则集合
    }


}
