package com.academico.models.service.components;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.academico.models.service.dto.mail.Mail;
import com.academico.models.service.exception.NegocioException;
import com.academico.models.service.function.FormatMessage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class SendEmail {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine engine;
	
	public void  createEmail(Mail mail ) {
		sendMessage(mail);
	}
	
	@Async
	private void sendMessage(Mail mail) {
		try {
			
			MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = engine.process(mail.getTemplate(), context);
           
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(html,true);
            helper.setFrom(mail.getFrom());
            
            emailSender.send(mimeMessage);
            
        } catch (MailException exception) {
            throw new NegocioException(FormatMessage.formatMessage("Servidor de E-mail não está disponível"));
        } catch (MessagingException e) {
        	throw new NegocioException(FormatMessage.formatMessage(e.getMessage()));
		}
		
	}
	
	
}
