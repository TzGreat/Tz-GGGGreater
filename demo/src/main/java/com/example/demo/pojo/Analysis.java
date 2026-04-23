package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 部门薪资分析POJO
 * 对应视图 analysis
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Analysis {
    private Integer deptId;
    private String deptName;
    private String manager;
    private String location;

    // 基础统计信息
    private Integer employeeCount;
    private BigDecimal avgSalary;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;
    private BigDecimal totalSalary;

    // 薪资分布统计
    private Integer lowSalaryCount;           // 低薪资范围员工数 (<5000)
    private Integer mediumSalaryCount;        // 中等薪资范围员工数 (5000-9999)
    private Integer highSalaryCount;          // 高薪资范围员工数 (10000-19999)
    private Integer veryHighSalaryCount;      // 极高薪资范围员工数 (>=20000)

    /**
     * 获取薪资分布比例 - 低薪资比例
     */
    public Double getLowSalaryRatio() {
        if (employeeCount == null || employeeCount == 0) {
            return 0.0;
        }
        return (double) lowSalaryCount / employeeCount * 100;
    }

    /**
     * 获取薪资分布比例 - 中等薪资比例
     */
    public Double getMediumSalaryRatio() {
        if (employeeCount == null || employeeCount == 0) {
            return 0.0;
        }
        return (double) mediumSalaryCount / employeeCount * 100;
    }

    /**
     * 获取薪资分布比例 - 高薪资比例
     */
    public Double getHighSalaryRatio() {
        if (employeeCount == null || employeeCount == 0) {
            return 0.0;
        }
        return (double) highSalaryCount / employeeCount * 100;
    }

    /**
     * 获取薪资分布比例 - 极高薪资比例
     */
    public Double getVeryHighSalaryRatio() {
        if (employeeCount == null || employeeCount == 0) {
            return 0.0;
        }
        return (double) veryHighSalaryCount / employeeCount * 100;
    }
}