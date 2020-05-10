package com.ruoyi.project.shopping.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * 商品对象 good
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@Data
public class Good extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "商品序号", cellType = Excel.ColumnType.NUMERIC)
    private Long goodId;

    @Excel(name = "商品类型序号", cellType = Excel.ColumnType.NUMERIC)
    private Long goodTypeId;

    @Excel(name = "商品名称")
    private String goodName;

    @Excel(name = "商品价格")
    private BigDecimal price;

    @Excel(name = "销售量")
    private Integer sales;

    @Excel(name = "概述")
    private String summary;

    @Excel(name = "图片路径")
    private String pic;
}
