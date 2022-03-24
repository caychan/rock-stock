package com.cay.rockstock.controller;


import com.cay.rockstock.beans.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public CommonResponse startSpider() {
        log.info("receive test request");
        return CommonResponse.success();
    }
}
