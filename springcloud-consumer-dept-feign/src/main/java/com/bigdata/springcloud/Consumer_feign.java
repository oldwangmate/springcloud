package com.bigdata.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.bigdata.springcloud")
@ComponentScan("com.bigdata.springcloud")
public class Consumer_feign {

    public static void main(String[] args) {
        SpringApplication.run(Consumer_feign.class,args);
    }
}
