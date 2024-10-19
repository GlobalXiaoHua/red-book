package com.lateautumncoder.rbauth.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by 40973 ON 2024-10-18 23:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String nickName;
    private LocalDateTime createTime;
}
