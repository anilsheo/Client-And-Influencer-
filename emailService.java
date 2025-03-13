package com.example.demo.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
@Service
public class emailService 
{
	private final String senderEmail="fasheo333@gmail.com";
	private final String senderPassword="pfxp iijf mkmq qldl";

	public void sendEmail(String recipientEmail,String subject,String messageText)
	{
		Properties props=new Properties();
		 props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.host", "smtp.gmail.com");
	     props.put("mail.smtp.port", "587");
	        
	     
	     Session session =Session.getInstance(props,new Authenticator()
	    		 {
	    	 	protected PasswordAuthentication getPasswordAuthentication()
	    	 	{
	    	 		return new PasswordAuthentication(senderEmail,senderPassword);
	    	 	}
	    		 });
	     
	     try
	     {
	    	 Message message =new MimeMessage(session);
	    	 message.setFrom(new InternetAddress(senderEmail));
	    	 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	    	 message.setSubject(subject);
	    	 message.setText(messageText);
	    	 
	    	 Transport.send(message);
	    	 System.out.println("Email Successfully Sent to :"+recipientEmail);
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	}
	
}
