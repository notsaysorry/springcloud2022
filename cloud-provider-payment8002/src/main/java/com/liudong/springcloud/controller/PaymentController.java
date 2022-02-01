package com.liudong.springcloud.controller;

import com.liudong.springcloud.entities.CommonResult;
import com.liudong.springcloud.entities.Payment;
import com.liudong.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private int port;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult<>(200, "插入成功, port = " + port,result);
        }else {
            return new CommonResult<>(200, "插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.select(id);
        if(payment != null){
            return new CommonResult<>(200, "查询成功, port = " + port, payment);
        }else {
            return new CommonResult<>(200, "查询失败",null);
        }
    }


    @GetMapping("/payment/port")
    public String getPort(){
        return port + "";
    }

}
