package com.liudong.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamMQ8801Main {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQ8801Main.class, args);
    }
}
