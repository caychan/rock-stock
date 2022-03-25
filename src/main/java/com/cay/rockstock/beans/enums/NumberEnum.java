package com.cay.rockstock.beans.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Map;

public enum NumberEnum {
    GE('个', new BigDecimal(1)),
    SHI('十', new BigDecimal(10)),
    BAI('百', new BigDecimal(100)),
    QIAN('千', new BigDecimal(1_000)),
    WAN('万', new BigDecimal(10_000)),
    YI('亿', new BigDecimal(100_000_000));

    private final Character desc;
    private final BigDecimal times;

    NumberEnum(Character desc, BigDecimal times) {
        this.times = times;
        this.desc = desc;
    }

    private static final Map<Character, NumberEnum> instances;

    static {
        instances = Maps.uniqueIndex(Lists.newArrayList(NumberEnum.values()), NumberEnum::getDesc);
    }


    public static NumberEnum getEnum(Character desc) {
        return instances.get(desc);
    }

    public Character getDesc() {
        return desc;
    }

    public BigDecimal getTimes() {
        return times;
    }
}
