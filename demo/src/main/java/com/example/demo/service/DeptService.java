package com.example.demo.service;

import com.example.demo.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();
    void add(Dept dept);
    void update(Dept dept);
    void delete(Integer id);
    Dept selectById(Integer id);
}
