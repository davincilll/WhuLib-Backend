package com.dfcold.whulibbackend.pipeline.processor;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.dto.LoginCrawlingDto;
import com.dfcold.whulibbackend.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.util.CrawlerUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
 * @author dfcold
 */
@Component
@Slf4j
public class LoginCrawlingProcessor extends AbstractProcessor {
    String fetchUrl = "https://seat.lib.whu.edu.cn/auth/signIn";
    String csrfTokenUrl = "https://seat.lib.whu.edu.cn/login?targetUri=%2F";
    String captchaUrl = "https://seat.lib.whu.edu.cn/auth/createCaptcha";
    String ocrUrl = "https://spider-service.myhk.fun/ocr/base64";
    Map<String, String> loginHeaders = Map.of(
            "Referer","https://seat.lib.whu.edu.cn/login?targetUri=%2F");
    Map<String,String> csrfTokenHeaders = Map.of();
    @Override
    public void process(CrawlingContent content) {
        handleCsrfToken(content);
        handleCaptcha(content);
        handleLoginSend(content);

    }
    public void handleCsrfToken(CrawlingContent content){
        String body = HttpRequest.get(csrfTokenUrl).headerMap(baseHeaders, true).execute().body();
        content.getLoginCrawlingDto().setSynchronizerToken(CrawlerUtils.parseCsrfToken(body));
    }
    @SneakyThrows
    public void handleCaptcha(CrawlingContent content){
        HttpResponse httpResponse = convertAndGet(captchaUrl);
        String json = httpResponse.body();
        // 将 JSON 字符串反序列化为一个 Map
        Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
        // 获取 rooms 列表
        String captchaId = map.get("captchaId");
        String captchaImage = map.get("captchaImage");
        //解析captchaImage
        HttpResponse httpResponse1 = convertAndGet(captchaImage, ocrUrl);
        String json1 = httpResponse.body();
        Map<String,String> map1 = mapper.readValue(json1, new TypeReference<Map<String, String>>(){});
        String answer = map1.get("result");
        // 设置进content中
        content.getLoginCrawlingDto().setCaptchaId(captchaId);
        content.getLoginCrawlingDto().setAnswer(answer);
    }
    public void handleLoginSend(CrawlingContent content){
        LoginCrawlingDto loginCrawlingDto = content.getLoginCrawlingDto();
        HttpResponse httpResponse = convertAndPost(loginCrawlingDto,loginHeaders);
        if(CrawlerUtils.checkLoginSuccess(httpResponse.body())){
            List<HttpCookie> cookies = httpResponse.getCookies();
            content.setCookies(cookies);
        }else{
            log.debug("login failed,detail:{}", httpResponse.body());
            //handler other situation
        }
    }
}
