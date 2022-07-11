package com.maple.springcloud.dao;

import com.maple.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Package_Name: com.maple.springcloud.dao
 * @Auth: Maple
 * @Date: 2022/7/11 14:37
 **/
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
