package com.liudong.springcloud.controller;

import com.liudong.springcloud.entities.CommonResult;
import com.liudong.springcloud.entities.Payment;
import com.liudong.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private int port;
    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String s: services){
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            for (ServiceInstance si: instances){
                System.out.println(si.getInstanceId() + "\t" + si.getHost() + "\t"
                + si.getPort() + "\t" + si.getUri());
            }
        }
        return discoveryClient;
    }

    @GetMapping("/payment/port")
    public String getPort(){
        return port + "";
    }

    @GetMapping("/payment/timeout")
    public CommonResult<String> timeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<>(200, "ok", port + "");
    }

    @GetMapping("/payment/test2")
    public CommonResult<String> test2(){
        return new CommonResult<>(200, "ok", port + "");
    }
    @GetMapping("/payment/test1")
    public CommonResult<String> test1(){
        return new CommonResult<>(200, "ok", port + "");
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
