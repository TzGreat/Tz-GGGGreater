package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Emp {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String position;
    private double salary;
    private Integer departmentId;
    private String email;
    private LocalDateTime hireDate;
    private LocalDateTime createTime;

    // 在 Emp 类中添加这个构造函数
    public Emp(Integer id, String name, String gender, Integer age, String position,
               Double salary, Integer departmentId, String email,
               LocalDateTime hireDate, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
        this.email = email;
        this.hireDate = hireDate;
        this.createTime = createTime;
    }
}
