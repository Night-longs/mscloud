package com.maple.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package_Name: com.maple.springcloud.config
 * @Auth: Maple
 * @Date: 2022/7/12 9:56
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced                  //该注解赋予RestTemplate负载均衡的能力，支持通过服务名称（可以接卸到指定的某一台）来调用服务
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
