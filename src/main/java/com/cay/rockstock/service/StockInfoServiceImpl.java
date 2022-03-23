package com.cay.rockstock.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cay.rockstock.beans.entity.StockInfo;
import com.cay.rockstock.mapper.StockInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements StockInfoService {

    //可以不声明stockInfoMapper,当前类中的baseMapper就是实际上的stockInfoMapper
    @Autowired(required = false)
    private StockInfoMapper stockInfoMapper;

    /**
     * 使用MybatisPlus的默认方法
     */
    public void savePlus(StockInfo vo) {
        StockInfo one = this.getById(vo.getId());
        StockInfo stock = baseMapper.selectById(vo.getId());
    }
}
