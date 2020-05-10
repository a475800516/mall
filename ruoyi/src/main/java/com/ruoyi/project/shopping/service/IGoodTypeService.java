package com.ruoyi.project.shopping.service;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.shopping.domain.GoodType;

import java.util.List;

/**
 * 商品类型信息 服务层
 * 
 */
public interface IGoodTypeService
{
    /**
     * 查询商品类型信息集合
     * 
     * @param goodType 商品类型信息
     * @return 商品类型列表
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
     * @param postIds 需要删除的商品类型ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteGoodTypeByIds(Long[] postIds);

    /**
     * 新增保存商品类型信息
     * 
     * @param goodType 商品类型信息
     * @return 结果
     */
    public int insertGoodType(GoodType goodType);

    /**
     * 修改保存商品类型信息
     * 
     * @param goodType 商品类型信息
     * @return 结果
     */
    public int updateGoodType(GoodType goodType);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param goodTypes 商品类型列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildGoodTypeTreeSelect(List<GoodType> goodTypes);

    /**
     * 构建前端所需要树结构
     *
     * @param goodTypes 商品类型列表
     * @return 树结构列表
     */
    public List<GoodType> buildGoodTypeTree(List<GoodType> goodTypes);
}
