package com.dfcold.whulibbackend.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.tomcat.jni.Library;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author dfcold
 */

public enum BuildingType {
    // 信息分馆
    INFORMATION_BRANCH_LIBRARY("1"),
    ENGINEERING_BRANCH_LIBRARY("2"),
    MEDICAL_BRANCH_LIBRARY("3"),
    MAIN_LIBRARY("4"),
    NIGHT_INFORMATION_BRANCH_LIBRARY("7"),
    NIGHT_MAIN_LIBRARY("13");
        private final String code;
    BuildingType(String code){
        this.code = code;
    }
    @JsonValue
    public String getCode(){
        return code;
    }
}

