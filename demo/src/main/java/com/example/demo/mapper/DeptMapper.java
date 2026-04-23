package com.example.demo.mapper;

import com.example.demo.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全表
    @Select("select * from dept")
    List<Dept> list();

    //插入
    @Insert("insert into dept(name,manager,location, created_time)" +
            "VALUES(#{name},  #{manager}, #{location},  #{createdTime})")
    void insert(Dept dept);

    //修改
    @Update("update dept set name=#{name}, manager=#{manager},location=#{location} where id=#{id}")
    void update(Dept dept);

    //删除
    @Delete("delete from dept where id=#{id} ")
    void deleteById(Integer id);

    //查询
    @Select("select * from dept where id=#{id}")
    Dept selectById(Integer id);
}
