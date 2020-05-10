package com.ruoyi.project.shopping.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 【请填写功能名称】对象 order
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@Data
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    @Excel(name = "订单编号")
    private String ordersId;

    /** $column.columnComment */
    @Excel(name = "订单总价格")
    private BigDecimal priceSum;

    /** 订单状态（0已下单未支付，1已支付待发货，2已发货待签收，3已签收，5已关闭） */
    @Excel(name = "订单状态", readConverterExp = "0=已下单未支付，1已支付待发货，2已发货待签收，3已签收，5已关闭")
    private String status;

    /** 支付二维码 */
    @Excel(name = "支付二维码")
    private String payQrCode;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freight;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 买家用户ID */
    @Excel(name = "买家用户ID")
    private Long buyerId;

}
