package com.lateautumncoder.rbauth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.lateautumncoder.exception.BizException;
import com.lateautumncoder.exception.impl.ResponseCodeEnum;
import com.lateautumncoder.rbauth.constant.RedisKeyConstants;
import com.lateautumncoder.rbauth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.lateautumncoder.rbauth.service.VerificationCodeService;
import com.lateautumncoder.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by 40973 ON 2024-10-20 22:52
 */
@Slf4j
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        // 手机号
        String phone = sendVerificationCodeReqVO.getPhone();
        // 构建验证码 redis key
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);
        // 判断是否已发送验证码
        boolean isSent = redisTemplate.hasKey(key);
        if (isSent) {
            throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
        }
        // 生成 6 位随机数字验证码
        String code = RandomUtil.randomNumbers(6);
        // todo: 调用第三方短信发送服务
        log.info("==> 手机号: {}, 已发送验证码：【{}】", phone, code);
        // 存储验证码到 redis, 并设置过期时间为 3 分钟
        redisTemplate.opsForValue().set(key, code, 3, TimeUnit.MINUTES);
        
        return Response.success();
    }
}
