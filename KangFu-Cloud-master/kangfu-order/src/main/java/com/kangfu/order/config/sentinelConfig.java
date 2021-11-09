package com.kangfu.order.config;


import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单服务 限流规则
 */
@Configuration
public class sentinelConfig {

    /**
     * 1.若您是通过 Spring Cloud Alibaba 接入的 Sentinel，则无需额外进行配置即可使用 @SentinelResource 注解。
     *
     * 2.若您的应用使用了 Spring AOP（无论是 Spring Boot 还是传统 Spring 应用），
     * 您需要通过配置的方式将 SentinelResourceAspect 注册为一个 Spring Bean。
     *
     * 应用 @SentinelResource 注解，必须开启对应的切面，引入SentinelResourceAspect。
     * @return
     */
    /*@Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }*/




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
        AuthorityRuleManager.loadRules(authorityRuleList);

        // 定义限流规则
        List<FlowRule> folwRules=new ArrayList<>();// 定义限流规则集合

        FlowRule flowRule1=new FlowRule();// 定义限流规则
        flowRule1.setResource("orderlist");// 定义限流资源
        flowRule1.setGrade(RuleConstant.FLOW_GRADE_QPS);// 定义限流规则类型
        flowRule1.setCount(2);// 定义QPS阀值，即每秒最多处理的请求个数
        folwRules.add(flowRule1);// 添加规则到集合

        FlowRule flowRule2=new FlowRule();// 定义限流规则
        flowRule2.setResource("orderquery");// 定义限流资源
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);// 定义限流规则类型
        flowRule2.setCount(2);// 定义QPS阀值，即每秒最多处理的请求个数
        folwRules.add(flowRule2);// 添加规则到集合

        FlowRuleManager.loadRules(folwRules);// 加载规则集合
    }

}
