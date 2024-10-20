package com.lateautumncoder.rbauth;

import com.alibaba.druid.filter.config.ConfigTools;
import com.lateautumncoder.rbauth.domain.dataobject.UserDO;
import com.lateautumncoder.rbauth.domain.mapper.UserDOMapper;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class RbAuthApplicationTests {

    @Autowired
    private UserDOMapper userDOMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        UserDO userDO = UserDO.builder().username("张三").createTime(LocalDateTime.now()).updateTime(LocalDateTime.now()).build();
        int insert = userDOMapper.insert(userDO);
        System.out.println(insert);
    }

    /**
     * Druid 密码加密
     */
    @SneakyThrows
    @Test
    void testEncodePassword() {
        String password = "123456";
        String[] strings = ConfigTools.genKeyPair(512);
        // 私钥
        log.info("privateKey: {}", strings[0]);
        // 公钥
        log.info("publicKey: {}", strings[1]);
        // 通过私钥加密密码
        String encrypt = ConfigTools.encrypt(strings[0], password);
        log.info("encrypt -> password: {}", encrypt);
    }
    @Test
    void testSetKeyValue() {
        redisTemplate.opsForValue().set("name", "测试redis");
    }

    @Test
    void ThreadPoolTaskExecutorTests() {
        threadPoolTaskExecutor.submit(() -> {
            log.info("异步线程");
        });
    }


}
