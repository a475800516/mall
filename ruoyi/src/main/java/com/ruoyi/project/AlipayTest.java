package com.ruoyi.project;

import com.ruoyi.common.utils.PayUtil;

public class AlipayTest {
    public static void main(String[] args) {

        String str = PayUtil.alipay("20200507102401008", "0.01", "hehetest12", "hahatest2");
        System.out.println(str);
    }
}
