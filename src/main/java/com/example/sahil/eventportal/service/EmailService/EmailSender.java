package com.example.sahil.eventportal.service.EmailService;

public interface EmailSender {
    void sendEmail(String to,String subject,String body);
}
