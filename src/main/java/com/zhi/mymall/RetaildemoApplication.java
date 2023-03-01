package com.zhi.mymall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RetaildemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetaildemoApplication.class, args);
    }
}
