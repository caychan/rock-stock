package com.cay.rockstock.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "stock")
public class Stock {

    //主键
    @TableId
    private Integer id;

    //代码
    @TableField
    private String code;

    //名称
    @TableField
    private String name;

    //当前价格
    @TableField
    private BigDecimal price;

    //昨收
    @TableField
    private BigDecimal lastClose;

    //今开
    @TableField
    private BigDecimal open;

    //今收
    @TableField
    private BigDecimal close;

    //今高
    @TableField
    private BigDecimal high;

    //今低
    @TableField
    private BigDecimal low;

    //涨跌额
    @TableField
    private BigDecimal riseValue;

    //涨幅
    @TableField
    private BigDecimal riseRatio;

    //振幅
    @TableField
    private BigDecimal swingRatio;

    //总市值
    @TableField
    private BigDecimal totalMarketValue;

    //流通市值
    @TableField
    private BigDecimal circulatingMarketValue;

    //成交额
    @TableField
    private BigDecimal tradeValue;

    //成交量
    @TableField
    private BigDecimal tradeVolume;

    //换手率
    @TableField
    private BigDecimal turnoverRatio;

    //市净率
    @TableField
    private BigDecimal bookRatio;

    //市盈率
    @TableField
    private BigDecimal earnRatio;

    //资金流向
    @TableField
    private Integer fundFlow;

    //所属行业
    @TableField
    private String industry;

    //股东数量
    @TableField
    private Integer stockholder;

    //时间
    @TableField
    private Integer date;

    //更新时间
    @TableField
    private Date updateTime;

    //创建时间
    @TableField
    private Date createTime;

    //附加信息
    @TableField
    private String extra;

}
