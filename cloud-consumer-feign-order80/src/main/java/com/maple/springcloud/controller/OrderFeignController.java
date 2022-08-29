package com.maple.springcloud.controller;

import com.maple.springcloud.entities.CommonResult;
import com.maple.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package_Name: com.maple.springcloud.controller
 * @Auth: Maple
 * @Date: 2022/8/24 17:02
 **/
@RestController
@RequestMapping("consumer")
@Slf4j
public class OrderFeignController {

    private final PaymentFeignService paymentFeignService;

    public OrderFeignController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/get")
    public CommonResult getPaymentById(@RequestParam Long id) {
        return paymentFeignService.getPaymentById(id);
    }


}
