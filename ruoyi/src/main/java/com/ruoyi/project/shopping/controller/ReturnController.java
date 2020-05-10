package com.ruoyi.project.shopping.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.PayUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.shopping.domain.AliReturnPay;
import com.ruoyi.project.shopping.domain.Orders;
import com.ruoyi.project.shopping.service.IOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/shopping/return")
public class ReturnController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ReturnController.class);
    @Autowired
    private IOrdersService ordersService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    /**
     * 支付宝回调的接口
     * @param response
     * @param returnPay
     * @param req
     * @throws IOException
     */
    @RequestMapping(value = "/aliReturnPay", method = RequestMethod.POST)
    public void returnPay(HttpServletResponse response, AliReturnPay returnPay, HttpServletRequest req)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        logger.info("****************************************支付宝的的回调函数被调用******************************");
        if (!PayUtil.checkSign(req)) {
            logger.info("****************************************验签失败*******************************************");
            response.getWriter().write("failture");
            return;
        }
        if (returnPay == null) {
            logger.info("支付宝的returnPay返回为空");
            response.getWriter().write("success");
            return;
        }
        logger.info("支付宝的returnPay" + returnPay.toString());
        if (returnPay.getTrade_status().equals("TRADE_SUCCESS")) {
            logger.info("支付宝的支付状态为TRADE_SUCCESS");
            //更新订单支付状态
            String ordersId = returnPay.getOut_trade_no();
            Orders orders = new Orders();
            orders.setOrdersId(ordersId);
            orders.setStatus(Constants.ORDER_STATUS_UNDELIVERY);
            ordersService.updateOrders(orders);
        }
        response.getWriter().write("success");
    }
}
