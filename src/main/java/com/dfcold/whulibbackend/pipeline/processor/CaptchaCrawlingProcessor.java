package com.dfcold.whulibbackend.pipeline.processor;

import com.dfcold.whulibbackend.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.pipeline.CrawlingContent;

/**
 * @author dfcold
 */
public class CaptchaCrawlingProcessor extends AbstractProcessor {
    static final String FETCH_URL = "https://seat.lib.whu.edu.cn/auth/createCaptcha";


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