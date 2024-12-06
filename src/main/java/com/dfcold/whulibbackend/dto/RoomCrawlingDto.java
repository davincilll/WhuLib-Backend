package com.dfcold.whulibbackend.dto;


import com.dfcold.whulibbackend.enums.BuildingType;
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
