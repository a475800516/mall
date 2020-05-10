package com.ruoyi.project.shopping.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 orders_good
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@Data
public class OrdersGood extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "订单ID")
    private String ordersId;

    @Excel(name = "商品ID")
    private Long goodId;

    @Excel(name = "购买商品数量")
    private Integer num;

}
