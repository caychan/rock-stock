package com.cay.rockstock.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CommonUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");


    /**
     * 得到今天的时间 yyyyMMdd
     */
    public static int getDate() {
        return NumberUtils.toInt(LocalDate.now().format(FORMATTER));
    }
}
