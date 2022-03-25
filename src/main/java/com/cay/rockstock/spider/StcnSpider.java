package com.cay.rockstock.spider;

import com.alibaba.fastjson.JSON;
import com.cay.rockstock.beans.entity.Stock;
import com.cay.rockstock.beans.enums.NumberEnum;
import com.cay.rockstock.config.ApplicationContextHelper;
import com.cay.rockstock.service.StockInfoService;
import com.cay.rockstock.service.StockService;
import com.cay.rockstock.spider.pipeline.StcnPipeline;
import com.cay.rockstock.utils.CommonUtil;
import com.cay.rockstock.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
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
            //股票代码
            String code = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/i/text()").get();
            stock.setCode(code);

            //股票名称
            String name = page.getHtml().xpath("//html/body/div[3]/div[2]/div[1]/text()").get();
            stock.setName(name);

            //当前价格 14.98
            String price = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/span/text()").get();
            stock.setPrice(NumberUtil.formatNumber(price));

            //今开 15.15
            String open = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[1]/span[1]/span/text()").get();
            stock.setOpen(NumberUtil.formatNumber(open));

            //昨收 15.20
            String lastClose = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[1]/span[4]/span/text()").get();
            stock.setLastClose(NumberUtil.formatNumber(lastClose));

            //最高 15.32
            String high = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[1]/span[2]/span/text()").get();
            stock.setHigh(NumberUtil.formatNumber(high));

            //最低 14.82
            String low = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[1]/span[3]/span/text()").get();
            stock.setLow(NumberUtil.formatNumber(low));

            //涨跌额 -0.22
            String riseValue = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[1]/div[1]/div[2]/span[1]/text()").get();
            stock.setRiseValue(NumberUtil.formatNumber(riseValue));

            //涨幅 -1.64%
            String riseRatio = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[1]/div[1]/div[2]/span[2]/text()").get();
            stock.setRiseRatio(NumberUtil.formatNumber(riseRatio));

            //振幅 2.44%
            String swingRatio = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[3]/span[1]/span/text()").get();
            stock.setSwingRatio(NumberUtil.formatNumber(swingRatio));

            //换手率 32.98万手
            String tradeVolume = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[2]/span[1]/span/text()").get();
            stock.setTradeVolume(NumberUtil.formatNumber(tradeVolume, NumberEnum.WAN));

            //成交额 4.99亿元
            String tradeValue = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[2]/span[2]/span/text()").get();
            stock.setTradeValue(NumberUtil.formatNumber(tradeValue, NumberEnum.YI));

            //总市值2912.97亿元
            String totalValue = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[2]/span[3]/span/text()").get();
            stock.setTotalMarketValue(NumberUtil.formatNumber(totalValue, NumberEnum.YI));
            
            //流通市值 2912.77亿元
            String circulatingMarketValue = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[2]/span[4]/span/text()").get();
            stock.setCirculatingMarketValue(NumberUtil.formatNumber(circulatingMarketValue, NumberEnum.YI));

            //换手率 0.38%
            String turnover = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[3]/span[2]/span/text()").get();
            stock.setTurnoverRatio(NumberUtil.formatNumber(turnover));

            //市净率 0.89
            String bookRatio = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[3]/span[3]/span/text()").get();
            stock.setBookRatio(NumberUtils.toScaledBigDecimal(bookRatio));

            //市盈率 8.00
            String earnRatio = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[2]/div[3]/span[4]/span/text()").get();
            stock.setEarnRatio(NumberUtils.toScaledBigDecimal(earnRatio));

            String industry = page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[3]/span[1]/text()").get();
            stock.setIndustry(industry);

//            String stockholder = page.getHtml().xpath("").get();
//            String open = page.getHtml().xpath("").get();

            stock.setDate(CommonUtil.getDate());
            stock.setCreateTime(new Date());
            stock.setUpdateTime(new Date());

            page.putField("stock", stock);

            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
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
                .addPipeline(new StcnPipeline())
                .thread(1)
                .run();
        log.info("done stcn spider...");
    }


}
