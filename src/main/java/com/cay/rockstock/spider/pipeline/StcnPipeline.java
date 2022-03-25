package com.cay.rockstock.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.cay.rockstock.beans.entity.Stock;
import com.cay.rockstock.config.ApplicationContextHelper;
import com.cay.rockstock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Slf4j
public class StcnPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Stock stock = resultItems.get("stock");
        if (stock == null || StringUtils.isEmpty(stock.getCode())) {
            return;
        }

        try {
            StockService stockService = (StockService) ApplicationContextHelper.getBean("stockService");
            boolean success = stockService.save(stock);
            log.info("save stock result:{}, {}", success, JSON.toJSONString(stock));
        } catch (Exception e) {
            log.error("save stock error:{}", stock, e);
        }
    }

}
