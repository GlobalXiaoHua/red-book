package com.lateautumncoder.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import com.lateautumncoder.constant.DateFormatEnum;
import com.lateautumncoder.utils.JsonUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by 40973 ON 2024-10-19 20:01
 */
@AutoConfiguration
public class JacksonAutoConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 禁用未知属性反序列化：FAIL_ON_UNKNOWN_PROPERTIES 设置为 false，这意味着在反序列化 JSON 数据时，如果遇到与目标对象不匹配的属性，ObjectMapper 不会抛出异常，而是会忽略这些未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 禁用空 Bean 序列化：FAIL_ON_EMPTY_BEANS 设置为 false，表示如果要序列化的对象是一个空的 Bean（没有属性），ObjectMapper 也不会抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 设置凡是为 null 的字段，返参中均不返回，请根据项目组约定是否开启
        // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        // 支持 LocalDateTime、LocalDate、LocalTime
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateFormatEnum.Y_M_D_H_M_S_FORMAT.getFormat())));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateFormatEnum.Y_M_D_H_M_S_FORMAT.getFormat())));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateFormatEnum.DATE_FORMAT_Y_M_D.getFormat())));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateFormatEnum.DATE_FORMAT_Y_M_D.getFormat())));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateFormatEnum.DATE_FORMAT_H_M_S.getFormat())));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateFormatEnum.DATE_FORMAT_H_M_S.getFormat())));
        javaTimeModule.addSerializer(YearMonth.class, new YearMonthSerializer(DateTimeFormatter.ofPattern(DateFormatEnum.DATE_FORMAT_Y_M.getFormat())));
        javaTimeModule.addDeserializer(YearMonth.class, new YearMonthDeserializer(DateTimeFormatter.ofPattern(DateFormatEnum.DATE_FORMAT_Y_M.getFormat())));
        objectMapper.registerModule(javaTimeModule);
        JsonUtils.init(objectMapper);
        return objectMapper;
    }
}
