package com.lateautumncoder.rbauth.service;

import com.lateautumncoder.rbauth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.lateautumncoder.response.Response;

/**
 * Created by 40973 ON 2024-10-20 22:52
 */
public interface  VerificationCodeService {
    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
