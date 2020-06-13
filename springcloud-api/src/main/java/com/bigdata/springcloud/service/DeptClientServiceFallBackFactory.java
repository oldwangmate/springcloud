package com.bigdata.springcloud.service;

import com.bigdata.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//服务降级
@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("没有对应的信息 服务关闭");
                return dept;
            }

            @Override
            public List<Dept> queryAll() {
                List<Dept> dept = new ArrayList<>();
                Dept de = new Dept();
                de.setDeptno(0L);
                de.setDname("没有对应的信息 服务关闭");
                dept.add(de);
                return dept;
            }

            @Override
            public Integer addDept(Dept dept) {
               return 0;
            }
        };
    }
}
