package com.example.demo.Services.Impl;

import com.example.demo.Services.EmailServices;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Service
@RequiredArgsConstructor
public class EmailServicesImpl implements EmailServices {


    private final Logger logger = LoggerFactory.getLogger(EmailServicesImpl.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText("Hello, \n" + body);
        mailMessage.setFrom("gO1fO@example.com");
        mailSender.send(mailMessage);
        logger.info("Email sent successfully.");

    }

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom("gO1fO@example.com");
        mailSender.send(mailMessage);
        logger.info("Email sent successfully with multiple recipients.");

    }

    @Async
    @Override
    public void sendEmailWithTemplate(String to, String subject, String htmlContent) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("sdevil78088@gmail.com");
            helper.setText(htmlContent, true);


            mailSender.send(message);
            logger.info("Email sent successfully with HTML template.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Async
    @Override
    public void sendEmailWithAttachment(String to, String subject, String message, File file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("sdevil78088@gmail.com");
            helper.setText(message);
            FileSystemResource resource = new FileSystemResource(file);
            helper.addAttachment(file.getName(), file);
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully with attachment.");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String message, InputStream inputStream) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("sdevil78088@gmail.com");
            helper.setText(message);
            /*Input Stream*/

            File file = new File("src/main/resources/images/test.pdf");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource resource = new FileSystemResource(file);
            helper.addAttachment(resource.getFilename(), file);

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    public void sentOtpOnEmailTemplate(String to, String subject, String body) throws MessagingException {

        String ConformationUrl = "https://www.google.com";
        String CancelUrl = "https://www.google.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setFrom("sdevil78088@gmail.com");

        Context context = new Context();

        context.setVariable("otp", generateOTP());
        context.setVariable("message", subject);
        context.setVariable("ConformationUrl", ConformationUrl);
        context.setVariable("CancelUrl", CancelUrl);
        String htmlTemplate = templateEngine.process("email-template", context);
        helper.setText(htmlTemplate, true);
        mailSender.send(message);

    }

    /*   Generate Otp  */

    public static String generateOTP() {
        String characters = "0123456789";
        int length = 6;
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(index);
        }
        return stringBuilder.toString();
    }
}
