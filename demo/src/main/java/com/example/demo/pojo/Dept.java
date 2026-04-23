package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dept {
    private int id;
    private String name;
    private  String manager;
    private String location;
    private LocalDateTime createdTime;
}
