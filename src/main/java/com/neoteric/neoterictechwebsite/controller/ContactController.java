package com.neoteric.neoterictechwebsite.controller;

import com.neoteric.neoterictechwebsite.dto.ContactFormDto;
import com.neoteric.neoterictechwebsite.service.ContactServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {
    @Autowired
    ContactServiceImp service;
    @PostMapping("/sendEmail")
    public ResponseEntity<String> submitForm(@RequestBody @Valid ContactFormDto dto) {

        return  service.submitContactForm(dto);
    }

}
