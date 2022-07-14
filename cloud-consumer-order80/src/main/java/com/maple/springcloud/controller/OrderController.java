package com.maple.springcloud.controller;

import com.maple.springcloud.entities.CommonResult;
import com.maple.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Package_Name: com.maple.springcloud.controller
 * @Auth: Maple
 * @Date: 2022/7/12 9:46
 **/
@RestController
@RequestMapping("consumer")
@Slf4j
public class OrderController {

    private final RestTemplate restTemplate;

    // 单机时可以写死地址
    //public static final String PAYMENT_URL = "http://localhost:8001";

    //集群时使用下面的地址 使用微服务名称
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",
                payment, CommonResult.class);
    }

    @GetMapping("/payment/get")
    public CommonResult<Payment> getPayment(@RequestParam Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get?id=" + id, CommonResult.class);
    }

}
