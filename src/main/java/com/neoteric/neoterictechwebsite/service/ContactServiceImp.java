package com.neoteric.neoterictechwebsite.service;


import com.neoteric.neoterictechwebsite.dto.ContactFormDto;
import com.neoteric.neoterictechwebsite.entity.ContactEntity;
import com.neoteric.neoterictechwebsite.repo.ContactRepository;
import com.neoteric.neoterictechwebsite.repo.ContactService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImp implements ContactService {

    private final ContactRepository repository;
    private final JavaMailSender mailSender;

    public ContactServiceImp(ContactRepository repository, JavaMailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
    }

    @Override
    public ResponseEntity<String> submitContactForm(ContactFormDto dto) {
        ContactEntity entity = new ContactEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCompany(dto.getCompany());
        entity.setDesignation(dto.getDesignation());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setMessage(dto.getMessage());
        repository.save(entity);

        sendCompanyEmail(dto);
        sendUserConfirmation(dto);
        return new ResponseEntity<>("saved sucessfully",HttpStatus.OK);
    }

    private void sendCompanyEmail(ContactFormDto dto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("gounimahesh9000@gmail.com");
            helper.setSubject("New Contact Form Submission");
            helper.setText(
                    "New Contact Request:\n\n" +
                            "Name: " + dto.getFirstName() + " " + dto.getLastName() + "\n" +
                            "Email: " + dto.getEmail() + "\n" +
                            "Message: " + dto.getMessage(), true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email", e);
        }
    }

    private void sendUserConfirmation(ContactFormDto dto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(dto.getEmail());
            helper.setSubject("Thank you for contacting us");
            helper.setText(
                    "<h2>Hi " + dto.getFirstName() + ",</h2>" +
                            "<p> Thank you for reaching out to <strong>Neoteric Technologies</strong>! We're thrilled to hear from you. Your message has been received,One of our team members will connect with you shortly. Until then, feel free to explore more about us on our website or follow us on social media!</p>" +
                            "<p>Warm regards,<br><strong>Neoteric Technologies inc Pvt Ltd</strong></p>" ,true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending user confirmation", e);
        }
    }
}
