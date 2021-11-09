package com.kangfu.auth.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kangfu.auth.form.LoginBody;
import com.kangfu.auth.form.RegisterBody;
import com.kangfu.auth.service.SysLoginService;
import com.kangfu.common.core.domain.R;
import com.kangfu.common.core.utils.JwtUtils;
import com.kangfu.common.core.utils.StringUtils;
import com.kangfu.common.security.auth.AuthUtil;
import com.kangfu.common.security.service.TokenService;
import com.kangfu.common.security.utils.SecurityUtils;
import com.kangfu.system.api.model.LoginUser;

/**
 * token 控制
 * 
 * @author kangfu
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form, @RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("login origin = "+origin);
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    /**
     * 在用户退出时删除缓存信息同时保存用户退出日志
     * @param request
     * @return
     */
    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request, @RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("logout origin = "+origin);
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request, @RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("login refresh = "+origin);
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody, @RequestHeader(value = "origin",required = false)String origin)
    {
        //System.out.println("login register = "+origin);
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
