package com.quxiangtech.designPattern.template;

public class WeixinPay extends OrderMethod{
    @Override
    public boolean pay() {
        System.out.println("微信支付成功");
        return true;
    }
}
