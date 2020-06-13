package com.bigdata.springcloud;

import com.bigdata.rule.CustomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = CustomRule.class)  //在微服务启动的时候加载自定义的Ribbon类
@EnableZuulProxy
public class Consumer_80 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer_80.class,args);
    }
}
