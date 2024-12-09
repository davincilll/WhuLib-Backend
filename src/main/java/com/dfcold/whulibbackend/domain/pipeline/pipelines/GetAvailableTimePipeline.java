package com.dfcold.whulibbackend.domain.pipeline.pipelines;

import com.dfcold.whulibbackend.domain.entity.User;
import com.dfcold.whulibbackend.domain.pipeline.AbstractPipeline;
import com.dfcold.whulibbackend.util.ContentUtils;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.domain.pipeline.processor.AvailableTimeCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.LoginCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.ValidLoginCrawlingProcessor;
import com.dfcold.whulibbackend.domain.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GetAvailableTimePipeline extends AbstractPipeline {
    @Resource
    private LoginCrawlingProcessor loginCrawlingProcessor;
    @Resource
    ValidLoginCrawlingProcessor validLoginCrawlingProcessor;
    @Resource
    AvailableTimeCrawlingProcessor availableTimeCrawlingProcessor;
    @Resource
    IUserService userService;

    @Resource
    ContentUtils contentUtils;

    @Override
    public void execute(CrawlingContent content){
        validLoginCrawlingProcessor.process(content);
        if (!content.isLogin()){
            loginCrawlingProcessor.process(content);
        }
        availableTimeCrawlingProcessor.process(content);
    }
}
