package com.dfcold.whulibbackend.util;

import cn.hutool.http.HttpUtil;
import com.dfcold.whulibbackend.dto.Seat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dfcold
 */
public class CrawlerUtils extends HttpUtil {
    public static String parseCsrfToken(String html) {
        // 解析 HTML
        Document document = Jsoup.parse(html);

        // 查找 input 元素
        Element inputElement = document.getElementById("SYNCHRONIZER_TOKEN");

        if (inputElement != null) {
            // 获取 value 属性
            return inputElement.attr("value");
        } else {
            return null;
        }
    }

    public static boolean checkLoginSuccess(String html) {
        return true;
    }

    public static List<Seat> parseSeatList(String html) {
        List<Seat> seats = new ArrayList<>();
        Document doc = Jsoup.parse(html);

        // 选择所有座位的链接
        Elements seatElements = doc.select("li[id^=seat_] a");

        for (Element seatElement : seatElements) {
            // 获取座位的ID
            String id = seatElement.parent().id();
            // 获取座位号
            String seatNumber = seatElement.text();
            // 获取local属性
            String localAttr = seatElement.attr("local");

            Boolean local = null;
            if (!localAttr.isEmpty()) {
                local = Boolean.parseBoolean(localAttr);
            }

            seats.add(new Seat(id, seatNumber, local, null));
        }

        return seats;
    }
}
