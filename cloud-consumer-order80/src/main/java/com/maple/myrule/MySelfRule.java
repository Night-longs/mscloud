package com.maple.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package_Name: com.maple.myrule
 * @Auth: Maple
 * @Date: 2022/7/22 14:22
 **/
@Configuration
public class MySelfRule {
    @Bean
    public IRule getRule() {
        return new RandomRule();

    }
}
