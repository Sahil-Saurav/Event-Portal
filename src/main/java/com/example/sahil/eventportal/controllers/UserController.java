package com.example.sahil.eventportal.controllers;

import com.example.sahil.eventportal.Enumerated;
import com.example.sahil.eventportal.models.dto.UserPrincipal;
import com.example.sahil.eventportal.models.dto.requestDto.LoginDTO;
import com.example.sahil.eventportal.models.dto.requestDto.PostAssignRoleDTO;
import com.example.sahil.eventportal.models.dto.requestDto.PostUserDTO;
import com.example.sahil.eventportal.models.dto.responseDto.TokenDTO;
import com.example.sahil.eventportal.models.dto.responseDto.UserDetailsDTO;
import com.example.sahil.eventportal.models.entity.Department;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.service.DepartmentService.DepartmentService;
import com.example.sahil.eventportal.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.findUserByEmail(loginDTO.getEmail());
        if(user == null) {
            throw new UsernameNotFoundException("Invalid Email or User Doesn't Exist");
        }
        String token = userService.verify(loginDTO);
        return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
    }

    @PostMapping("/admin/user/role")
    public ResponseEntity<String> assignRoleToUser(@RequestBody PostAssignRoleDTO assignRoleDTO) {
        userService.assignRoleToUser(assignRoleDTO.getEmail(), assignRoleDTO.getRole());
        return new ResponseEntity<>("Assigned Role",HttpStatus.OK);
    }

    @GetMapping("/user/{role}")
    public ResponseEntity<Set<UserDetailsDTO>> getUserByRole(@PathVariable("role") Enumerated.RolesEnum role) {
        Set<User> users = userService.getUserBasedOnRole(role);
        Set<UserDetailsDTO> usersDTO = users
                .stream()
                .map(user -> new UserDetailsDTO(user))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @DeleteMapping("/user/remove-own-account")
    public ResponseEntity<String> deleteOwnAccount(@AuthenticationPrincipal UserPrincipal user) {
        userService.deleteByEmailId(user.getUsername());
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/delete")
    public ResponseEntity<String> deleteUserByEmailId(@RequestParam("email") String email){
        userService.deleteByEmailId(email);
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }
}
