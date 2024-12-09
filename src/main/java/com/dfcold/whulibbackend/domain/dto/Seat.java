package com.dfcold.whulibbackend.domain.dto;

import com.dfcold.whulibbackend.domain.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dfcold
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private String id;
    private String seat;
    private Boolean local;
    private SeatStatus status;
    private String roomId;
}
