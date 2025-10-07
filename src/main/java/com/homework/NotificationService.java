package com.homework;

public class NotificationService {
    private EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendNotification() {
        String message = emailService.getMessage();
        System.out.println("Notification: " + message);
    }
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
    
}
