package com.CodingBootcamp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingBootcamp.model.EmailTemplate;
import com.CodingBootcamp.service.EmailService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/")
public class EmailController {
	@Autowired 
	private EmailService emailservice;
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	
	 
	 
	 @PostMapping("/send")
	 public ResponseEntity<HttpStatus> sends(@RequestBody EmailTemplate email){
		 logger.info("Email Sent");
			 if(emailservice.sendEmail(email))
			 return ResponseEntity.status(HttpStatus.OK).build();
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			 
		 
		 
	 }
		
	
	

}