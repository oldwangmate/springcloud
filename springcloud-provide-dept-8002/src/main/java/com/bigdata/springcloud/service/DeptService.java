package com.bigdata.springcloud.service;

import com.bigdata.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {

    public Integer addDept(Dept dept);

    public Dept queryDeptById(Long id);

    public List<Dept> QueryAll();
}
