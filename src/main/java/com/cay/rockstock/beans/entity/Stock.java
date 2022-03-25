package com.cay.rockstock.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "stock_info")
public class Stock {

    //主键
    @TableId
    private int id;

    //代码
    @TableField
    private String code;

    //名称
    @TableField
    private String name;

    //当前价格
    @TableField
    private Float price;

    //昨收
    @TableField
    private Float lastClose;

    //今开
    @TableField
    private Float open;

    //今收
    @TableField
    private Float close;

    //今高
    @TableField
    private Float high;

    //今低
    @TableField
    private Float low;

    //涨幅
    @TableField
    private Float riseRatio;

    //总市值
    @TableField
    private Double totalMarketValue;

    //流通市值
    @TableField
    private Double circulatingMarketValue;

    //成交额
    @TableField
    private Float tradeValue;

    //成交量
    @TableField
    private Float tradeVolume;

    //换手率
    @TableField
    private Float turnoverRatio;

    //市净率
    @TableField
    private Float bookRatio;

    //市盈率
    @TableField
    private Float earnRatio;

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
