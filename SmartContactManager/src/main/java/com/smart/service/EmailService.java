package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {

		boolean f=false;
		
		String from = "mukesh.chotu96@gmail.com";
		
		
		// variable for gmail
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		System.out.println("Properties :" + properties);

		// host set
		properties.put("mail.smtp.host", host);
		// port set
		properties.put("mail.smtp.port", "465");
		// ssl set
		properties.put("mail.smtp.ssl.enable", "true");
		// authentication set
		properties.put("mail.smtp.auth", "true");

		// step 1 : to get the session object

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("mukesh.chotu96@gmail.com", "*******");
			}

		});

		session.setDebug(true);

		// step 2 : compose the message [text, media]

		MimeMessage m = new MimeMessage(session);

		try {
			// from email
			m.setFrom(from);

			// adding recipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
//			m.setText(message);
			
			m.setContent(message, "text/html");
			

			// send the message using transport class
			Transport.send(m);

			System.out.println("Send Successfully....");

			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
}
