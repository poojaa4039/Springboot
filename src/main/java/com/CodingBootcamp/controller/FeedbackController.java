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

import com.CodingBootcamp.model.Feedback;
import com.CodingBootcamp.service.FeedbackService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	
	@GetMapping("/feedback")
	public List<Feedback> showFeedback(){
		logger.info("show feedback Called");
		System.out.println("hello there");
		return feedbackService.showFeedback();
	}
	
	@PostMapping("/feedback")
	public void storeFeedback(@RequestBody Feedback feedback)
	{
		logger.info("Feedback stored");
		feedbackService.storeFeedback(feedback);
	}
}
