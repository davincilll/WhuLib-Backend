package com.dfcold.whulibbackend.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.tomcat.jni.Library;

/**
 * @author dfcold
 */
public enum BuildingType {
    // 信息分馆
    INFORMATION_BRANCH_LIBRARY(1),
    ENGINEERING_BRANCH_LIBRARY(2),
    MEDICAL_BRANCH_LIBRARY(3),
    MAIN_LIBRARY(4),
    NIGHT_INFORMATION_BRANCH_LIBRARY(7),
    NIGHT_MAIN_LIBRARY(13);
        private final int code;
    BuildingType(int code){
        this.code = code;
    }
    @JsonValue
    public int getCode(){
        return code;
    }
}
