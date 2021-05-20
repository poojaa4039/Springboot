package com.CodingBootcamp;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.controller.EmailController;
import com.CodingBootcamp.model.EmailTemplate;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class EmailControllerTest {

	@Autowired
	EmailController econ;
	@Test
	void testsend() {
		EmailTemplate e=new EmailTemplate();
		e.setMsgBody("Thank you for joining bootcamp");
		e.setSubject("bootcamp event");
		econ.sends(e);
		
		
	}

}
