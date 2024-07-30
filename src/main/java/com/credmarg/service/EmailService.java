package com.credmarg.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private List<String> sentEmails = new ArrayList<>();

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(List<String> vendorEmails) {

        for (String email : vendorEmails){
            try{
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(email);
                helper.setSubject("Payment Notification");
                helper.setText("Sending payments to vendor with email " + email, true);

                javaMailSender.send(message);
                sentEmails.add(email);
            }catch (MessagingException e){
                e.printStackTrace();
            }
        }
    }

    public List<String> getAllEmails() {
        return sentEmails;
    }
}
