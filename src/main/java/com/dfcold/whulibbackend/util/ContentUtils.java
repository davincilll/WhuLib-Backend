package com.dfcold.whulibbackend.util;

import com.dfcold.whulibbackend.domain.entity.User;
import com.dfcold.whulibbackend.domain.pipeline.CrawlingContent;
import com.dfcold.whulibbackend.domain.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dfcold
 */
@Component
public class ContentUtils {
    @Resource
    IUserService userService;
    public CrawlingContent getContent(Long id) {
        User user = userService.getById(id);
        return user.getLastRunContent();
        //List<HttpCookie> cookieList = new ArrayList<>();
        //// 假设 cookies 字符串是以分号分隔的
        //String[] cookieArray = cookies.split(";");
        //for (String cookie : cookieArray) {
        //    cookie = cookie.trim(); // 去掉前后的空格
        //    String[] cookieParts = cookie.split("=");
        //    if (cookieParts.length == 2) {
        //        String name = cookieParts[0].trim();
        //        String value = cookieParts[1].trim();
        //        cookieList.add(new HttpCookie(name, value));
        //    }
        //}
    }
}
