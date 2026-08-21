package com.example.sahil.eventportal.controllers;

import com.example.sahil.eventportal.models.dto.DepartmentDTO;
import com.example.sahil.eventportal.models.dto.DeptUsersDTO;
import com.example.sahil.eventportal.models.entity.Department;
import com.example.sahil.eventportal.service.DepartmentService.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/department")
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.addDepartment(departmentDTO);
        return new ResponseEntity<>("Department added successfully", HttpStatus.OK);
    }

    @GetMapping("/department/{deptCode}")
    public ResponseEntity<DeptUsersDTO> getDepartment(@PathVariable String deptCode) {
        Department department = departmentService.getDepartmentByDeptCode(deptCode);
        return new ResponseEntity<>(new DeptUsersDTO(department), HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(dept -> new DepartmentDTO(dept)).collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

}
