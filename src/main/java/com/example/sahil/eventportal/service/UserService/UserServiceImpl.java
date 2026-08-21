package com.example.sahil.eventportal.service.UserService;

import com.example.sahil.eventportal.models.dto.UserDetailsDTO;
import com.example.sahil.eventportal.models.dto.LoginDTO;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.repository.DepartmentRepository;
import com.example.sahil.eventportal.repository.UserRepository;
import com.example.sahil.eventportal.service.JwtService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private DepartmentRepository departmentRepository;

    @Autowired
    public void UserService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            AuthenticationManager authenticationManager,
                            JwtService jwtService,
                            DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.departmentRepository = departmentRepository;
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
    public ResponseEntity<String> deleteUserByEmail(LoginDTO loginDTO) {
        User toDelete = userRepository.findByEmail(loginDTO.getEmail());
        if(toDelete == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if(passwordEncoder.matches(loginDTO.getPassword(), toDelete.getPassword())) {
            userRepository.delete(toDelete);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong password", HttpStatus.FORBIDDEN);
    }
}
