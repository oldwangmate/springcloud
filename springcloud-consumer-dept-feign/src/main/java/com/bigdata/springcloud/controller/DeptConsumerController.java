package com.bigdata.springcloud.controller;

import com.bigdata.springcloud.pojo.Dept;
import com.bigdata.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 调用微服务的两种方式
 * 1.微服务名称 ribbon
 * 2.接口和注解 feign
 */
@RestController
public class DeptConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
       return this.deptClientService.queryById(id);
    }

    @RequestMapping("/consumer/dept/get/add")
    public Integer add(Dept dept){
      return this.deptClientService.addDept(dept);
    }


    @RequestMapping("/consumer/dept/get/list")
    public List<Dept> list(){
        return this.deptClientService.queryAll();
    }

}
