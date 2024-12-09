package com.dfcold.whulibbackend.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author dfcold
 * 所有可用的时间片段
 */
@Data
public class AvailableTime {
    private String seat;
    private List<String> availablePeriodList;
}
