package com.dfcold.whulibbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author dfcold
 */
@Data
public class LoginCrawlingDto {
    @JsonProperty("SYNCHRONIZER_TOKEN")
    private String synchronizerToken;
    @JsonProperty("SYNCHRONIZER_URI")
    private String synchronizerUri="/login";
    private String username;
    private String password;
    private String captchaId;
    private String answer;
    private final String authid = "-1";
}
