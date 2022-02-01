package com.liudong.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/payment/current")
    public String payment(){
        return "" + port + System.currentTimeMillis();
    }
}
