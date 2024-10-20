package com.lateautumncoder.rbauth.controller;

import com.lateautumncoder.aspect.ApiOperationLog;
import com.lateautumncoder.rbauth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.lateautumncoder.rbauth.service.VerificationCodeService;
import com.lateautumncoder.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 40973 ON 2024-10-20 23:24
 */
@RestController
@Slf4j
public class VerificationCodeController {
    @Resource
    private VerificationCodeService verificationCodeService;

    @PostMapping("/verification/code/send")
    @ApiOperationLog(description = "发送短信验证码")
    public Response<?> send(@Validated @RequestBody SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        return verificationCodeService.send(sendVerificationCodeReqVO);
    }
}
