package com.dfcold.whulibbackend.domain.dto.req;

import com.dfcold.whulibbackend.domain.dto.RunConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.core.handler.JacksonTypeHandler;
import lombok.Data;

import java.time.LocalTime;

/**
 * @author dfcold
 */
@Data
public class UserUpdateReq {
    private Long id;
    private String libUsername;
    private String libPassword;
    /**
     * 配置的自动运行的配置
     */
    @JsonProperty("runConfig")
    private RunConfig runConfig;
}
