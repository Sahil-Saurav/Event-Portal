package com.example.sahil.eventportal.service.UserService;

import com.example.sahil.eventportal.Enumerated.RolesEnum;
import com.example.sahil.eventportal.exception.UnAuthorizedAccess;
import com.example.sahil.eventportal.models.dto.UserPrincipal;
import com.example.sahil.eventportal.models.dto.responseDto.UserDetailsDTO;
import com.example.sahil.eventportal.models.dto.requestDto.LoginDTO;
import com.example.sahil.eventportal.models.entity.Role;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.repository.DepartmentRepository;
import com.example.sahil.eventportal.repository.RoleRepository;
import com.example.sahil.eventportal.repository.UserRepository;
import com.example.sahil.eventportal.service.JwtService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private DepartmentRepository departmentRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void UserService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            AuthenticationManager authenticationManager,
                            JwtService jwtService,
                            DepartmentRepository departmentRepository,
                            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDepartment(user.getDepartment());
        return userRepository.save(user);
    }

    @Override
    public String verify(LoginDTO user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        }
        return "failed";
    }

    @Override
    public List<UserDetailsDTO> findAllUsers() {
        return userRepository.findAll().stream().map(map -> new UserDetailsDTO(map)).toList();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void assignRoleToUser(String email,RolesEnum role) {
        Role toAssignRole = roleRepository.findRoleByName(role.toString());
        if(toAssignRole == null) {
            throw new UnAuthorizedAccess("The Role you're trying to assign is prohibited or not exists");
        }
        User userToAssign = userRepository.findByEmail(email);
        if(userToAssign == null) {
            throw new UsernameNotFoundException("User not found");
        }
        userToAssign.addRole(toAssignRole);
    }

    @Override
    public Set<User> getUserBasedOnRole(RolesEnum role){
        Set<User> users = userRepository.findAllByRoleName(role.toString());
        return users;
    }

    @Override
    public void deleteByEmailId(String email) {
        User user = userRepository.findByEmail(email);
        Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
        if(user.getRoles().contains(adminRole)) {
            throw new UnAuthorizedAccess("You are not allowed to remove this user");
        }
        userRepository.delete(user);
    }
}
