package com.example.demo.Controller;

import com.example.demo.pojo.Dept;
import com.example.demo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
@CrossOrigin(origins = "*")//解决跨域问题
public class DeptController {
    @Autowired
    private DeptService deptService;


    @GetMapping
    public ResponseEntity<List<Dept>> list() {
        try {
            List<Dept> depts = deptService.list();
            return ResponseEntity.ok(depts);
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Dept dept) {
        try {
            deptService.add(dept);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("创建部门失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Dept dept) {
        try {
            deptService.update(dept);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("更新部门失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            deptService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("删除部门失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dept> selectById(@PathVariable Integer id) {
        try {
            Dept dept = deptService.selectById(id);
            if (dept != null) {
                return ResponseEntity.ok(dept);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("获取部门详情失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

