package com.bigdata.springcloud.dao;

import com.bigdata.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {

     public Integer addDept(Dept dept);

     public Dept queryDeptById(Long id);

     public List<Dept> queryAll();
}
