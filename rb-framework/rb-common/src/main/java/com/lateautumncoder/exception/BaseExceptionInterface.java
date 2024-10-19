package com.lateautumncoder.exception;

/**
 * Created by 40973 ON 2024-10-18 16:14
 */
public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
