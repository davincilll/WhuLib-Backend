package com.dfcold.whulibbackend.domain.dto;

import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.handler.JacksonTypeHandler;
import lombok.Data;

import java.util.Objects;

/**
 * @author dfcold
 */
@Data
public class UserInfo {
    private Long id;
    private String username;
    private String libUsername;
    private String libPassword;
    /**
     * 配置的自动运行的配置
     */
    private RunConfig runConfig;

    public RunConfig getRunConfig() {
        if (runConfig == null) {
            runConfig = new RunConfig();
        }
        return runConfig;
    }
}

