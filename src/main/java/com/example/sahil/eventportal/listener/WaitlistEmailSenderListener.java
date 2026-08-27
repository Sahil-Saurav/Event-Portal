package com.example.sahil.eventportal.listener;

import com.example.sahil.eventportal.event.WaitlistPromotedEvent;
import com.example.sahil.eventportal.service.EmailService.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class WaitlistEmailSenderListener {

    private EmailSender mailSender;

    @Autowired
    public WaitlistEmailSenderListener(EmailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendConfirmationEmailToWaitlisted(WaitlistPromotedEvent waitlistPromotedEvent) {
        mailSender.sendEmail(waitlistPromotedEvent.getTo(),waitlistPromotedEvent.getSubject(),waitlistPromotedEvent.getBody());
    }
}
