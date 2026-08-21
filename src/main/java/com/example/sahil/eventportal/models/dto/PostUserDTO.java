package com.example.sahil.eventportal.models.dto;

import com.example.sahil.eventportal.models.entity.Department;

public class PostUserDTO {
    private String name;
    private String email;
    private String password;
    private String rollNumber;
    private Department department;
    private String deptCode;

    public PostUserDTO(String name, String email, String password, String rollNumber, String deptCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rollNumber = rollNumber;
        this.deptCode = deptCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
