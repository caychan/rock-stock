package com.cay.rockstock.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cay.rockstock.beans.entity.Stock;
import com.cay.rockstock.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component(value = "stockService")
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {


}
