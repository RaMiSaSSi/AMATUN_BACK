package com.example.demo.service.Auth;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);
    }
    public void sendPersonnelBoutiqueEmail(String to, String username, String password) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Welcome to the Boutique Team");

        String htmlContent = "<div style='background-color: #FF6F00; color: #000; padding: 20px; font-family: Arial, sans-serif;'>"
                + "<h1 style='color: #FFF;'>Welcome to the Team!</h1>"
                + "<p>Dear <strong>" + username + "</strong>,</p>"
                + "<p>Your login details are as follows:</p>"
                + "<ul>"
                + "<li><strong>Email:</strong> " + to + "</li>"
                + "<li><strong>Password:</strong> " + password + "</li>"
                + "</ul>"
                + "<p>We are excited to have you on board!</p>"
                + "</div>";

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

}