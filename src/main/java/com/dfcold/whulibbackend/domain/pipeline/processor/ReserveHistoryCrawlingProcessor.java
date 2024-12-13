package com.dfcold.whulibbackend.domain.pipeline.processor;

import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.domain.dto.ReserveHistory;
import com.dfcold.whulibbackend.domain.dto.Room;
import com.dfcold.whulibbackend.domain.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.dfcold.whulibbackend.util.CrawlerUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dfcold
 * 预约历史记录处理器
 */
@Component
public class ReserveHistoryCrawlingProcessor extends AbstractProcessor {
    String fetchUrl = "https://seat.lib.whu.edu.cn/history?type=SEAT";

    @Override
    public void process(CrawlingContent content) {
        List<ReserveHistory> reserveHistoryList = getHistory(content);
        //reserveHistoryList.addAll(getMoreHistory(content));
        content.setReserveHistoryList(reserveHistoryList);
    }

    public List<ReserveHistory> getHistory(CrawlingContent content) {
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = convertAndGet(fetchUrl, cookieHeader);
        String result = httpResponse.body();
        List<ReserveHistory> reserveHistoryList = CrawlerUtils.parseReserveHistory(result);
        return reserveHistoryList;
    }

    public List<ReserveHistory> getMoreHistory(CrawlingContent content) {

        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = convertAndGet(fetchUrl, cookieHeader);
        String result = httpResponse.body();
        List<ReserveHistory> reserveHistoryList = CrawlerUtils.parseMoreReserveHistory(result);
        return reserveHistoryList;
    }
}
