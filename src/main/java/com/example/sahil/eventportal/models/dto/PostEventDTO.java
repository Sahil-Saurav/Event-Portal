package com.example.sahil.eventportal.models.dto;

import com.example.sahil.eventportal.Enumerated.EventApprovalStatus;

import java.time.LocalDateTime;

public class PostEventDTO {
    private String title;
    private String description;
    private String category;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxCapacity;
    private Integer registeredCount = 0;
    private String organizerEmail;
    private String deptCode;

    public PostEventDTO() {}

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

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}

