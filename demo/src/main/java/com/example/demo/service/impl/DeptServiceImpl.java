package com.example.demo.service.impl;

import com.example.demo.mapper.DeptMapper;
import com.example.demo.pojo.Dept;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

    @Override
    public void add(Dept dept) {
        log.info("创建部门: {}", dept);
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        log.info("更新部门: {}", dept);
        deptMapper.update(dept);
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }
}

