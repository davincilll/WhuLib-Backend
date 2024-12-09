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
public class ValidLoginCrawlingProcessor extends AbstractProcessor {

    String fetchUrl = "https://seat.lib.whu.edu.cn/map";
    @Override
    @SneakyThrows
    public void process(CrawlingContent content) {
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = convertAndGet(fetchUrl, cookieHeader);
        if (httpResponse.getStatus() == 302){
            content.setLogin(false);
        }
    }
}
