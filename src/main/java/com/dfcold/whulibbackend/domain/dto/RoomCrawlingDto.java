package com.dfcold.whulibbackend.domain.dto;


import com.dfcold.whulibbackend.domain.enums.BuildingType;
import lombok.Data;

/**
 * @author dfcold
 */
@Data
public class RoomCrawlingDto {
    private BuildingType building;
    private String floor;
    private String onDate;
}
