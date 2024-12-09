package com.dfcold.whulibbackend.domain.pipeline.processor;

import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.domain.dto.AvailableTime;
import com.dfcold.whulibbackend.domain.dto.AvailableTimeCrawlingDto;
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
public class AvailableTimeCrawlingProcessor extends AbstractProcessor {
    String fetchUrl = "https://seat.lib.whu.edu.cn/freeBook/ajaxGetTime";
    @Override
    @SneakyThrows
    public void process(CrawlingContent content) {
        AvailableTimeCrawlingDto availableTimeCrawlingDto = content.getAvailableTimeCrawlingDto();
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = convertAndGet(availableTimeCrawlingDto,fetchUrl, cookieHeader);
        String json = httpResponse.body();
        AvailableTime availableTime = CrawlerUtils.parseAvailableTime(json);
        availableTime.setSeat(availableTimeCrawlingDto.getId());
        content.setAvailableTime(availableTime);
    }
}
