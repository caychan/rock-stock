package com.cay.rockstock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan({"com.cay.rockstock.*"})
@MapperScan("com.cay.rockstock.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class RockStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockStockApplication.class, args);
    }

}
