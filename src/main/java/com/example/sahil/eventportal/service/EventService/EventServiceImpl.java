package com.example.sahil.eventportal.service.EventService;

import com.example.sahil.eventportal.exception.ResourceNotFoundException;
import com.example.sahil.eventportal.models.dto.EventDetailsDTO;
import com.example.sahil.eventportal.models.dto.PostEventDTO;
import com.example.sahil.eventportal.models.entity.Department;
import com.example.sahil.eventportal.models.entity.Event;
import com.example.sahil.eventportal.models.entity.User;
import com.example.sahil.eventportal.repository.DepartmentRepository;
import com.example.sahil.eventportal.repository.EventRepository;
import com.example.sahil.eventportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    EventServiceImpl(EventRepository eventRepository,
                     UserRepository userRepository,
                     DepartmentRepository departmentRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public void addEvent(PostEventDTO postEventDTO) {
        User organizer = userRepository.findByEmail(postEventDTO.getOrganizerEmail());
        if(organizer == null) {
            throw new ResourceNotFoundException("The organizer was not found!!");
        }
        Department dept = departmentRepository.findByDeptCode(postEventDTO.getDeptCode());
        if(dept == null) {
            throw new ResourceNotFoundException("The department was not found!!\n enter correct department code");
        }
        Event event = new Event(postEventDTO);
        event.setOrganizer(organizer);
        event.setDepartment(dept);

        eventRepository.save(event);
    }

    @Override
    public EventDetailsDTO getEventDetailsById(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The event was not found!"));
        EventDetailsDTO eventDetailsDTO = new EventDetailsDTO(event);
        return eventDetailsDTO;
    }

    @Override
    public List<EventDetailsDTO> getAllEvent() {
        List<Event> events = eventRepository.findAll();
        List<EventDetailsDTO> eventDetailsDTOs = events
                .stream()
                .map(event -> new EventDetailsDTO(event))
                .collect(Collectors.toList());
        return eventDetailsDTOs;
    }
}
