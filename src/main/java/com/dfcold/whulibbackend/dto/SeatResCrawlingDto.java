package com.dfcold.whulibbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author dfcold
 * 座位预约的dto
 */
@Data
public class SeatResCrawlingDto {
    @JsonProperty("SYNCHRONIZER_TOKEN")
    private String synchronizerToken;
    @JsonProperty("SYNCHRONIZER_URI")
    private String synchronizerUri;
    private String date;
    private String seat;
    private String start;
    private String end;
    private final String authid = "-1";
}
