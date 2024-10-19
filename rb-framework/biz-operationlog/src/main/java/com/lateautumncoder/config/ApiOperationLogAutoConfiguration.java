package com.lateautumncoder.config;

import com.lateautumncoder.aspect.ApiOperationLogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by 40973 ON 2024-10-18 19:27
 */
@AutoConfiguration
public class ApiOperationLogAutoConfiguration {
    @Bean
    public ApiOperationLogAspect apiOperationLogAspect() {
        return new ApiOperationLogAspect();
    }

}
