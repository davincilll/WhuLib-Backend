package com.dfcold.whulibbackend.domain.pipeline.processor;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.dfcold.whulibbackend.domain.dto.LoginCrawlingDto;
import com.dfcold.whulibbackend.domain.pipeline.AbstractProcessor;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.util.CrawlerUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        content.setLogin(true);
    }
    public void handleCsrfToken(CrawlingContent content){
        String body = HttpRequest.get(csrfTokenUrl).headerMap(baseHeaders, true).execute().body();
        String csrfToken = CrawlerUtils.parseCsrfToken(body);
        content.getLoginCrawlingDto().setSynchronizerToken(csrfToken);
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
        Map<String, String> captchaData = Map.of("base64_image",captchaImage);
        HttpResponse httpResponse1 = convertAndPost(captchaData, ocrUrl);
        String json1 = httpResponse1.body();
        Map<String,String> map1 = mapper.readValue(json1, new TypeReference<Map<String, String>>(){});
        String answer = map1.get("result");
        // 设置进content中
        content.getLoginCrawlingDto().setCaptchaId(captchaId);
        content.getLoginCrawlingDto().setAnswer(answer);
    }
    public void handleLoginSend(CrawlingContent content){
        LoginCrawlingDto loginCrawlingDto = content.getLoginCrawlingDto();
        HttpResponse httpResponse = convertAndPost(loginCrawlingDto,fetchUrl,loginHeaders);
        if(httpResponse.getStatus()==302){
            List<HttpCookie> cookies = httpResponse.getCookies();
            StringBuilder stringBuilder = new StringBuilder();
            for (HttpCookie cookie : cookies) {
                if (!stringBuilder.isEmpty()) {
                    stringBuilder.append("; ");
                }
                stringBuilder.append(cookie.getName()).append("=").append(cookie.getValue());
            }
            // 设置cotent的cookies
            content.setCookies(stringBuilder.toString());
        }else{
            log.debug("login failed,detail:{}", httpResponse.body());
            //handler other situation
            throw new RuntimeException("login failed");
        }
    }
}
