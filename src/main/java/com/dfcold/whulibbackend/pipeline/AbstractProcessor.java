package com.dfcold.whulibbackend.pipeline;

/**
 * @author dfcold
 */
public abstract class AbstractProcessor<T> {
    static final String FETCH_URL = "";
    /**
     * 处理方法
     */
    public void process(CrawlingContent content){
        String res = getResult();
        setResult(parseResult(),content);
    };

    /**
     * 向fetch_url 发送请求，获取结果，并进行解析
     * @return 请求得到的raw结果
     */
    public abstract String getResult();

    /**
     * 将raw 结果转化为实体类，
     * @return T
     */
    public abstract T parseResult();

    /**
     * 将结果存入content中，这里只在process接口被调用
     * @param t T类型实例 content 共享上下文
     */
    protected abstract void setResult(T t,CrawlingContent content);
}
