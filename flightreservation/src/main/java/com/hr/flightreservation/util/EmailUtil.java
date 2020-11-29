package com.hr.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;
	@Value("${flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;
	
	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress, String filePath) {
		
		MimeMessage message = sender.createMimeMessage();
		
		//boolean tells if there are multiple parameters to mail
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itenerary", new File(filePath));
			
			sender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}
}
