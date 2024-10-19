package com.lateautumncoder.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lateautumncoder.constant.DateFormatEnum;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by 40973 ON 2024-10-18 17:38
 */
public class JsonUtils {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 禁用未知属性反序列化：FAIL_ON_UNKNOWN_PROPERTIES 设置为 false，这意味着在反序列化 JSON 数据时，如果遇到与目标对象不匹配的属性，ObjectMapper 不会抛出异常，而是会忽略这些未知属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 禁用空 Bean 序列化：FAIL_ON_EMPTY_BEANS 设置为 false，表示如果要序列化的对象是一个空的 Bean（没有属性），ObjectMapper 也不会抛出异常
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateFormatEnum.Y_M_D_H_M_S_FORMAT.getFormat())));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateFormatEnum.Y_M_D_H_M_S_FORMAT.getFormat())));

        // 注册 Java 时间模块：JavaTimeModule 是一个模块，用于处理 Java 8 的日期和时间类型（如 LocalDateTime、LocalDate 等）的序列化和反序列化，解决这些类型在 JSON 中的表现问题。
        OBJECT_MAPPER.registerModule(javaTimeModule);
    }

    /**
     * 初始化：统一使用 Spring Boot 个性化配置的 ObjectMapper
     *
     * @param objectMapper
     */
    public static void init(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }

    @SneakyThrows
    public static String toJsonString(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }
}
