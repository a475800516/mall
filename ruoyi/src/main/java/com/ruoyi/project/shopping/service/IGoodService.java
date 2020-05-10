package com.ruoyi.project.shopping.service;

import com.ruoyi.project.shopping.domain.Good;

import java.util.List;

/**
 * 商品Service接口
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
public interface IGoodService 
{
    /**
     * 查询商品
     * 
     * @param goodId 商品ID
     * @return 商品
     */
    public Good selectGoodById(Long goodId);

    /**
     * 查询商品列表
     * 
     * @param good 商品
     * @return 商品集合
     */
    public List<Good> selectGoodList(Good good);

    /**
     * 新增商品
     * 
     * @param good 商品
     * @return 结果
     */
    public int insertGood(Good good);

    /**
     * 修改商品
     * 
     * @param good 商品
     * @return 结果
     */
    public int updateGood(Good good);

    /**
     * 批量删除商品
     * 
     * @param goodIds 需要删除的商品ID
     * @return 结果
     */
    public int deleteGoodByIds(Long[] goodIds);

    /**
     * 删除商品信息
     * 
     * @param goodId 商品ID
     * @return 结果
     */
    public int deleteGoodById(Long goodId);
}
