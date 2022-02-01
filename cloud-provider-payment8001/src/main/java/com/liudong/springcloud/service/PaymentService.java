package com.liudong.springcloud.service;

import com.liudong.springcloud.entities.Payment;


public interface PaymentService {

    int create(Payment payment);

    Payment select(Long id);
}
