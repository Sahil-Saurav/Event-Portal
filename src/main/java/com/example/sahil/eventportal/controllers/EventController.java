package com.example.sahil.eventportal.controllers;


import com.example.sahil.eventportal.models.dto.EventDetailsDTO;
import com.example.sahil.eventportal.models.dto.PostEventDTO;
import com.example.sahil.eventportal.service.EventService.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public ResponseEntity<String> createEvent(@RequestBody PostEventDTO postEventDTO) {
        eventService.addEvent(postEventDTO);
        return new ResponseEntity<>("Event added successfully", HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<EventDetailsDTO> getEvent(@PathVariable("eventId") Integer eventId) {
        EventDetailsDTO event = eventService.getEventDetailsById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventDetailsDTO>> getAllEvents() {
        List<EventDetailsDTO> events = eventService.getAllEvent();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
