package com.academico.config;

//import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SpringMailConfig {
	
	@Bean
	public JavaMailSender mailSender() {
		
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		
		emailSender.setHost("127.0.0.1");
		emailSender.setPort(1025);
//		emailSender.setUsername("");
//		emailSender.setPassword("");
		
//		Properties props = new Properties();
//		
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.starttls.enable.required", "true");
//		props.put("mail.smtp.ssl.enable", "false");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.debug", "true");
		
//		emailSender.setJavaMailProperties(props);
		
		return emailSender;
	}

}
