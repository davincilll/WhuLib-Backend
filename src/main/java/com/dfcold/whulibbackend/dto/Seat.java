package com.dfcold.whulibbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dfcold
 */
@Data
@AllArgsConstructor
public class Seat {
    private String id;
    private String seat;
    private Boolean local;
    private String roomId;
}
