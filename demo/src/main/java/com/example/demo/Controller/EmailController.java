package com.example.demo.Controller;

import com.example.demo.Controller.API.EmailRequest;
import com.example.demo.Helper.CustomResponse;
import com.example.demo.Services.EmailServices;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailServices emailServices;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> sendEmailWithHtml(@RequestBody EmailRequest emailRequest) {
        emailServices.sendEmailWithTemplate(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getMessage()
        );
        return ResponseEntity.ok(CustomResponse.builder().status(HttpStatus.OK)
                .success(true)
                .message("Email sent successfully.").build());
    }

    @PostMapping("/send-attachment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> sendEmailWithAttachment(@RequestPart EmailRequest request,
                                                     @RequestPart("file") MultipartFile file) throws IOException {
        emailServices.sendEmailWithAttachment(
                request.getTo(),
                request.getSubject(),
                request.getMessage(),
                file.getInputStream());

        return ResponseEntity.ok(CustomResponse.builder().status(HttpStatus.OK)
                .success(true)
                .message("Email sent successfully.").build());
    }


    @PostMapping("/send-otp")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> sentOtpOnEmailTemplate(@RequestBody EmailRequest emailRequest) throws MessagingException {

        emailServices.sentOtpOnEmailTemplate(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getMessage()
        );
        return ResponseEntity.ok(CustomResponse.builder().status(HttpStatus.OK)
                .success(true)
                .message("Email sent successfully.").build());
    }
}
