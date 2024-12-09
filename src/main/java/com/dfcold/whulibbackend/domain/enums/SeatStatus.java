package com.dfcold.whulibbackend.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dfcold
 */
@AllArgsConstructor
@Slf4j
public enum SeatStatus {
    /**
     * 空闲
     */
    IDLE("idle"),
    /**
     * 使用中
     */
    USRE("usre"),
    /**
     * 空闲，有电源
     */
    IDLE_POWER("idle_power"),
    /**
     * 空闲，靠窗 有电源
     */
    IDLE_BOTH("idle_both"),
    /**
     * 使用中，有电源
     */
    INUSE_POWER("inuse_power"),
    /**
     * 使用中，靠窗 有电源
     */
    INUSE_BOTH("inuse_both"),
    /**
     * 离开中
     */
    LEAVE("leave"),
    /**
     * 预约中
     */
    AGREEMENT("agreement"),
    /**
     * 使用中,电脑
     */
    INUSE_COMPUTER("inuse_computer"),
    /**
     * 空闲,电脑
     */
    IDLE_COMPUTER("idle_computer"),
    /**
     * 无法使用
     */
    NO_USRE("noUsre"),
    /**
     * 未知
     */

    UNKNOWN("unknown");

    /**
     * 使用状态
     */
    @Getter
    private final String status;

    public static SeatStatus fromStatus(String status) {
        for (SeatStatus seatStatus : SeatStatus.values()) {
            if (seatStatus.getStatus().equals(status)) {
                return seatStatus;
            }
        }
        log.debug("Unknown status: {}", status);
        // 处理未知状态
        return UNKNOWN;
    }

}