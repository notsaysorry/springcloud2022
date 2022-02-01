package com.liudong.springcloud.service.impl;

import com.liudong.springcloud.dao.PaymentDao;
import com.liudong.springcloud.entities.Payment;
import com.liudong.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment select(Long id) {
        return paymentDao.select(id);
    }
}
