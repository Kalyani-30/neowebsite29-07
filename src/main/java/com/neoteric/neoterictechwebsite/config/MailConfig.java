package com.neoteric.neoterictechwebsite.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
    public class MailConfig {

        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost("smtp.gmail.com");
            sender.setPort(587);

            sender.setUsername("gounimahesh9000@gmail.com");
            sender.setPassword("hqdu yhyb mpkr rysm");

            Properties props = sender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return sender;
        }
    }


