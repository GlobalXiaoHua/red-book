package com.lateautumncoder.aspect;

import java.lang.annotation.*;

/**
 * Created by 40973 ON 2024-10-18 18:02
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface ApiOperationLog {

    /**
     * @return API 功能描述
     */
    String description() default "";
}
