package com.example.sahil.eventportal.models.dto.requestDto;

import com.example.sahil.eventportal.Enumerated.RolesEnum;

public class PostAssignRoleDTO {
    private RolesEnum role;
    private String email;

    public PostAssignRoleDTO() {}

    public PostAssignRoleDTO(RolesEnum role, String email) {
        this.role = role;
        this.email = email;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolesEnum getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}
