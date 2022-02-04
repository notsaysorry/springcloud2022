package com.liudong.springcloud.controller;

import com.liudong.springcloud.entities.CommonResult;
import com.liudong.springcloud.entities.Payment;
import com.liudong.springcloud.lb.RoundLoadBanlancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RoundLoadBanlancer roundLoadBanlancer;

    //private static final String PAYMENT_URL = "http://localhost:8001/";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consume/payment/create")
    public CommonResult<Integer> create(Payment payment){
       return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consume/payment/createEntity")
    public CommonResult<Integer> createEntity(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(400, "添加失败");
        }

    }

    @GetMapping("/consume/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consume/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(400, "查询失败");
        }
    }

    @GetMapping("/consume/payment/port")
    public CommonResult<String> getPort(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = roundLoadBanlancer.getInstance(instances);
        URI uri = instance.getUri();
        ResponseEntity<String> entity = restTemplate.getForEntity(uri + "/payment/port", String.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return new CommonResult<>(200, "成功", entity.getBody());
        }else {
            return new CommonResult<>(400, "查询失败");
        }
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}


