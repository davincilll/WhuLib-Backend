package com.dfcold.whulibbackend.controller;

import com.dfcold.whulibbackend.common.Result;
import com.dfcold.whulibbackend.domain.dto.*;
import com.dfcold.whulibbackend.domain.entity.User;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.domain.pipeline.pipelines.*;
import com.dfcold.whulibbackend.domain.service.IUserService;
import com.dfcold.whulibbackend.util.ContentUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dfcold
 * 代理访问图书馆资源
 */
@RestController
@RequestMapping("/proxy")
public class LibraryProxyController {
    @Resource
    GetRoomsPipeline getRoomsPipeline;
    @Resource
    GetSeatsPipeline getSeatsPipeline;
    @Resource
    GetAvailableTimePipeline getAvailableTimePipeline;
    @Resource
    ReservePipeline reservePipeline;
    @Resource
    GetReserveHistoryPipeline getReserveHistoryPipeline;
    @Resource
    IUserService userService;

    @Operation(summary = "获取所有房间", description = "获取所有房间")
    @GetMapping("/rooms")
    public Result getRooms(Authentication authentication, @ModelAttribute RoomCrawlingDto roomCrawlingDto) {
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getById(userId);
        CrawlingContent lastRunContent = user.getLastRunContent();
        lastRunContent.setRoomCrawlingDto(roomCrawlingDto);
        getRoomsPipeline.execute(lastRunContent);
        List<Room> rooms = lastRunContent.getRooms();
        userService.updateById(user);
        return Result.ok().data("rooms", rooms);
    }

    @Operation(summary = "获取所有座位", description = "获取所有座位")
    @GetMapping("/seats")
    public Result getSeats(Authentication authentication, @ModelAttribute SeatCrawlingDto seatCrawlingDto) {
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getById(userId);
        CrawlingContent lastRunContent = user.getLastRunContent();
        lastRunContent.setSeatCrawlingDto(seatCrawlingDto);
        getSeatsPipeline.execute(lastRunContent);
        List<Seat> seats = lastRunContent.getSeats();
        userService.updateById(user);
        return Result.ok().data("seats", seats);
    }

    @Operation(summary = "获取所有可用时间段", description = "获取所有可用时间段")
    @GetMapping("/time")
    public Result getTime(Authentication authentication, @ModelAttribute AvailableTimeCrawlingDto availableTimeCrawlingDto) {

        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getById(userId);
        CrawlingContent lastRunContent = user.getLastRunContent();
        lastRunContent.setAvailableTimeCrawlingDto(availableTimeCrawlingDto);
        getAvailableTimePipeline.execute(lastRunContent);
        AvailableTime availableTime = lastRunContent.getAvailableTime();
        return Result.ok().data("availableTime",availableTime);
    }

    @Operation(summary = "座位预约", description = "座位预约")
    @PostMapping("/res")
    public Result book(Authentication authentication,@RequestBody SeatResCrawlingDto seatResCrawlingDto) {
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getById(userId);
        CrawlingContent lastRunContent = user.getLastRunContent();
        lastRunContent.setSeatResCrawlingDto(seatResCrawlingDto);
        reservePipeline.execute(lastRunContent);
        if (!lastRunContent.isReserved()) {
            return Result.error().message("预约失败");
        }
        return Result.ok();
    }

    @Operation(summary = "获取所有预约情况", description = "获取所有预约情况")
    @GetMapping("/reserveHistory")
    public Result getRes(Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getById(userId);
        CrawlingContent lastRunContent = user.getLastRunContent();
        getReserveHistoryPipeline.execute(lastRunContent);
        List<ReserveHistory> reserveHistoryList = lastRunContent.getReserveHistoryList();
        return Result.ok().data("reserveHistoryList", reserveHistoryList);
    }

    @Operation(summary = "座位释放", description = "座位释放")
    @PostMapping("/release")
    public Result release(Authentication authentication) {
        return Result.ok();
    }
}
