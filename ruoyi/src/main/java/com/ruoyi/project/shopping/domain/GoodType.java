package com.ruoyi.project.shopping.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品类型表
 * 
 */
@Data
public class GoodType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品类型序号 */
    @Excel(name = "商品类型序号", cellType = ColumnType.NUMERIC)
    private Long goodTypeId;

    /** 商品类型编码 */
    @Excel(name = "商品类型编码")
    private Long parentId;

    /** 商品类型名称 */
    @Excel(name = "商品类型名称")
    private String goodTypeName;

    /** 商品类型排序 */
    @Excel(name = "商品类型排序")
    private Integer orderNum;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 子商品类型 */
    private List<GoodType> children = new ArrayList<GoodType>();

}
