package com.ruoyi.project.shopping.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.shopping.domain.GoodType;
import com.ruoyi.project.shopping.mapper.GoodTypeMapper;
import com.ruoyi.project.shopping.service.IGoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类型信息 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class GoodTypeServiceImpl implements IGoodTypeService
{
    @Autowired
    private GoodTypeMapper goodTypeMapper;

//    @Autowired
//    private SysUserPostMapper userPostMapper;

    /**
     * 查询商品类型信息集合
     * 
     * @param goodType 商品类型信息
     * @return 商品类型信息集合
     */
    @Override
    public List<GoodType> selectGoodTypeList(GoodType goodType)
    {
        return goodTypeMapper.selectGoodTypeList(goodType);
    }

    /**
     * 查询所有商品类型
     * 
     * @return 商品类型列表
     */
    @Override
    public List<GoodType> selectGoodTypeAll()
    {
        return goodTypeMapper.selectGoodTypeAll();
    }

    /**
     * 通过商品类型ID查询商品类型信息
     * 
     * @param goodTypeId 商品类型ID
     * @return 角色对象信息
     */
    @Override
    public GoodType selectGoodTypeById(Long goodTypeId)
    {
        return goodTypeMapper.selectGoodTypeById(goodTypeId);
    }

    /**
     * 删除商品类型信息
     * 
     * @param goodTypeId 商品类型ID
     * @return 结果
     */
    @Override
    public int deleteGoodTypeById(Long goodTypeId)
    {
        return goodTypeMapper.deleteGoodTypeById(goodTypeId);
    }

    /**
     * 批量删除商品类型信息
     * 
     * @param goodTypeIds 需要删除的商品类型ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteGoodTypeByIds(Long[] goodTypeIds)
    {
//        for (Long goodTypeId : goodTypeIds)
//        {
//            GoodType goodType = selectGoodTypeById(goodTypeId);
//            if (countUserPostById(goodTypeId) > 0)
//            {
//                throw new CustomException(String.format("%1$s已分配,不能删除", goodType.getPostName()));
//            }
//        }
        return goodTypeMapper.deleteGoodTypeByIds(goodTypeIds);
    }

    /**
     * 新增保存商品类型信息
     * 
     * @param goodType 商品类型信息
     * @return 结果
     */
    @Override
    public int insertGoodType(GoodType goodType)
    {
        return goodTypeMapper.insertGoodType(goodType);
    }

    /**
     * 修改保存商品类型信息
     * 
     * @param goodType 商品类型信息
     * @return 结果
     */
    @Override
    public int updateGoodType(GoodType goodType)
    {
        return goodTypeMapper.updateGoodType(goodType);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param goodTypes 商品类型列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildGoodTypeTreeSelect(List<GoodType> goodTypes)
    {
        List<GoodType> goodTypeTrees = buildGoodTypeTree(goodTypes);
        return goodTypeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 构建前端所需要树结构
     *
     * @param goodTypes 商品类型列表
     * @return 树结构列表
     */
    @Override
    public List<GoodType> buildGoodTypeTree(List<GoodType> goodTypes)
    {
        List<GoodType> returnList = new ArrayList<GoodType>();
        List<Long> tempList = new ArrayList<Long>();
        for (GoodType goodType : goodTypes)
        {
            tempList.add(goodType.getGoodTypeId());
        }
        for (Iterator<GoodType> iterator = goodTypes.iterator(); iterator.hasNext();)
        {
            GoodType goodType = (GoodType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(goodType.getParentId()))
            {
                recursionFn(goodTypes, goodType);
                returnList.add(goodType);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = goodTypes;
        }
        return returnList;
    }


    /**
     * 递归列表
     */
    private void recursionFn(List<GoodType> list, GoodType t)
    {
        // 得到子节点列表
        List<GoodType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (GoodType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<GoodType> it = childList.iterator();
                while (it.hasNext())
                {
                    GoodType n = (GoodType) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<GoodType> getChildList(List<GoodType> list, GoodType t)
    {
        List<GoodType> tlist = new ArrayList<GoodType>();
        Iterator<GoodType> it = list.iterator();
        while (it.hasNext())
        {
            GoodType n = (GoodType) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getGoodTypeId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<GoodType> list, GoodType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
