package com.example.sahil.eventportal.service.EventService;

import com.example.sahil.eventportal.models.dto.responseDto.EventDetailsDTO;
import com.example.sahil.eventportal.models.dto.requestDto.PostEventDTO;
import com.example.sahil.eventportal.models.dto.responseDto.UserDTO;

import java.util.List;

public interface EventService {

    void addEvent(PostEventDTO postEventDTO);
    EventDetailsDTO getEventDetailsById(Integer id);
    List<EventDetailsDTO> getAllEvent();
    List<UserDTO> getAllUsersFromEvent(Long eventId);
}
