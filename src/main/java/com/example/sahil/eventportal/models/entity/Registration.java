package com.example.sahil.eventportal.models.entity;

import com.example.sahil.eventportal.Enumerated.RegistrationStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "registrations",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_student_event",
                columnNames = {"student_id", "event_id"}
        )
)
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @CreationTimestamp
    @Column(name = "registration_time", nullable = false, updatable = false)
    private LocalDateTime registrationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private RegistrationStatus status = RegistrationStatus.WAITLISTED;

    @Column(name = "is_attended", nullable = false)
    private Boolean isAttended = false;

    public Registration() {
    }

    public Registration(User student, Event event) {
        this.student = student;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public Boolean getIsAttended() {
        return isAttended;
    }

    public void setIsAttended(Boolean isAttended) {
        this.isAttended = isAttended;
    }
}