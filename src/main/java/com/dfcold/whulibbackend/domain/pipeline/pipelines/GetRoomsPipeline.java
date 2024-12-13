package com.dfcold.whulibbackend.domain.pipeline.pipelines;

import com.dfcold.whulibbackend.domain.entity.User;
import com.dfcold.whulibbackend.domain.pipeline.AbstractPipeline;
import com.dfcold.whulibbackend.util.ContentUtils;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.domain.pipeline.processor.LoginCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.RoomCrawlingProcessor;
import com.dfcold.whulibbackend.domain.pipeline.processor.ValidLoginCrawlingProcessor;
import com.dfcold.whulibbackend.domain.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dfcold
 */
@Component
public class GetRoomsPipeline extends AbstractPipeline {
    @Resource
    private LoginCrawlingProcessor loginCrawlingProcessor;
    @Resource
    ValidLoginCrawlingProcessor validLoginCrawlingProcessor;
    @Resource
    RoomCrawlingProcessor roomCrawlingProcessor;
    @Override
    public void execute(CrawlingContent content){
        validLoginCrawlingProcessor.process(content);
        if (!content.isLogin()){
            loginCrawlingProcessor.process(content);
        }
        roomCrawlingProcessor.process(content);
    }
}
