package com.dfcold.whulibbackend.pipeline;

import com.dfcold.whulibbackend.util.CrawlerUtils;
import org.springframework.stereotype.Component;

/**
 * @author dfcold
 * 用这个全局的类来存取给线程级别的content一个存储的地方
 */
@Component
public class ContextHolder {
    private static final ThreadLocal<CrawlingContent> CONTEXT = new ThreadLocal<>();
    public static void set(CrawlingContent content) {
        CONTEXT.set(content);
    }
    public static CrawlingContent get() {
        return CONTEXT.get();
    }
    
}
