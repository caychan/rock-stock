package com.cay.rockstock.spider;

import com.alibaba.fastjson.JSON;
import com.cay.rockstock.beans.entity.Stock;
import com.cay.rockstock.config.StockConfig;
import com.cay.rockstock.spider.pipeline.StcnPipeline;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.CharSet;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Slf4j
@Component
public class StcnSpider implements PageProcessor {
    public static final String URL_PREFIX = "http://info.stcn.com/dc/stock/?stockcode=";
    private final Site site = Site.me().setCharset(StandardCharsets.UTF_8.name()).setRetryTimes(1).setSleepTime(5000).setTimeOut(3000);

    @Resource
    private ExecutorService commonExecutor;

    @Override
    public void process(Page page) {
        try {
            Stock stock = new Stock();
            String code = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/i/text()").get();
            stock.setCode(code);

            String name = page.getHtml().xpath("//html/body/div[3]/div[2]/div[1]/text()").get();
            stock.setName(name);

            String price = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/span/tidyText()").get();
            stock.setPrice(new Float(price));

            log.info("stock record:{}", JSON.toJSONString(stock));

            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
        } catch (Exception e) {
            log.error("process stcn spider error:{}", page.getUrl().get(), e);
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void startSpider(List<String> ids) {
        log.info("start stcn spider...");
        List<String> urls = ids.stream().map(id -> URL_PREFIX + id).collect(Collectors.toList());
        Spider.create(new StcnSpider())
                .addUrl(urls.toArray(new String[]{}))
                .setSpawnUrl(false)
                .setExitWhenComplete(true)
                .setExecutorService(commonExecutor)
                .thread(1)
                .run();
        log.info("done stcn spider...");
    }

    public static void main(String[] args) {
        StcnSpider stcnSpider = new StcnSpider();
        stcnSpider.startSpider(Lists.newArrayList("000001"));
    }

}
