package com.cay.rockstock.mysql;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cay.rockstock.beans.entity.StockInfo;
import com.cay.rockstock.service.StockInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockInfoTest {

    @Autowired(required = false)
    public StockInfoService stockInfoService;


    @Test
    public void testInsert() {
        StockInfo stockInfo = new StockInfo();
        stockInfo.setCode("000005");
        stockInfo.setName("平安");
//        stockInfo.setCreateTime(new Date());
//        stockInfo.setUpdateTime(new Date());

        boolean res = stockInfoService.save(stockInfo);
        log.info("save result: {}, {}", res, stockInfo);
    }

    @Test
    public void testUpdate() {
        StockInfo stockInfo = new StockInfo();
        stockInfo.setCode("000001");
        stockInfo.setName("平安223");
        UpdateWrapper<StockInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("code", stockInfo.getCode());

//        stockInfo.setCreateTime(new Date());
//        stockInfo.setUpdateTime(new Date());

        boolean res = stockInfoService.update(stockInfo, wrapper);
        log.info("updateById result: {}, {}", res, stockInfo);
    }

}
