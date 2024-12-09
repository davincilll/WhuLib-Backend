package com.dfcold.whulibbackend.domain.pipeline;

import com.dfcold.whulibbackend.domain.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dfcold
 * 爬取过程中的上下文
 * content是公共的每次客户端进行请求都要完成一次content的构建和持久化
 * 不同的路由需要自己根据需求取串联Processor进行处理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrawlingContent {
    private LoginCrawlingDto loginCrawlingDto = new LoginCrawlingDto();
    private RoomCrawlingDto roomCrawlingDto= new RoomCrawlingDto();
    private SeatCrawlingDto seatCrawlingDto = new SeatCrawlingDto();
    private SeatResCrawlingDto seatResCrawlingDto = new SeatResCrawlingDto();
    private AvailableTimeCrawlingDto availableTimeCrawlingDto = new AvailableTimeCrawlingDto();
    private String cookies = "";

    private List<Room> rooms = new ArrayList<>();
    private List<Seat> seats = new ArrayList<>();
    private AvailableTime availableTime = new AvailableTime();
    private boolean isReserved = false;
    private boolean isLogin = false;

}
