package com.dfcold.whulibbackend.util;

import cn.hutool.http.HttpUtil;
import com.dfcold.whulibbackend.domain.dto.AvailableTime;
import com.dfcold.whulibbackend.domain.dto.Seat;
import com.dfcold.whulibbackend.domain.enums.SeatStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalTime;
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
        Element inputElement = document.select("#SYNCHRONIZER_TOKEN").first();

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
            String idInfo = seatElement.parent().id();
            String id = idInfo.substring(5);
            // 获取座位号
            String seatNumber = seatElement.text();
            // 获取local属性
            String localAttr = seatElement.attr("local");
            String seatStatus = seatElement.attr("class");
            SeatStatus status = SeatStatus.fromStatus(seatStatus);
            Boolean local = null;
            if (!localAttr.isEmpty()) {
                local = Boolean.parseBoolean(localAttr);
            }

            seats.add(new Seat(id, seatNumber, local,status, null));
        }

        return seats;
    }
    public static AvailableTime parseAvailableTime(String html) {
        Document doc = Jsoup.parse(html);
        AvailableTime availableTime = new AvailableTime();
        List<String> availablePeriodList = new ArrayList<>();
        Elements elements = doc.select("a[time]");
        if (elements.size()>0){
            String startTimeStr = null;
            String endTimeStr = null;

            for (Element element : elements) {
                String timeStr = element.attr("time");
                if( "now".equals(timeStr)){
                    startTimeStr = timeStr;
                }else{
                    int timeInt = Integer.parseInt(timeStr);
                    //起止时间是now，则用当前节点的时间续期半小时
                    if ("now".equals(startTimeStr)){
                        endTimeStr = String.valueOf(timeInt+30);
                    }else{
                        // 当前节点的值和endTimeStr相同，续期半小时
                        if (timeInt==Integer.parseInt(endTimeStr)){
                            endTimeStr = String.valueOf(timeInt+30);
                            // 当前节点与endTimeStr不同，则代表有了一个新时间段
                        }else{
                            // 添加进list，清空
                            availablePeriodList.add(startTimeStr+"-"+endTimeStr);
                            startTimeStr = timeStr;
                            endTimeStr = String.valueOf(timeInt+30);
                        }
                    }
                }
            }
            // 处理最后一个时间段
            if (!(endTimeStr==null)){
                //endTimeStr = String.valueOf(Integer.parseInt(startTimeStr)+30);
                availablePeriodList.add(startTimeStr+"-"+endTimeStr);
            }
            // 只有一个now的情况
            if (("now".equals(startTimeStr)) && endTimeStr == null){
                // 获取now代表的分钟数
                LocalTime nowTime = LocalTime.now();
                int nowMinute = nowTime.getMinute();
                if (nowMinute>=30){
                    // 如果当前分钟数大于等于30，则取下一个半小时
                    endTimeStr = String.valueOf((nowTime.getHour()+1)*60);
                }else{
                    // 如果当前分钟数小于30，则取当前半小时
                    endTimeStr = String.valueOf(nowTime.getHour()*60 + 30);
                }
                availablePeriodList.add(startTimeStr+"-"+endTimeStr);
            }
            availableTime.setAvailablePeriodList(availablePeriodList);
        }
        return availableTime;
    }
}
