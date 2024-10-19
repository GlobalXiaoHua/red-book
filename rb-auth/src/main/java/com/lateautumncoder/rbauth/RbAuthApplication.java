package com.lateautumncoder.rbauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.lateautumncoder.rbauth.domain.mapper")
public class RbAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbAuthApplication.class, args);
    }

}
