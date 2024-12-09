package com.dfcold.whulibbackend.domain.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author dfcold
 */
@Component
public class StringToBuildingTypeConverter implements Converter<String, BuildingType> {
    @Override
    public BuildingType convert(String source) {
        for (BuildingType type : BuildingType.values()) {
            if (type.getCode().equals(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid BuildingType: " + source);
    }
}
