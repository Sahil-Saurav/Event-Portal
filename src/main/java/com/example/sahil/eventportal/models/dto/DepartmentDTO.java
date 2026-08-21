package com.example.sahil.eventportal.models.dto;

import com.example.sahil.eventportal.models.entity.Department;

public class DepartmentDTO {
    private String deptCode;
    private String deptName;

    public DepartmentDTO() {}

    public DepartmentDTO(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
    }

    public DepartmentDTO(Department dept) {
        this.deptCode = dept.getDeptCode();
        this.deptName = dept.getDeptName();
    }


    public String getDeptCode() {
        return deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
