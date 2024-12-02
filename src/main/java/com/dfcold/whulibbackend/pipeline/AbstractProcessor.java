package com.dfcold.whulibbackend.pipeline;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * @author dfcold
 */
@Component
public abstract class AbstractProcessor {
    protected String fetchUrl = "";
    protected Map<String, String> baseHeaders = Map.of(
            "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.82",
            "Origin", "https://seat.lib.whu.edu.cn");
    protected Map<String, String> otherHeaders = Map.of(
    );
    @Resource
    protected ObjectMapper mapper;



    /**
     * 处理
     *
     * @param content 公共上下文
     */
    public abstract void process(CrawlingContent content);

    public HttpResponse convertAndGet(){
        return convertAndGet(Optional.empty(),fetchUrl,otherHeaders,baseHeaders);
    }
    public HttpResponse convertAndGet(String url){
        return convertAndGet(Optional.empty(), url, otherHeaders, baseHeaders);
    }
    public HttpResponse convertAndGet(Object object) {
        return convertAndGet(Optional.of(object), fetchUrl, otherHeaders, baseHeaders);
    }

    public HttpResponse convertAndGet(Object object, String url) {
        return convertAndGet(Optional.of(object), url, otherHeaders, baseHeaders);
    }

    public HttpResponse convertAndGet(Object object, Map<String, String> headers) {
        return convertAndGet(Optional.of(object), fetchUrl, headers, baseHeaders);
    }

    private HttpResponse convertAndGet(Optional<Object> object, String url, Map<String, String> headers, Map<String, String> baseHeaders) {
        String encodedUrl = url;
        if (object.isPresent()){
            Map<String, Object> formData = mapper.convertValue(object, new TypeReference<Map<String, Object>>() {
            });
            encodedUrl = HttpUtil.urlWithForm(url, formData, StandardCharsets.UTF_8, true);
        }
        return HttpRequest.get(encodedUrl)
                .headerMap(baseHeaders, true)
                .headerMap(headers, false)
                .execute();
    }

    public HttpResponse convertAndPost(Object object) {
        return convertAndPost(object, fetchUrl, otherHeaders, baseHeaders);
    }

    public HttpResponse convertAndPost(Object object, String url) {
        return convertAndPost(object, url, otherHeaders, baseHeaders);
    }

    public HttpResponse convertAndPost(Object object, Map<String, String> headers) {
        return convertAndPost(object, fetchUrl, headers, baseHeaders);
    }

    private HttpResponse convertAndPost(Object object, String url, Map<String, String> headers, Map<String, String> baseHeaders) {
        Map<String, Object> formData = mapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
        return HttpRequest.post(url)
                .headerMap(baseHeaders, true)
                .headerMap(headers, false)
                .form(formData).execute();
    }
}




