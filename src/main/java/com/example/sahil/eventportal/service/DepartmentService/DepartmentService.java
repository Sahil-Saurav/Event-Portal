package com.example.sahil.eventportal.service.DepartmentService;

import com.example.sahil.eventportal.models.dto.DepartmentDTO;
import com.example.sahil.eventportal.models.dto.EventDTO;
import com.example.sahil.eventportal.models.dto.UserDTO;
import com.example.sahil.eventportal.models.entity.Department;
import com.example.sahil.eventportal.models.entity.User;

import java.util.List;

public interface DepartmentService {
    void addDepartment(DepartmentDTO department);
    Department getDepartmentByDeptCode(String deptCode);
    List<Department> getAllDepartments();
    List<UserDTO> getAllUsersInDepartment(Department department);
    List<EventDTO> getAllEventsInDepartment(Department department);
}
