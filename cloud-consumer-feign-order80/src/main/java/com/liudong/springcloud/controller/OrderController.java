package com.liudong.springcloud.controller;

import com.liudong.springcloud.entities.CommonResult;
import com.liudong.springcloud.entities.Payment;
import com.liudong.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/consume/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        return orderService.get(id);
    }

    @GetMapping("/consume/payment/timeout")
    public CommonResult<String> timeout(){
        return orderService.timeout();
    }
}
