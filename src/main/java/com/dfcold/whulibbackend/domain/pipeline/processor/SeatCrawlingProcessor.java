package com.dfcold.whulibbackend.domain.pipeline.processor;

import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.domain.dto.Seat;
import com.dfcold.whulibbackend.domain.dto.SeatCrawlingDto;
import com.dfcold.whulibbackend.domain.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.util.CrawlerUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dfcold
 */
@Component
public class SeatCrawlingProcessor extends AbstractProcessor {

    String fetchUrl = "https://seat.lib.whu.edu.cn/mapBook/getSeatsByRoom";
    String fetchSeatAvailablePeriodsUrl = "https://seat.lib.whu.edu.cn/freeBook/ajaxGetTime";

    @Override
    @SneakyThrows
    public void process(CrawlingContent content) {
        SeatCrawlingDto seatCrawlingDto = content.getSeatCrawlingDto();
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = convertAndGet(seatCrawlingDto,fetchUrl,cookieHeader);
        String json = httpResponse.body();

        List<Seat> seatList = CrawlerUtils.parseSeatList(json);
        seatList.forEach(seat -> {
            seat.setRoomId(content.getSeatCrawlingDto().getRoom());
        });
        content.setSeats(seatList);
    }
}
