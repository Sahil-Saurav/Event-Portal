package com.example.sahil.eventportal.models.dto.responseDto;

import com.example.sahil.eventportal.Enumerated.EventApprovalStatus;
import com.example.sahil.eventportal.models.entity.Event;

import java.time.LocalDateTime;

public class EventDetailsDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxCapacity;
    private Integer registeredCount;
    private EventApprovalStatus eventApprovalStatus;
    private LocalDateTime createdAt;
    private UserDTO organizer;
    private DepartmentDTO department;

    public EventDetailsDTO() {}

    public EventDetailsDTO(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.category = event.getCategory();
        this.venue = event.getVenue();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.maxCapacity = event.getMaxCapacity();
        this.registeredCount = event.getRegisteredCount();
        this.eventApprovalStatus = event.getApprovalStatus();
        this.createdAt = event.getCreatedAt();
        this.organizer = new UserDTO(event.getOrganizer());
        this.department = new DepartmentDTO(event.getDepartment());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Integer getRegisteredCount() {
        return registeredCount;
    }

    public EventApprovalStatus getEventApprovalStatus() {
        return eventApprovalStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserDTO getOrganizer() {
        return organizer;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }
}
