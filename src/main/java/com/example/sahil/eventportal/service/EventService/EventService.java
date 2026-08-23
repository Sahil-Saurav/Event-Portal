package com.example.sahil.eventportal.service.EventService;

import com.example.sahil.eventportal.models.dto.EventDetailsDTO;
import com.example.sahil.eventportal.models.dto.PostEventDTO;
import com.example.sahil.eventportal.models.dto.UserDTO;
import com.example.sahil.eventportal.models.entity.Event;

import java.util.List;

public interface EventService {

    void addEvent(PostEventDTO postEventDTO);
    EventDetailsDTO getEventDetailsById(Integer id);
    List<EventDetailsDTO> getAllEvent();
    List<UserDTO> getAllUsersFromEvent(Long eventId);
}
