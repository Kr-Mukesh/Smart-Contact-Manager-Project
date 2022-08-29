package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.service.EmailService;


@Controller
public class ForgotController {
	Random random = new Random(1000);
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping("/forgot")
	public String openEmailForm() {
		
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam("email") String email, HttpSession session) {
		
		System.out.println("EMAIL :"+email);
		
		//generating otp of 4-digit
		
		int otp = random.nextInt(99999);
		System.out.println("OTP :"+otp);
		
		// write code for send otp to email
		
		String subject ="OTP FROM SCM";
		String message =""
				+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
				+ "<h1>"
				+ "OTP is"
				+ "<b>"+otp
				+ "</b>"
				+ "</h1>"
				+ "</div>";
				
				
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		if(flag) {
			
			session.setAttribute("myotp", otp);
			session.setAttribute(email, email);
			
			return "verify_otp";
			
		}else {
			
			session.setAttribute(message, "Check Your Email Id !!");
			
			return "forgot_email_form";
		}
		
		
	}
	
}
