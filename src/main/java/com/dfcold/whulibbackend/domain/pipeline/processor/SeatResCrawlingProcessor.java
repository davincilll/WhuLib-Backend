package com.dfcold.whulibbackend.domain.pipeline.processor;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.domain.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.util.CrawlerUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dfcold
 */
@Component
public class SeatResCrawlingProcessor extends AbstractProcessor {
    String fetchUrl = "https://seat.lib.whu.edu.cn/selfRes";
    String csrfTokenUrl = "https://seat.lib.whu.edu.cn/map";
    @Override
    public void process(CrawlingContent content) {
        handleCsrfToken(content);
        handleSeatResSend(content);
    }
    public void handleCsrfToken(CrawlingContent content){
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies());
        HttpResponse httpResponse = HttpRequest.get(csrfTokenUrl).headerMap(baseHeaders, true).headerMap(cookieHeader, false).execute();
        String body = httpResponse.body();
        String csrfToken = CrawlerUtils.parseCsrfToken(body);
        content.getSeatResCrawlingDto().setSynchronizerToken(csrfToken);
    }
    public void handleSeatResSend(CrawlingContent content){
        Map<String,String> cookieHeader = Map.of("Cookie", content.getCookies()
                ,"Referer","https://seat.lib.whu.edu.cn/map"
        );
        HttpResponse httpResponse = convertAndPost(content.getSeatResCrawlingDto(), fetchUrl, cookieHeader);
        if (httpResponse.getStatus() == 200){
            if (httpResponse.body().contains("预约失败，请尽快选择其他时段或座位")){
                content.setReserved(false);
            }else{
                content.setReserved(true);
            }
        }
    }
}
