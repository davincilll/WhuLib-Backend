package com.dfcold.whulibbackend.pipeline.processor;

import cn.hutool.http.HttpRequest;
import com.dfcold.whulibbackend.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.util.CrawlerUtils;

/**
 * @author dfcold
 */
public class SeatResCrawlingProcessor extends AbstractProcessor {
    String fetchUrl = "https://seat.lib.whu.edu.cn/selfRes";
    String csrfTokenUrl = "https://seat.lib.whu.edu.cn/map";
    @Override
    public void process(CrawlingContent content) {

    }
    public void handleCsrfToken(CrawlingContent content){
        String body = HttpRequest.get(csrfTokenUrl).headerMap(baseHeaders, true).execute().body();
        content.getSeatResCrawlingDto().setSynchronizerToken(CrawlerUtils.parseCsrfToken(body));
    }
    public void handleSeatResSend(CrawlingContent content){
    }
}
