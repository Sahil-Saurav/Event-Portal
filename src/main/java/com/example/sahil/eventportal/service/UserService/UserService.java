package com.example.sahil.eventportal.service.UserService;

import com.example.sahil.eventportal.Enumerated.RolesEnum;
import com.example.sahil.eventportal.models.dto.UserPrincipal;
import com.example.sahil.eventportal.models.dto.responseDto.UserDetailsDTO;
import com.example.sahil.eventportal.models.dto.requestDto.LoginDTO;
import com.example.sahil.eventportal.models.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<User> findById(int id);
    User findByUsername(String username);
    User saveUser(User user);
    String verify(LoginDTO user);
    List<UserDetailsDTO> findAllUsers();
    User findUserByEmail(String email);
    void assignRoleToUser(String email, RolesEnum role);
    Set<User> getUserBasedOnRole(RolesEnum role);
    void deleteByEmailId(String email);
}
