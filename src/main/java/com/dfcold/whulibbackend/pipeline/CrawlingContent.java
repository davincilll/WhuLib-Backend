package com.dfcold.whulibbackend.pipeline;

import com.dfcold.whulibbackend.dto.LoginCrawlingDto;
import com.dfcold.whulibbackend.dto.RoomCrawlingDto;
import com.dfcold.whulibbackend.entity.LibConfig;

/**
 * @author dfcold
 * 爬取过程中的上下文
 * content是公共的每次客户端进行请求都要完成一次content的构建和持久化（redis）
 * 不同的路由需要自己根据需求取串联Processor进行处理
 */
public class CrawlingContent {
    private LibConfig libConfig;
    private LoginCrawlingDto loginCrawlingDto;
    private RoomCrawlingDto roomCrawlingDto;
}
