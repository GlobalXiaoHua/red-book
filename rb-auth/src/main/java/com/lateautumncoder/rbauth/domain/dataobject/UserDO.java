package com.lateautumncoder.rbauth.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by 40973 ON 2024-10-19 1:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDO {
    private Long id;
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
