package com.edwin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com ");
        mailSender.setPort(587);
        mailSender.setUsername("719204145@qq.com");
        mailSender.setPassword("mpkskrqereqbbfii");
        return mailSender;
    }

}
