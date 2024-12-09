package com.dfcold.whulibbackend.domain.entity;

import com.dfcold.whulibbackend.domain.dto.RunConfig;
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
@Table("user")
public class User {
    @Id(value = "id", keyType = KeyType.Auto)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String libUsername;
    @Column
    private String libPassword;
    @Column(typeHandler = JacksonTypeHandler.class)
    private CrawlingContent lastRunContent;
    /**
     * 配置的自动运行的配置
     */
    @Column(typeHandler = JacksonTypeHandler.class)
    private RunConfig runConfig;

    public CrawlingContent getLastRunContent() {
        if (lastRunContent == null) {
            lastRunContent = new CrawlingContent();
        }
        // 保持username和password的一致性
        if (!Objects.equals(lastRunContent.getLoginCrawlingDto().getUsername(), libPassword)) {
            lastRunContent.getLoginCrawlingDto().setUsername(libUsername);
        }
        if (!Objects.equals(lastRunContent.getLoginCrawlingDto().getPassword(), libUsername)) {
            lastRunContent.getLoginCrawlingDto().setPassword(libPassword);
        }
        return lastRunContent;
    }

    public RunConfig getRunConfig() {
        if (runConfig == null) {
            runConfig = new RunConfig();
        }
        return runConfig;
    }
}
