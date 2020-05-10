package com.ruoyi.common.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021001160672069";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJ8KfIiUvLxwjVvT7yQRPGDzwkTAPvR0yaHuetaTrfP0I20XX22Eo7mk0X9oyX8c0W3sfHTyhSCO8YZntMbp4hcZloWwV5PJOM0yOxx3Ohj/Fnpcq5S4fs0bkIS657orC/UQGETUBhGrR3iu+mGD+H+rW02NNiii4fqyqk61LpoCq1l6nTOANiK/Qr5I+GWpirPSnJvsGlpeZUmraNzerJlsh/3pJAw8HIVYZvs9kxbtgTITiHoeYWhbSKZUXm3ClkJrgIOcCZkuPWxNLaz2nI2qovJ8EkY4LOHN3ME5aKTqt4YoRhVU+hB25chMb1gg8oxygvROyfcxcqL5egzRJlAgMBAAECggEALTB35wyDlkioLk2HN5n2PmZzT3jPuS3kfIEvaPIWZVi1+nt4Z9vHxerw4q7UtFp00888J6FZFjbzDU1Vi16Z018B4Nwg2Oztca8h/HXY/c7ISSpZVYbXCtBSC9OvX7WWrbZ+BrfAJY+4G4mapRFAju2lZY+7AIdAQQO0Pr8RNJPWU+r1MPWUsWeJWDkOZ86E16ydMBYDTYasEIoPbEqsp8uyGcPXMKRN2hsYUgi1zQUaZGounPPJAEIJ7NG76dVXn3gscesNT25IFfxW3WabdF6yUR+TtttG3EnzazzObHwI/0yBeDNQt5xrEV2DOGSROSPO0mFkRL/HKl2FZ2lnwQKBgQDROtP1o9cxDJXxUGcnBcHekDmxOfBDGcr8lltiX2mNuTqaOFEQrFI/8R5fCPdBTzFWxnJqFijj4Q8QUUou9PG8JMsDbJi/dYZhveZSuvueLIGNUhIdGLxdJ57EN5n7jeT5iXbmHuJkrIhmZnV3h5tVpoSaJI08U5h4PqleoVZLmQKBgQCoxkX30RnVIvSgxs8+kZCWWzFj8wtiMowspN9YHJ/sMHplfgVBbNC5KU9mxhbnm7aVn4VxbO/dIDodc8AOV17nMUpw7atdBLfthpXN0lqeDQZCYKCKeeMH5Oo6C7Ve4CX/uxs1RpCrDqq8J0YIXZ27nGJeFTUwWq+4e4ePk3lcrQKBgQCPo5NgT9qsEbfesxQd9hXidaElymDnsA1q2s5h+p68bS2OMpt0ltjgjOgM1lrouzVkHVju+8eWqittSGFlW0OZpqi48RTsXAKll3lH0pZrQKKwImfNEe4/BbQ5gPJ1gDRyG61Ezm8eLlR1+GEsbix14JuoNu+/OqSx6FLeZGSaAQKBgBU89hsMRhrrDiuZOQUfnjk9eoagVANkZv1LNgvJdlprc0uIQzMLdWyHhUVikglNL/m+HDW8xqZl4spIAZgMaJXhPwPc9J589IRAVmOTH/IZo3sA29LxKZ+Zr9vkE0vAGygGLJNUsOnFq7iHnKl61lmAgWRjeND/6HLnoJRxs2j1AoGAWWozglbvGieHnuey2rLqS/hTeYQNZOnvNAd0CCRefpAV6TIij7EtTjpaoqneaN6VIWnrMfm3b70XXrp31NQGX5CHy/wS03Z+0rK0UriwF4b13Kg/Fw8iQe2DCk4R7pa/ok1yyebauTqYfuC6ckBJt/uInBx41nVL373lK1F99UQ=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgYDAzRcXUMZupYeqkVI7UUTJpZfQ/cdIiYCujyTqYdirWuwKwmtI5TAFC3XHWnZmeOfBrKyL1pK1tGuB73wD2I+0qwMXmNSskm6Y5Arj/z/NL2V1qo3SKpINtVSIH+x6ap/bXPOS8ewRufAhuWVrCdKUd9B9kyYmZny92Wrl7O4FAZiM+VJM5mrFYobF0GlRYdWmYJHLbdi5E5mt9v1EbxzJn/zrrTJGn2PpPjU6M5SQZ/R5rVobpZV6hvUA3v3roLAsuu+f7F4wvEyu7Xf2S4Jw+9PJobZY9U893/jobJGCEAfXOBcmtBUvZ60hEtuEF1mlzEuBdMiS6F1IJuslNQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://sanpeike.51vip.biz/dev-api/shopping/return/aliReturnPay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "你的回调页面";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
//    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//沙箱

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
