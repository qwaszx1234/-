package com.kangfu.gateway.service;

import java.io.IOException;
import com.kangfu.common.core.exception.CaptchaException;
import com.kangfu.common.core.web.domain.AjaxResult;

/**
 * 验证码处理
 * 
 * @author kangfu
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     *
     * @return
     * @throws IOException
     * @throws CaptchaException
     */
    public AjaxResult createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     *
     * @param key
     * @param value
     * @throws CaptchaException
     */
    public void checkCapcha(String key, String value) throws CaptchaException;
}
