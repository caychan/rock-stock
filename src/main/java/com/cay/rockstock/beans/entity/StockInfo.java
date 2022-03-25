package com.cay.rockstock.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "stock_info")
public class StockInfo {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 其他信息
     */
    @TableField
    private String code;

    /**
     * 是否伪删除  0否 1是
     */
    @TableField
    private String name;

    /**
     * 创建日期
     */
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Date createTime;

    /**
     * 更新日期
     */
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Date updateTime;

}
