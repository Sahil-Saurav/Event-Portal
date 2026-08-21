package com.example.sahil.eventportal.service.UserService;

import com.example.sahil.eventportal.models.dto.UserDetailsDTO;
import com.example.sahil.eventportal.models.dto.LoginDTO;
import com.example.sahil.eventportal.models.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(int id);
    User findByUsername(String username);
    User saveUser(User user);
    String verify(LoginDTO user);
    List<UserDetailsDTO> findAllUsers();
    User findUserByEmail(String email);
    ResponseEntity<String> deleteUserByEmail(LoginDTO loginDTO);
}
