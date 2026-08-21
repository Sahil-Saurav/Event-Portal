package com.example.sahil.eventportal.models.dto;

import com.example.sahil.eventportal.Enumerated.RegistrationStatus;
import com.example.sahil.eventportal.models.entity.Registration;

public class RegistrationDTO {
    private Long eventId;
    private UserDTO user;
    private EventDTO event;
    private RegistrationStatus registrationStatus;
    private Boolean isAttended;

    public RegistrationDTO() {}

    public RegistrationDTO(Registration registration) {
        this.user = registration.getStudent() != null ? new UserDTO(registration.getStudent()) : null;
        this.event = registration.getEvent() != null ? new EventDTO(registration.getEvent()) : null;
        this.eventId = registration.getId();
        this.registrationStatus = registration.getStatus();
        this.isAttended = registration.getIsAttended();

    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public Boolean getAttended() {
        return isAttended;
    }

    public void setAttended(Boolean attended) {
        isAttended = attended;
    }
}
