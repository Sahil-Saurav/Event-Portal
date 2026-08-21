package com.example.sahil.eventportal.controllers;

import com.example.sahil.eventportal.models.dto.LoginDTO;
import com.example.sahil.eventportal.models.dto.PostUserDTO;
import com.example.sahil.eventportal.models.dto.UserDetailsDTO;
import com.example.sahil.eventportal.models.entity.Department;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.service.DepartmentService.DepartmentService;
import com.example.sahil.eventportal.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private DepartmentService departmentService;
    @Autowired
    public UserController(UserService userService,DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }


    @GetMapping("/public/user/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable int id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ResponseEntity<>(new UserDetailsDTO(user), HttpStatus.OK);
    }

    @PostMapping("/public/user")
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody PostUserDTO user) {
        User newUser = new User(user);
        Department dept = departmentService.getDepartmentByDeptCode(user.getDeptCode());
        newUser.setDepartment(dept);
        userService.saveUser(newUser);
        UserDetailsDTO newUserDTO = new UserDetailsDTO(newUser);
        return new ResponseEntity(newUserDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDetailsDTO>> getUser() {
        List<UserDetailsDTO> allUsers = userService.findAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/public/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        User user = userService.findUserByEmail(loginDTO.getEmail());
        if(user == null) {
            throw new UsernameNotFoundException("Invalid Email or User Doesn't Exist");
        }
        return userService.verify(loginDTO);
    }
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestBody LoginDTO loginDTO) {
        return userService.deleteUserByEmail(loginDTO);
    }
}
