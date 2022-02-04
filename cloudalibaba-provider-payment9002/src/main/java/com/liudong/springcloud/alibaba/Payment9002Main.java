package com.liudong.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Payment9002Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment9002Main.class, args);
    }
}
