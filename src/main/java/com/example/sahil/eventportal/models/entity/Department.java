package com.example.sahil.eventportal.models.entity;

import com.example.sahil.eventportal.models.dto.DepartmentDTO;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dept_code", nullable = false, unique = true, length = 10)
    private String deptCode;

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    @OneToMany(mappedBy = "department")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Event> events = new HashSet<>();

    public Department() {
    }

    public Department(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
    }

    public Department(DepartmentDTO departmentDTO) {
        this.deptCode = departmentDTO.getDeptCode();
        this.deptName = departmentDTO.getDeptName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if(user == null) {
            this.users = new HashSet<>();
        }
        this.users.add(user);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
    public void addEvent(Event event) {
        if(event == null) {
            this.events = new HashSet<>();
        }
        this.events.add(event);
    }
}