package com.maple.springcloud.controller;

import com.maple.springcloud.entities.CommonResult;
import com.maple.springcloud.entities.Payment;
import com.maple.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService, DiscoveryClient discoveryClient) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("订单插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "插入数据库失败,serverPort:" + serverPort, null);
        }
    }

    @GetMapping("/get")
    public CommonResult getPaymentById(@RequestParam Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("订单查询结果：" + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询数据库成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult<>(400, "没有对应记录,serverPort:" + serverPort, id);
        }
    }

    @GetMapping("/discovery")
    public  Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("-----------service:" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {

            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

}
