package com.example.sahil.eventportal.models.dto;

import com.example.sahil.eventportal.models.entity.User;

import java.sql.Timestamp;

public class UserDetailsDTO {
    private String name;
    private String email;
    private String rollNumber;
    private Timestamp createdAt;
    private DepartmentDTO department;

    public UserDetailsDTO(User user) {
        this.name = user.getName();
        this.rollNumber = user.getRollNumber();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.department = user.getDepartment() != null ? new DepartmentDTO(user.getDepartment()) : null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }
}
