package com.liudong.springcloud.service;

import com.liudong.springcloud.entities.CommonResult;
import com.liudong.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface OrderService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> get(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    CommonResult<String> timeout();

}
