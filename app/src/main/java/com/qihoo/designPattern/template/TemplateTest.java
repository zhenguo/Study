package com.qihoo.designPattern.template;

public class TemplateTest {
    public static void main(String[] args) {
        OrderMethod orderMethod = new AlipayPay();
        orderMethod.pay();
    }
}
