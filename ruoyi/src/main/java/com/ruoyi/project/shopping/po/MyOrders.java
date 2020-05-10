package com.ruoyi.project.shopping.po;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 我的订单
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@Data
public class MyOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "订单编号")
    private String ordersId;

    @Excel(name = "订单总价格")
    private BigDecimal priceSum;

    /** 订单状态（0已下单未支付，1已支付待发货，2已发货待签收，3已签收，5已关闭） */
    @Excel(name = "订单状态", readConverterExp = "0=已下单未支付，1已支付待发货，2已发货待签收，3已签收，5已关闭")
    private String status;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freight;

    /** 买家用户ID */
    @Excel(name = "买家用户ID")
    private Long buyerId;

    /** 买家用户名称 */
    @Excel(name = "买家用户名称")
    private String buyerName;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long goodId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodName;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 购买商品的数量 */
    @Excel(name = "购买商品的数量")
    private Integer num;

}
