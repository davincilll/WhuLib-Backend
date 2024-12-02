package com.dfcold.whulibbackend.pipeline.processor;

import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.dto.Seat;
import com.dfcold.whulibbackend.dto.SeatResCrawlingDto;
import com.dfcold.whulibbackend.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.util.CrawlerUtils;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @author dfcold
 */
public class SeatCrawlingProcessor extends AbstractProcessor {

    final String FETCH_URL = "https://seat.lib.whu.edu.cn/mapBook/getSeatsByRoom";

    @Override
    @SneakyThrows
    public void process(CrawlingContent content) {
        SeatResCrawlingDto seatResCrawlingDto = content.getSeatResCrawlingDto();
        HttpResponse httpResponse = convertAndGet(seatResCrawlingDto);
        String json = httpResponse.body();
        List<Seat> seatList = CrawlerUtils.parseSeatList(json);
        seatList.forEach(seat -> seat.setRoomId(content.getSeatCrawlingDto().getRoom()));
        content.setSeats(seatList);
    }
}
