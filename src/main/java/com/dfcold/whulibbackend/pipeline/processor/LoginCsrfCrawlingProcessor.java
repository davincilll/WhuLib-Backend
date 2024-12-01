package com.dfcold.whulibbackend.pipeline.processor;

import com.dfcold.whulibbackend.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.pipeline.CrawlingContent;

/**
 * @author dfcold
 */
public class LoginCsrfCrawlingProcessor extends AbstractProcessor {
    static final String FETCH_URL = "https://seat.lib.whu.edu.cn/login?targetUri=%2F";

    @Override
    public String getResult() {
        return "";
    }

    @Override
    public Object parseResult() {
        return null;
    }

    @Override
    protected void setResult(Object o, CrawlingContent content) {

    }
}
