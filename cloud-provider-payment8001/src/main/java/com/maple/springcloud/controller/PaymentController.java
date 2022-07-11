package com.maple.springcloud.controller;

import com.maple.springcloud.entities.CommonResult;
import com.maple.springcloud.entities.Payment;
import com.maple.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(Payment payment) {

        int result = paymentService.create(payment);
        log.info("订单插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功", payment);
        }else {
            return new CommonResult<>(400, "插入数据库失败", null);
        }
    }

    @GetMapping("/get")
    public CommonResult getPaymentById(@RequestParam Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("订单查询结果：" + payment);

        if (payment != null) {
            return new CommonResult<>(200, "查询数据库成功", payment);
        }else {
            return new CommonResult<>(400, "没有对应记录", id);
        }

    }





}
