package com.example.demo.Controller;

import com.example.demo.pojo.Analysis;
import com.example.demo.service.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins = "*")//解决跨域问题
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @GetMapping
    public ResponseEntity<List<Analysis>> list(){
        try{
            List<Analysis> list = analysisService.list();
            return ResponseEntity.ok(list);
        }catch(Exception e){
            log.error("获取失败",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<Analysis> findById(@PathVariable Integer deptId){
        try{
            Analysis analysis=analysisService.findById(deptId);
            if(analysis!=null){
                return ResponseEntity.ok(analysis);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            log.error("查询失败",e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
