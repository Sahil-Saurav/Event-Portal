package com.example.sahil.eventportal.models.dto.responseDto;

import com.example.sahil.eventportal.Enumerated;
import com.example.sahil.eventportal.models.entity.Event;

import java.time.LocalDateTime;

public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Enumerated.EventApprovalStatus eventApprovalStatus;

    public EventDTO() {}

    public EventDTO(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.category = event.getCategory();
        this.venue = event.getVenue();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.eventApprovalStatus = event.getApprovalStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Enumerated.EventApprovalStatus getEventApprovalStatus() {
        return eventApprovalStatus;
    }

    public void setEventApprovalStatus(Enumerated.EventApprovalStatus eventApprovalStatus) {
        this.eventApprovalStatus = eventApprovalStatus;
    }
}
