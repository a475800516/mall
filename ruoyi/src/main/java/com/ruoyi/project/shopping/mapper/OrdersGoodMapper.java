package com.ruoyi.project.shopping.mapper;

import com.ruoyi.project.shopping.domain.OrdersGood;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
public interface OrdersGoodMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public OrdersGood selectOrdersGoodById(Long ordersId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ordersGood 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<OrdersGood> selectOrdersGoodList(OrdersGood ordersGood);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ordersGood 【请填写功能名称】
     * @return 结果
     */
    public int insertOrdersGood(OrdersGood ordersGood);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ordersGood 【请填写功能名称】
     * @return 结果
     */
    public int updateOrdersGood(OrdersGood ordersGood);

    /**
     * 删除【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteOrdersGoodById(Long orderId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrdersGoodByIds(Long[] orderIds);
}
