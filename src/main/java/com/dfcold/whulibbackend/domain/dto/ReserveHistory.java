package com.dfcold.whulibbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dfcold
 */
@Data
@AllArgsConstructor
public class ReserveHistory {
    private String location;
    private String status;
    private String time;
}
