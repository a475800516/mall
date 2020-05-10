package com.ruoyi.project.shopping.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.shopping.domain.Orders;
import com.ruoyi.project.shopping.po.MyOrders;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
public interface IOrdersService
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Orders selectOrdersById(Long ordersId);

    /**
     * 下单
     * @param goodId
     * @return
     */
    public AjaxResult placeAnOrder(Long goodId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param orders 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param orders 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MyOrders> selectMyOrdersList(Orders orders);

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
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] ordersIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteOrdersById(Long ordersId);
}
