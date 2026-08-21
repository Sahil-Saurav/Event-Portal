package com.example.sahil.eventportal.service.RegistrationService;

import com.example.sahil.eventportal.Enumerated;
import com.example.sahil.eventportal.models.dto.PostRegistrationDTO;
import com.example.sahil.eventportal.models.dto.RegistrationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistrationService {
    ResponseEntity<String> addRegistration(PostRegistrationDTO registrationDTO);
    ResponseEntity<RegistrationDTO> getRegistrationById(Long id);
    ResponseEntity<List<RegistrationDTO>> getRegistrations();
    ResponseEntity<String> cancelRegistrationById(Long id);
    ResponseEntity<List<RegistrationDTO>> getRegistrationsByUserId(Long id);
}
