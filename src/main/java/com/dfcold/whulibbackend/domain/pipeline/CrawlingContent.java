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
 * content是公共的，每次客户端进行请求都要完成一次content的构建和持久化
 * 由pipeline组装不同的Processor，由controller调用，组装content，交由pipeline进行处理，最后根据content中的内容向前端反馈
 * TODO:将pipeline封装为一个Service，可以由前端进行调用，也可以在线程池中进行流转调用，实现自动化
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
    private List<ReserveHistory> reserveHistoryList = new ArrayList<>();
    private boolean isReserved = false;
    private boolean isLogin = false;

}
