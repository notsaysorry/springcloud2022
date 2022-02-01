package com.liudong.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String get() {
        return "80客户端出现问题";
    }

    @Override
    public String timeout() {
        return "80客户端出现问题";
    }
}
