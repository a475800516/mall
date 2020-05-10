package com.ruoyi.project.shopping.mapper;

import com.ruoyi.project.shopping.domain.GoodType;

import java.util.List;

/**
 * 商品类型信息 数据层
 * 
 */
public interface GoodTypeMapper
{
    /**
     * 查询商品类型数据集合
     * 
     * @param goodType 商品类型信息
     * @return 商品类型数据集合
     */
    public List<GoodType> selectGoodTypeList(GoodType goodType);

    /**
     * 查询所有商品类型
     * 
     * @return 商品类型列表
     */
    public List<GoodType> selectGoodTypeAll();

    /**
     * 通过商品类型ID查询商品类型信息
     * 
     * @param goodTypeId 商品类型ID
     * @return 角色对象信息
     */
    public GoodType selectGoodTypeById(Long goodTypeId);

    /**
     * 删除商品类型信息
     * 
     * @param goodTypeId 商品类型ID
     * @return 结果
     */
    public int deleteGoodTypeById(Long goodTypeId);

    /**
     * 批量删除商品类型信息
     * 
     * @param goodTypeIds 需要删除的商品类型ID
     * @return 结果
     */
    public int deleteGoodTypeByIds(Long[] goodTypeIds);

    /**
     * 修改商品类型信息
     * 
     * @param goodType 商品类型信息
     * @return 结果
     */
    public int updateGoodType(GoodType goodType);

    /**
     * 新增商品类型信息
     * 
     * @param goodType 商品类型信息
     * @return 结果
     */
    public int insertGoodType(GoodType goodType);

}
