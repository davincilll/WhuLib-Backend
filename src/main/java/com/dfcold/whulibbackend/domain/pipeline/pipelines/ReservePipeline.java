package com.dfcold.whulibbackend.domain.pipeline.pipelines;

import com.dfcold.whulibbackend.domain.pipeline.AbstractPipeline;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.domain.pipeline.processor.LoginCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.SeatResCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.ValidLoginCrawlingProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dfcold
 */
@Component
public class ReservePipeline extends AbstractPipeline {
    @Resource
    private LoginCrawlingProcessor loginCrawlingProcessor;
    @Resource
    ValidLoginCrawlingProcessor validLoginCrawlingProcessor;
    @Resource
    SeatResCrawlingProcessor seatResCrawlingProcessor;

    @Override
    public void execute(CrawlingContent content) {
        validLoginCrawlingProcessor.process(content);
        if (!content.isLogin()){
            loginCrawlingProcessor.process(content);
        }
        seatResCrawlingProcessor.process(content);
    }
}
