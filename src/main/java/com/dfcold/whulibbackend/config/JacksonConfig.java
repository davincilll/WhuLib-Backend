package com.dfcold.whulibbackend.config;

import com.dfcold.whulibbackend.domain.enums.BuildingType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author dfcold
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    /**
     * 自定义的类型转换器
     * @return Converter
     * ！！！注意这里不能转为lambda表达式，会表错
     */
    @Bean
    public Converter<String, BuildingType> stringToBuildingTypeConverter(){
        return new Converter<String, BuildingType>() {
            @Override
            public BuildingType convert(String source) {
                for (BuildingType type : BuildingType.values()) {
                    if (type.getCode().equals(source)) {
                        return type;
                    }
                }
                throw new IllegalArgumentException("Invalid BuildingType: " + source);
            }
        };
    }
}
