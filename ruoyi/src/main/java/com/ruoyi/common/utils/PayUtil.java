package com.ruoyi.common.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.ruoyi.common.config.AlipayConfig;
import com.ruoyi.common.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PayUtil {

    /**
     *
     * @param outTradeNo  商户订单号，商户网站订单系统中唯一订单号，必填   对应缴费记录的orderNo
     * @param totalAmount  付款金额，必填
     * @param subject 主题
     * @param body 商品描述，可空
     * @return
     */
    public static String alipay(String outTradeNo,String totalAmount,String subject,String body) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
//        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);
        try {
            request.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                    + "\"total_amount\":\""+ totalAmount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"timeout_express\":\""+ Constants.TIMEOUT_EXPRESS +"\"}"
//                    + "\"store_id\":\"NJ_002\"}"
//                    + "\"body\":\""+ body +"\"}"
            );
            //请求
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
                String qr_code = response.getQrCode();
                return qr_code;
            } else {
                System.out.println("调用失败");
                return"调用失败";
            }
//            result = alipayClient.pageExecute(request).getBody();
//            AlipayTradePrecreateResponse res = alipayClient.pageExecute(request, "get");
//            result = res.getBody();
//            System.out.println("*********************\n返回结果为："+result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付宝退款接口
     * @param outTradeNo
     * @param tradeNo
     * @param refundAmount
     * @param refundReason
     * @param out_request_no  标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     * @return
     */
    public static String aliRefund(String outTradeNo,String tradeNo,String refundAmount,String refundReason,String out_request_no) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        try {
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                    + "\"trade_no\":\""+ tradeNo +"\","
                    + "\"refund_amount\":\""+ refundAmount +"\","
                    + "\"refund_reason\":\""+ refundReason +"\","
                    + "\"out_request_no\":\""+ out_request_no +"\"}");

            //请求
            String result;

            //请求
            result = alipayClient.execute(alipayRequest).getBody();
            System.out.println("*********************\n返回结果为："+result);
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付宝的验签方法
     * @param req
     * @return
     */
    public static boolean checkSign(HttpServletRequest req) {
        Map<String, String[]> requestMap = req.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for(String value : values) {
                strs = strs + value;
            }
            System.out.println(("key值为"+key+"value为："+strs));
            paramsMap.put(key, strs);
        });

        //调用SDK验证签名
        try {
            return  AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("*********************验签失败********************");
            return false;
        }
    }
}
