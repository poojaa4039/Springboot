package com.CodingBootcamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodingBootcamp.model.Meeting;
import com.CodingBootcamp.service.MeetingService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/")
public class MeetingController {
	@Autowired 
	private MeetingService meeting;
	private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

	
	
	 @PostMapping("/schedulemeeting")
	 public String send(@RequestBody Meeting m){
		 
			 logger.info("meeting Scheduled");
			System.out.println(m.getStarttime()+" "+m.getEndtime()+" "+m.getEndtime().compareTo(m.getStarttime()));
			 return meeting.scheduleMeeting(m);
			
		 
	 }
		
	@GetMapping("/meeting")
	public  List<Meeting>  getMeeting() {
		 logger.info("meeting detailed Retrieved");
		return meeting.getMeetingDetails();
	}
	

}