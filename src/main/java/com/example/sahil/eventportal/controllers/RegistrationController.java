package com.example.sahil.eventportal.controllers;

import com.example.sahil.eventportal.models.dto.PostRegistrationDTO;
import com.example.sahil.eventportal.models.dto.RegistrationDTO;
import com.example.sahil.eventportal.service.RegistrationService.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody PostRegistrationDTO registrationDTO) {
        return registrationService.addRegistration(registrationDTO);
    }

    @GetMapping("/registration/{id}")
    public ResponseEntity<RegistrationDTO> getRegistration(@PathVariable Long id) {
        return registrationService.getRegistrationById(id);
    }

    @GetMapping("/registration")
    public ResponseEntity<List<RegistrationDTO>> getRegistrations() {
        return registrationService.getRegistrations();
    }

    @PostMapping("/registration/cancel/{id}")
    public ResponseEntity<String> cancelRegistration(@PathVariable Long id) {
        return registrationService.cancelRegistrationById(id);
    }
}
