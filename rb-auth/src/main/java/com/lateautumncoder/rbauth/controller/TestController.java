package com.lateautumncoder.rbauth.controller;

import com.lateautumncoder.aspect.ApiOperationLog;
import com.lateautumncoder.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by 40973 ON 2024-10-18 17:06
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperationLog(description = "测试接口1")
    public Response<String> test() {
        return Response.success("Hello world");
    }

    @GetMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response<User> test2() {
        User user = User.builder().nickName("张三").createTime(LocalDateTime.now()).build();
        return Response.success(user);
    }

    @PostMapping("/test3")
    @ApiOperationLog(description = "测试接口3")
    public Response<User> test3(@RequestBody User user) {
        return Response.success(user);
    }
}
