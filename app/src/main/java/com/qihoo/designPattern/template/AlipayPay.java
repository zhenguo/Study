package com.qihoo.designPattern.template;

public class AlipayPay extends OrderMethod{
    @Override
    public boolean pay() {
        System.out.println("支付宝支付成功");
        return true;
    }
}
