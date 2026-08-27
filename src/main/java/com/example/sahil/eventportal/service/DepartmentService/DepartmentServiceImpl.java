package com.example.sahil.eventportal.service.DepartmentService;

import com.example.sahil.eventportal.exception.ResourceNotFoundException;
import com.example.sahil.eventportal.models.dto.responseDto.DepartmentDTO;
import com.example.sahil.eventportal.models.dto.responseDto.EventDTO;
import com.example.sahil.eventportal.models.dto.responseDto.UserDTO;
import com.example.sahil.eventportal.models.entity.Department;
import com.example.sahil.eventportal.models.entity.Event;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public void addDepartment(DepartmentDTO department) {
        Department dept = new Department(department.getDeptCode(), department.getDeptName());
        departmentRepository.save(dept);
    }

    @Override
    public Department getDepartmentByDeptCode(String deptCode) {
        Department department = departmentRepository.findByDeptCode(deptCode);
        if(department == null){
            throw new ResourceNotFoundException("Department not found with deptCode: " + deptCode);
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty()){
            throw new ResourceNotFoundException("List is Empty!");
        }
        return departments;
    }

    @Override
    public List<UserDTO> getAllUsersInDepartment(Department department) {
        Set<User> users = department.getUsers();
        List<UserDTO> userDTOs = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public List<EventDTO> getAllEventsInDepartment(Department department) {
        Set<Event> events = department.getEvents();
        List<EventDTO> eventDTOs = events.stream().map(event -> new EventDTO(event)).collect(Collectors.toList());
        return eventDTOs;
    }
}
