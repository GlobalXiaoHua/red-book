package com.lateautumncoder.constant;

/**
 * Created by 40973 ON 2024-10-18 23:37
 */
public enum DateFormatEnum {

    Y_M_D_H_M_S_FORMAT("yyyy-MM-dd HH:mm:ss"), DATE_FORMAT_Y_M_D("yyyy-MM-dd"), DATE_FORMAT_H_M_S("HH:mm:ss"), DATE_FORMAT_Y_M("yyyy-MM");

    private final String format;

    private DateFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}