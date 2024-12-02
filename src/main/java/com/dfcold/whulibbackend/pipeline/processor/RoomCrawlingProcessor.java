package com.dfcold.whulibbackend.pipeline.processor;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.dfcold.whulibbackend.dto.Room;
import com.dfcold.whulibbackend.dto.RoomCrawlingDto;
import com.dfcold.whulibbackend.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.pipeline.CrawlingContent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author dfcold
 */
public class RoomCrawlingProcessor extends AbstractProcessor {
    final String FETCH_URL = "https://seat.lib.whu.edu.cn/mapBook/ajaxGetRooms";
    @Override
    @SneakyThrows
    public void process(CrawlingContent content) {
        RoomCrawlingDto roomCrawlingDto = content.getRoomCrawlingDto();
        HttpResponse httpResponse = convertAndGet(roomCrawlingDto);
        String json = httpResponse.body();
        // 将 JSON 字符串反序列化为一个 Map
        Map<String, List<Room>> map = mapper.readValue(json, new TypeReference<Map<String, List<Room>>>(){});
        // 获取 rooms 列表
        List<Room> rooms = map.get("rooms");
        content.setRooms(rooms);
    }
}
