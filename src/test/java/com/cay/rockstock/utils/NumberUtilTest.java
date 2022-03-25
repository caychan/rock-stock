package com.cay.rockstock.utils;

import com.cay.rockstock.beans.enums.NumberEnum;
import org.junit.jupiter.api.Test;

public class NumberUtilTest {

    @Test
    public void NumberTest() {
        System.out.println(NumberUtil.formatNumber("1.3元"));
        System.out.println(NumberUtil.formatNumber("1.33万亿元", NumberEnum.WAN));
    }
}
