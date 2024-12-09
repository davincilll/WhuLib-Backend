package com.dfcold.whulibbackend.domain.dto;

import com.dfcold.whulibbackend.domain.enums.BuildingType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author dfcold
 */
@Data
public class Room {
    private String id;
    private String name;
    @JsonIgnore
    private BuildingType building;
}
