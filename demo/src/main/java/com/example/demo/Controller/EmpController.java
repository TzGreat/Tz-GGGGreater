package com.example.demo.Controller;

import com.example.demo.pojo.Emp;
import com.example.demo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
@CrossOrigin(origins = "*")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public ResponseEntity<List<Emp>> list() {
        try {
            List<Emp> emps = empService.list();
            return ResponseEntity.ok(emps);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Emp emp) {
        try {
            empService.add(emp);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            empService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> findById(@PathVariable Integer id) {
        try {
            Emp emp = empService.selectById(id);
            if (emp != null) {
                return ResponseEntity.ok(emp);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Emp emp) {
        try {
            System.out.println("接收到员工更新数据: " + emp);
            System.out.println("departmentId: " + emp.getDepartmentId());
            System.out.println("hireDate: " + emp.getHireDate());

            empService.update(emp);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("更新员工失败: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}