package com.dfcold.whulibbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dfcold
 * 指挥Content进行运行的Config配置
 */
@Data
public class RunConfig {
    /**
     * 喜欢的座位id
     */
    private List<String> seatIds = new ArrayList<>();
    /**
     * 座位预约时间段
     */
    private List<String> reserveTimes = new ArrayList<>();
    /**
     * 是否自动托管预约，定时检测位置状态，如果显示已经离开了，为了防止离开时间过长，自动进行续约
     * 将会在超时的29分钟后进行座位的自动释放，并预约半小时后的座位
     */
    private boolean autoRelease = false;
    /**
     * 是否启用
     */
    private boolean enable = false;
}
