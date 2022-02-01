package com.liudong.springcloud.controller;

import com.liudong.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "timeoutGlobalHandle")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consume/payment/get")
    public String get(){
        return paymentService.get();
    }

    @GetMapping("/consume/payment/timeout")
//    @HystrixCommand(fallbackMethod = "timeoutHandle", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="1000")
//    })
    @HystrixCommand
    public String timeout(){
        return paymentService.timeout();
    }

    public String timeoutHandle(){return "80服务超时或出现错误，请稍后再试";}

    public String timeoutGlobalHandle(){return "全局80服务超时或出现错误，请稍后再试";}
}
