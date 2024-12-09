package com.dfcold.whulibbackend.domain.pipeline.processor;

import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.domain.dto.Room;
import com.dfcold.whulibbackend.domain.dto.RoomCrawlingDto;
import com.dfcold.whulibbackend.domain.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dfcold
 */
@Component
public class RoomCrawlingProcessor extends AbstractProcessor {
    String fetchUrl = "https://seat.lib.whu.edu.cn/mapBook/ajaxGetRooms";
    @Override
    @SneakyThrows
    public void process(CrawlingContent content) {
        RoomCrawlingDto roomCrawlingDto = content.getRoomCrawlingDto();
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = convertAndGet(roomCrawlingDto,fetchUrl, cookieHeader);
        String json = httpResponse.body();
        // 将 JSON 字符串反序列化为一个 Map
        Map<String, List<Room>> map = mapper.readValue(json, new TypeReference<Map<String, List<Room>>>(){});
        // 获取 rooms 列表
        List<Room> rooms = map.get("rooms");
        content.setRooms(rooms);
    }
}
