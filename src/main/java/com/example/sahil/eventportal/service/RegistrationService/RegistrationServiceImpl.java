package com.example.sahil.eventportal.service.RegistrationService;

import com.example.sahil.eventportal.Enumerated;
import com.example.sahil.eventportal.exception.ResourceNotFoundException;
import com.example.sahil.eventportal.models.dto.PostRegistrationDTO;
import com.example.sahil.eventportal.models.dto.RegistrationDTO;
import com.example.sahil.eventportal.models.entity.Event;
import com.example.sahil.eventportal.models.entity.Registration;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.repository.EventRepository;
import com.example.sahil.eventportal.repository.RegistrationRepository;
import com.example.sahil.eventportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository,
                                   EventRepository eventRepository,
                                   RegistrationRepository registrationRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<String> addRegistration(PostRegistrationDTO registrationDTO) {
        User user = userRepository.findByEmail(registrationDTO.getEmail());
        Event event = eventRepository.findByIdForUpdate(registrationDTO.getEventId()).orElse(null);

        if(event == null){
            throw new ResourceNotFoundException("Event not found");
        }

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        //race condition
        Registration registration = new Registration(user,event);
        if(event.getRegisteredCount() < event.getMaxCapacity()){
            event.setRegisteredCount(event.getRegisteredCount() + 1);
            eventRepository.save(event);
            registration.setStatus(Enumerated.RegistrationStatus.CONFIRMED);
            registrationRepository.save(registration);
            return new ResponseEntity<>("Registration added successfully", HttpStatus.OK);
        }else{
            registration.setStatus(Enumerated.RegistrationStatus.WAITLISTED);
            registrationRepository.save(registration);
            return new ResponseEntity<>("Seat is already all booked you are waitlisted", HttpStatus.OK);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<String> cancelRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
        Event event = eventRepository.findByIdForUpdate(Math.toIntExact(registration.getEvent().getId()))
                .orElseThrow(() -> new  ResourceNotFoundException("No event found"));

        registration.setStatus(Enumerated.RegistrationStatus.CANCELLED);
        registrationRepository.save(registration);

        Registration nextInLine = registrationRepository
                .findFirstByStatusOrderByRegistrationTimeAsc(Enumerated.RegistrationStatus.WAITLISTED);
        if(nextInLine != null){
            nextInLine.setStatus(Enumerated.RegistrationStatus.CONFIRMED);
            registrationRepository.save(nextInLine);
        }else{
            event.setRegisteredCount(event.getRegisteredCount() - 1);
            eventRepository.save(event);
        }
        return new ResponseEntity<>("Registration cancelled", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RegistrationDTO>> getRegistrationsByUserId(Long id) {
        List<Registration> registrations = registrationRepository.findAllByStudentId(id);
        List<RegistrationDTO> registrationDTOS = registrations.stream().map(RegistrationDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegistrationDTO> getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id).orElse(null);

        if(registration == null){
            throw new ResourceNotFoundException("Registration not found");
        }

        RegistrationDTO registrationDTO = new RegistrationDTO(registration);
        return new  ResponseEntity<>(registrationDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RegistrationDTO>> getRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        if(registrations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RegistrationDTO> registrationDTOS = registrations
                .stream()
                .map(reg -> new RegistrationDTO(reg))
                .collect(Collectors.toList());
        return new ResponseEntity<>(registrationDTOS, HttpStatus.OK);
    }

}
