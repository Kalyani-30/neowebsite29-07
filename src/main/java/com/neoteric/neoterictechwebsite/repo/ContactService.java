package com.neoteric.neoterictechwebsite.repo;

import com.neoteric.neoterictechwebsite.dto.ContactFormDto;
import org.springframework.http.ResponseEntity;

public interface ContactService {
     public ResponseEntity<String> submitContactForm(ContactFormDto dto);
}
