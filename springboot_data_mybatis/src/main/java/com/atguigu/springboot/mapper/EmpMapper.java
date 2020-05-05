package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;

public interface EmpMapper {
    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
