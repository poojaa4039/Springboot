package com.CodingBootcamp;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.controller.FeedbackController;
import com.CodingBootcamp.model.Feedback;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional

class FeedbackControllerTest {
	
	@Autowired
	FeedbackController fcon; 

	@Test
	void showFeedbackTest() {
		
		fcon.showFeedback();
		
	}
	
	@Test
	void storeFeedbackTest() {
		
		Feedback feedback=new Feedback();
		
		feedback.setId(1);
		feedback.setName("Aditya");
		feedback.setFeedback("Interesting session");
		feedback.setEmail("rahul8@gmail.com");
		
		fcon.storeFeedback(feedback);
	}

}
