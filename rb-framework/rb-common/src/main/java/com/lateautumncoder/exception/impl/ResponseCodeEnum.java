package com.lateautumncoder.exception.impl;

import com.lateautumncoder.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by 40973 ON 2024-10-20 16:46
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    SYSTEM_ERROR("AUTH-10000", "出错啦，后台小哥正在努力修复中..."), PARAM_NOT_VALID("AUTH-10001", "参数错误");

    private final String errorCode;
    private final String errorMessage;
}
