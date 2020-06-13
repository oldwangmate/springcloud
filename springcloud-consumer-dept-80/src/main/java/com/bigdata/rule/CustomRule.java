package com.bigdata.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRule {

    @Bean
    public IRule iRule(){
        return new CustomRandomRule();
    }
}
