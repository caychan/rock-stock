package com.cay.rockstock.mysql;

import com.cay.rockstock.beans.entity.Stock;
import com.cay.rockstock.service.StockService;
import com.cay.rockstock.spider.StcnSpider;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockTest {

    @Autowired(required = false)
    public StockService stockService;
    @Autowired(required = false)
    private StcnSpider stcnSpider;

    @Test
    public void testInsert() {
        Stock stock = new Stock();
        stock.setCode("000005");
        stock.setName("平安");
//        stockInfo.setCreateTime(new Date());
//        stockInfo.setUpdateTime(new Date());

        boolean res = stockService.save(stock);
        log.info("save result: {}, {}", res, stock);
    }


    @Test
    public void testSpider() {
        stcnSpider.startSpider(Lists.newArrayList("000006", "0000007"));
    }


}
