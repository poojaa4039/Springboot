package com.CodingBootcamp;


import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.model.Feedback;
import com.CodingBootcamp.service.FeedbackService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class FeedbackServiceTest {
	
	
	@Autowired
	FeedbackService feedservice;

	@Test
	void saveFeedbacktest() {
		Feedback feedback=new Feedback();
		
		feedback.setId(1);
		feedback.setName("Aditya");
		feedback.setFeedback("Interesting session");
		feedback.setEmail("rahul8@gmail.com");
		
		feedservice.storeFeedback(feedback);
		
	}

	@Test
	void getFeedbackTest() {
		 
		feedservice.showFeedback();
		
	}
	
//	@Test
//	void saveconstructorTest() {
//		Feedback feedback=new Feedback(1,"Aditya","rahul8@gmail.com","Interesting session");
//		
//		
//		
//		
//		
//	}
}
