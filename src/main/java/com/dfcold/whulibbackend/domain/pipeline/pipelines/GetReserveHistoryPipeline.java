package com.dfcold.whulibbackend.domain.pipeline.pipelines;

import com.dfcold.whulibbackend.domain.pipeline.AbstractPipeline;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.domain.pipeline.processor.LoginCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.ReserveHistoryCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.ValidLoginCrawlingProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dfcold
 */
@Component
public class GetReserveHistoryPipeline extends AbstractPipeline {
    @Resource
    ReserveHistoryCrawlingProcessor reserveHistoryCrawlingProcessor;
    @Resource
    LoginCrawlingProcessor loginCrawlingProcessor;
    @Resource
    ValidLoginCrawlingProcessor validLoginCrawlingProcessor;
    @Override
    public void execute(CrawlingContent content){
        validLoginCrawlingProcessor.process(content);
        if (!content.isLogin()){
            loginCrawlingProcessor.process(content);
        }
        reserveHistoryCrawlingProcessor.process(content);
    }
}
