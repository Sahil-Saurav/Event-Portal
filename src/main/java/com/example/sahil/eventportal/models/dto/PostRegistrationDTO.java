package com.example.sahil.eventportal.models.dto;

public class PostRegistrationDTO {
    private String email;
    private Integer eventId;

    public PostRegistrationDTO() {}

    public PostRegistrationDTO(String email, Integer eventId) {
        this.email = email;
        this.eventId = eventId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEmail() {
        return email;
    }

    public Integer getEventId() {
        return eventId;
    }
}
