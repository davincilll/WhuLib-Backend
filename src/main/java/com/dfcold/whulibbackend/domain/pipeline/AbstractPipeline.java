package com.dfcold.whulibbackend.domain.pipeline;

/**
 * @author dfcold
 */
public abstract class AbstractPipeline {

    public abstract void execute(CrawlingContent content);
}