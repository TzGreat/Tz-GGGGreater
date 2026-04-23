package com.example.demo.service.impl;

import com.example.demo.mapper.AnalysisMapper;
import com.example.demo.pojo.Analysis;
import com.example.demo.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<Analysis> list() {
        return analysisMapper.list();
    }

    @Override
    public Analysis findById(Integer deptId) {
        return analysisMapper.findById(deptId);
    }
}
