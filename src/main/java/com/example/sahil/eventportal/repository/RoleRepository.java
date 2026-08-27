package com.example.sahil.eventportal.repository;

import com.example.sahil.eventportal.models.entity.Role;
import com.example.sahil.eventportal.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String role);
}
