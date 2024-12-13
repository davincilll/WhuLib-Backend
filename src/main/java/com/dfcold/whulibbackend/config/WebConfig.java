package com.dfcold.whulibbackend.config;

import com.dfcold.whulibbackend.domain.enums.BuildingType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author dfcold
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

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
