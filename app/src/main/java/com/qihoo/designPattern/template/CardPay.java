package com.qihoo.designPattern.template;

public class CardPay extends OrderMethod{
    @Override
    public boolean pay() {
        System.out.println("信用卡支付");
        return true;
    }
}
