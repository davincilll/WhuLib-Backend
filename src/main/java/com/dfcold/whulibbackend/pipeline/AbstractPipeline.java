package com.dfcold.whulibbackend.pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dfcold
 */
public abstract class AbstractPipeline {
    private final List<AbstractProcessor> processors = new ArrayList<>();

    public AbstractPipeline addProcessor(AbstractProcessor processor) {
        processors.add(processor);
        // 允许链式调用

        return this;
    }

    public String execute(String input) {
        String result = input;
        CrawlingContent content = new CrawlingContent();
        for (AbstractProcessor processor : processors) {
            processor.process(content);
        }
        return result;
    }
}