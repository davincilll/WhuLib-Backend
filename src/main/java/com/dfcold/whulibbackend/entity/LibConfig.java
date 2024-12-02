package com.dfcold.whulibbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @author dfcold
 * 图书馆配置信息
 */
@Entity
@Data
public class LibConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * 保存的cookie信息
     */
    @Column
    private String cookies;
    /**
     * 配置自动运行的时间
     */
    private LocalTime autoRunTime;


    private String preferredSeat;
}
