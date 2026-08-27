package com.example.sahil.eventportal.models.dto.responseDto;

import com.example.sahil.eventportal.models.entity.Department;
import java.util.Set;
import java.util.stream.Collectors;

public class DeptUsersDTO {
    private String deptCode;
    private String deptName;
    private Set<UserDTO> users;

    public DeptUsersDTO() {}

    public DeptUsersDTO(Department dept) {
        this.deptCode = dept.getDeptCode();
        this.deptName = dept.getDeptName();
        this.users = dept.getUsers()
                .stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toSet());
    }

    public String getDeptCode() {
        return deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }
}
