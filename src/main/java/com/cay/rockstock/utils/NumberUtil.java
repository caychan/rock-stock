package com.cay.rockstock.utils;

import com.cay.rockstock.beans.enums.NumberEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;


@Slf4j
public class NumberUtil {

    private static final String NUMBER_REGEX = "[^(0-9).]";
    private static final String CHINESE_NUMBER_REGEX = "[^(十百千万亿)]";

    /**
     * 转化为数字
     * 如：输入"1.3万亿"和NumberEnum.YI，输出为"13000"
     *
     * @param str  字符串
     * @param unit 结果的单位
     */
    public static BigDecimal formatNumber(String str, NumberEnum unit) {
        BigDecimal tmp = formatNumber(str);
        return tmp.divide(unit.getTimes(), RoundingMode.HALF_DOWN);
    }

    /**
     * 转化为数字
     * 如：输入"1.3元"，输出为"1.3"
     */
    public static BigDecimal formatNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return BigDecimal.ZERO;
        }
        try {
            String number = Pattern.compile(NUMBER_REGEX).matcher(str).replaceAll("").trim();
            String non = Pattern.compile(CHINESE_NUMBER_REGEX).matcher(str).replaceAll("").trim();
            BigDecimal tmp = NumberUtils.toScaledBigDecimal(number);
            if (StringUtils.isNotEmpty(non)) {
                for (Character ch : non.toCharArray()) {
                    NumberEnum numberEnum = NumberEnum.getEnum(ch);
                    if (numberEnum == null) {
                        log.error("format number, param is illegal:{},{}", ch, str);
                        continue;
                    }
                    tmp = tmp.multiply(numberEnum.getTimes());
                }
            }
            return tmp;
        } catch (Exception e) {
            log.error("format number error:{}", str, e);
            return BigDecimal.ZERO;
        }
    }

}
