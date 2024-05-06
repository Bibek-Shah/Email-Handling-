package com.example.demo.Services;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.InputStream;

public interface EmailServices {

    void sendEmail(String to, String subject, String body);

    /* send email with multiple recipients */
    void sendEmail(String[] to, String subject, String body);

    /* send email with Html template */
    void sendEmailWithTemplate(String to, String subject, String htmlContent);

    /* send email with attachment */
    void sendEmailWithAttachment(String to, String subject, String message, File file);

    /* send email with attachment InputStream */
    void sendEmailWithAttachment(String to, String subject, String message, InputStream inputStream);

    void sentOtpOnEmailTemplate(String to, String subject, String body) throws MessagingException;
}

