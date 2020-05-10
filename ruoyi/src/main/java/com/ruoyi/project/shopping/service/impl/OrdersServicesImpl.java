package com.ruoyi.project.shopping.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PayUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.shopping.domain.Good;
import com.ruoyi.project.shopping.domain.OrdersGood;
import com.ruoyi.project.shopping.domain.Orders;
import com.ruoyi.project.shopping.mapper.GoodMapper;
import com.ruoyi.project.shopping.mapper.OrdersGoodMapper;
import com.ruoyi.project.shopping.mapper.OrdersMapper;
import com.ruoyi.project.shopping.po.MyOrders;
import com.ruoyi.project.shopping.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-27
 */
@Service
public class OrdersServicesImpl implements IOrdersService
{
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private OrdersGoodMapper ordersGoodMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Orders selectOrdersById(Long ordersId)
    {
        return ordersMapper.selectOrdersById(ordersId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param orders 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders) {
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param orders 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MyOrders> selectMyOrdersList(Orders orders) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return ordersMapper.selectMyOrdersList(userId);
    }

    /**
     * 下单
     * @param goodId
     * @return
     */
    @Transactional
    public AjaxResult placeAnOrder(Long goodId){
        //获取用户ID
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        String userName = SecurityUtils.getUsername();
        //查询商品信息
        Good good = goodMapper.selectGoodById(goodId);
        BigDecimal price = good.getPrice();
        String goodName = good.getGoodName();
        String ordersId = DateUtils.getOrderId();
        //调用支付宝预支付
        String qrCode = PayUtil.alipay(ordersId, price.toString(), goodName, null);
        //保存订单信息
        Orders orders = new Orders();
        orders.setOrdersId(ordersId);
        orders.setPriceSum(price);
        orders.setStatus(Constants.ORDER_STATUS_UNPAY);
        orders.setPayQrCode(qrCode);
        orders.setFreight(new BigDecimal(0.00));//默认没有运费
        orders.setBuyerId(userId);
        orders.setCreateBy(userName);
        ordersMapper.insertOrders(orders);
        //新增订单与商品的关联信息
        OrdersGood ordersGood = new OrdersGood();
        ordersGood.setOrdersId(ordersId);
        ordersGood.setGoodId(goodId);
        ordersGood.setNum(1);
        ordersGoodMapper.insertOrdersGood(ordersGood);
        return AjaxResult.success(qrCode);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param orders 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders)
    {
        orders.setCreateTime(DateUtils.getNowDate());
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param orders 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateOrders(Orders orders)
    {
        orders.setUpdateTime(DateUtils.getNowDate());
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ordersIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteOrdersByIds(Long[] ordersIds)
    {
        return ordersMapper.deleteOrdersByIds(ordersIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param ordersId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteOrdersById(Long ordersId)
    {
        return ordersMapper.deleteOrdersById(ordersId);
    }

}
