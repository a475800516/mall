package com.ruoyi.project.shopping.mapper;

import com.ruoyi.project.shopping.domain.Orders;
import com.ruoyi.project.shopping.po.MyOrders;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
public interface OrdersMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Orders selectOrdersById(Long ordersId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param orders
     * @return 【请填写功能名称】集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userId
     * @return 【请填写功能名称】集合
     */
    public List<MyOrders> selectMyOrdersList(Long userId);

    /**
     * 新增【请填写功能名称】
     * 
     * @param orders 【请填写功能名称】
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改【请填写功能名称】
     * 
     * @param orders 【请填写功能名称】
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 删除【请填写功能名称】
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteOrdersById(Long ordersId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ordersIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] ordersIds);
}
