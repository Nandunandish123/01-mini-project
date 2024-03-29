package com.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailsender; 
	

	public boolean sendemail(String subject, String body, String to) {
	       boolean	isSent =  false;
	       try {
    MimeMessage mimeMessage =  mailsender.createMimeMessage();
    MimeMessageHelper  helper =  new MimeMessageHelper(mimeMessage);
       helper.setTo(to);
       helper.setSubject(subject);
       helper.setText(body, true);
       mailsender.send(mimeMessage);
       isSent = true;
	       }catch(Exception e) {
	    	   e.printStackTrace();
	       }
	       
	       return isSent;
	}
}
