package com.example.sahil.eventportal.repository;

import com.example.sahil.eventportal.models.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByDeptCode(String deptCode);
}
