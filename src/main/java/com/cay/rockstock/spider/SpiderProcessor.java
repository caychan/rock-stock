package com.cay.rockstock.spider;

import com.cay.rockstock.config.StockConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SpiderProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(http://baidu\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("http://baidu\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            skip this page
//            page.setSkip(true);
//        }
//        log.info("page content: {}", page.getHtml());

        log.info("===========");
        log.info("id:{}", page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/i/text()"));
        log.info("name:{}", page.getHtml().xpath("//html/body/div[3]/div[2]/div[1]/text()"));
        log.info("price:{}", page.getHtml().xpath("/html/body/div[3]/div[3]/div[1]/div[1]/div[1]/div[1]/span/tidyText()"));
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void startSpider() {
        log.info("start spider...");
        List<String> urls = StockConfig.stockIds.stream().map(id->"http://info.stcn.com/dc/stock/?stockcode="+id).collect(Collectors.toList());
        Spider.create(new SpiderProcessor()).addUrl(urls.toArray(new String[]{})).thread(1).run();
        log.info("done spider...");
    }



}
