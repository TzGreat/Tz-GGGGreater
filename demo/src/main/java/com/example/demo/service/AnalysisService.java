package com.example.demo.service;

import com.example.demo.pojo.Analysis;

import java.util.List;

public interface AnalysisService {
    List<Analysis> list();
    Analysis findById(Integer deptId);
}
