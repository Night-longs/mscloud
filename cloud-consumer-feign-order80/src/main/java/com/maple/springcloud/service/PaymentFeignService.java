package com.maple.springcloud.service;

import com.maple.springcloud.entities.CommonResult;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
@Component
public interface PaymentFeignService {

    @GetMapping("/payment/get")
    public CommonResult getPaymentById(@RequestParam("id")  Long id);
}
