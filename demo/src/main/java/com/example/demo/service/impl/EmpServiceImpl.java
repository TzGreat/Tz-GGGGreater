package com.example.demo.service.impl;

import com.example.demo.mapper.EmpMapper;
import com.example.demo.pojo.Emp;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> list() {
        return empMapper.list();
    }

    @Override
    public void add(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public void delete(Integer id) {
        empMapper.delete(id);
    }

    @Override
    public Emp selectById(Integer id) {
        return empMapper.selectById(id);
    }
}
