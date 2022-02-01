package com.liudong.springcloud.dao;

import com.liudong.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment select(@Param("id") Long id);
}
