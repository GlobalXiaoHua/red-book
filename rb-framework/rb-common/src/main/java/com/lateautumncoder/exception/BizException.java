package com.lateautumncoder.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 40973 ON 2024-10-18 16:24
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
