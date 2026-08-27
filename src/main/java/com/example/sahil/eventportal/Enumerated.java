package com.example.sahil.eventportal;

public class Enumerated {
    public enum EventApprovalStatus {
        PENDING,APPROVED,CANCELLED
    }
    public enum RegistrationStatus {
        WAITLISTED,CONFIRMED,CANCELLED
    }
    public enum RolesEnum{
        ROLE_ADMIN,
        ROLE_STUDENT,
        ROLE_FACULTY
    }
}
