package com.example.demo.mapper;

import com.example.demo.pojo.Analysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnalysisMapper {
    //查询全部信息
    @Select("select * from analysis")
    List<Analysis> list();

    //查看特定部门信息
    @Select("select * from analysis where deptId=#{deptId}")
    Analysis findById(Integer deptId);
}
