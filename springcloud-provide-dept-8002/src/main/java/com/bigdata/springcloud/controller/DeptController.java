package com.bigdata.springcloud.controller;

import com.bigdata.springcloud.pojo.Dept;
import com.bigdata.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired //获取配置信息
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public Integer addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.queryDeptById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> addDept(){
        return deptService.QueryAll();
    }


    //注册进来的微服务，获取一些信息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> services = client.getServices();
        System.out.println(services);

        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost()+"\t");
            System.out.println(instance.getPort()+"\t");
            System.out.println(instance.getUri()+"\t");
            System.out.println(instance.getServiceId()+"\t");
        }
        return this.client;
    }
}
