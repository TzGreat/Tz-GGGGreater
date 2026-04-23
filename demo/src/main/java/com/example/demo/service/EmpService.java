package com.example.demo.service;

import com.example.demo.pojo.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> list();
    void add(Emp emp);
    void update(Emp emp);
    void delete(Integer id);
    Emp selectById(Integer id);
}
