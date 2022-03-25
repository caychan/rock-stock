package com.cay.rockstock.spider.pipeline;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Slf4j
public class StcnPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("stcnPipeline :{}, {}", JSON.toJSONString(resultItems), JSON.toJSONString(task));
    }
}
