package com.dfcold.whulibbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dfcold
 */
@SpringBootApplication
@MapperScan("com.dfcold.whulibbackend.domain.mapper")
public class WhuLibBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhuLibBackendApplication.class, args);
    }

}
