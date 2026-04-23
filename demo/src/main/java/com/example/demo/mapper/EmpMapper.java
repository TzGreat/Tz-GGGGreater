package com.example.demo.mapper;

import com.example.demo.pojo.Emp;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("SELECT * FROM emp")
    List<Emp> list();

    @Insert("INSERT INTO emp(name, gender, age, position, salary, department_id, email, hire_date, create_time) " +
            "VALUES(#{name}, #{gender}, #{age}, #{position}, #{salary}, #{departmentId}, #{email}, #{hireDate}, #{createTime})")
    void insert(Emp emp);

    @Update("UPDATE emp SET name=#{name}, gender=#{gender}, age=#{age}, position=#{position}, salary=#{salary}, " +
            "department_id=#{departmentId}, email=#{email}, hire_date=#{hireDate} WHERE id=#{id}")
    void update(Emp emp);

    @Delete("DELETE FROM emp WHERE id=#{id}")
    void delete(Integer id);

    @Select("SELECT * FROM emp WHERE id=#{id}")
    Emp selectById(Integer id);
}
