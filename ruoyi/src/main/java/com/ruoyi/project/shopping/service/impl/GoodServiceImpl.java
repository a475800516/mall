package com.ruoyi.project.shopping.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.shopping.domain.Good;
import com.ruoyi.project.shopping.mapper.GoodMapper;
import com.ruoyi.project.shopping.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@Service
public class GoodServiceImpl implements IGoodService
{
    @Autowired
    private GoodMapper goodMapper;

    /**
     * 查询商品
     * 
     * @param goodId 商品ID
     * @return 商品
     */
    @Override
    public Good selectGoodById(Long goodId)
    {
        return goodMapper.selectGoodById(goodId);
    }

    /**
     * 查询商品列表
     * 
     * @param good 商品
     * @return 商品
     */
    @Override
    public List<Good> selectGoodList(Good good)
    {
        return goodMapper.selectGoodList(good);
    }

    /**
     * 新增商品
     * 
     * @param good 商品
     * @return 结果
     */
    @Override
    public int insertGood(Good good)
    {
        good.setCreateTime(DateUtils.getNowDate());
        return goodMapper.insertGood(good);
    }

    /**
     * 修改商品
     * 
     * @param good 商品
     * @return 结果
     */
    @Override
    public int updateGood(Good good)
    {
        good.setUpdateTime(DateUtils.getNowDate());
        return goodMapper.updateGood(good);
    }

    /**
     * 批量删除商品
     * 
     * @param goodIds 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteGoodByIds(Long[] goodIds)
    {
        return goodMapper.deleteGoodByIds(goodIds);
    }

    /**
     * 删除商品信息
     * 
     * @param goodId 商品ID
     * @return 结果
     */
    @Override
    public int deleteGoodById(Long goodId)
    {
        return goodMapper.deleteGoodById(goodId);
    }
}
