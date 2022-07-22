package com.maple.springcloud.controller;

import com.maple.springcloud.entities.CommonResult;
import com.maple.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Payment> requestEntity = new HttpEntity(payment, requestHeaders);
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",
                requestEntity, CommonResult.class);
    }

    @GetMapping("/payment/get")
    public CommonResult<Payment> getPayment(@RequestParam Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get?id=" + id, CommonResult.class);
    }

    @PostMapping("/payment/createEntity")
    public CommonResult<Payment> createForEntity(@RequestBody Payment payment) {
        //使用RestTemplate调用其他服务接口时要设置请求头，不然会报415请求参数格式不匹配
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Payment> requestEntity = new HttpEntity(payment, requestHeaders);
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", requestEntity, CommonResult.class);
        if (commonResultResponseEntity.getStatusCode().is2xxSuccessful()) {
            return new CommonResult(200, "操作成功");
        } else {
            return new CommonResult(200, "操作失败");

        }
    }


    @GetMapping("/payment/getEntity")
    public CommonResult<Payment> getPaymentEntity(@RequestParam Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get?id=" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult(444, "操作失败");
        }

    }


}
