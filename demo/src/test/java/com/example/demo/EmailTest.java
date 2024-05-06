package com.example.demo;

import com.example.demo.Services.EmailServices;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@SpringBootTest
public class EmailTest {
    private static final Logger log = LoggerFactory.getLogger(EmailTest.class);
    @Autowired
    private EmailServices emailServices;

    @Test
    void emailSendTest() {
        emailServices.sendEmail("joshi3kapil@gmail.com", "test", "test");
        System.out.println("Email sent successfully.");
    }

    @Test
    void emailSendWithTemplateTest() {

        String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
        emailServices.sendEmailWithTemplate("joshi3kapil@gmail.com", "test", htmlContent);
        System.out.println("Email sent successfully with HTML template.");
    }

    @Test
    void emailSendWithAttachmentTest() {
        emailServices.sendEmailWithAttachment(
                "sahbibek55@gmail.com",
                "test",
                "test",
                new File("/Users/bibeksah/Documents/JAVA & REACT/EmailHandling/demo/src/main/resources/images/Screenshot 2024-04-13 at 5.06.36 PM.png"));
        System.out.println("Email sent successfully with attachment.");
    }

    @Test
    void emailSendWithInputStreamTest() {

        File file = new File("/Users/bibeksah/Desktop/Screenshot 2024-04-25 at 2.46.43 AM.png");
        try {
            InputStream inputStream = new FileInputStream(file);
            emailServices.sendEmailWithAttachment(
                    "joshi3kapil@gmail.com",
                    "test",
                    "test",
                    inputStream
            );
            log.info("Email sent successfully with InputStream.");
            System.out.println("Email sent successfully with InputStream.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
