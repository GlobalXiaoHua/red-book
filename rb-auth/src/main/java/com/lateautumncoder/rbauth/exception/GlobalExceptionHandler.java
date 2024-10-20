package com.lateautumncoder.rbauth.exception;


import com.lateautumncoder.exception.BizException;
import com.lateautumncoder.exception.impl.ResponseCodeEnum;
import com.lateautumncoder.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * Created by 40973 ON 2024-10-20 16:50
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BizException.class})
    public Response<Object> handleBizException(HttpServletRequest request, BizException e) {
        log.warn("request fail: {}, errorCode: {}, errorMessage: {}", request.getRequestURL(), e.getErrorCode(), e.getErrorMessage());
        return Response.fail(e);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        // 参数错误异常码
        String errorCode = ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode();
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer sb = new StringBuffer();
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error -> sb.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(", 当前值: '").append(error.getRejectedValue()).append("'; "));
        });
        String errorMessage = sb.toString();
        log.warn("request error: {}, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);
        return Response.fail(errorCode, errorMessage);
    }

    @ExceptionHandler(value = {Exception.class})
    public Response<Object> handleException(HttpServletRequest request, Exception e) {
        log.error("request error {}, ", request.getRequestURI(), e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }
}
