package com.CodingBootcamp;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.model.EmailTemplate;
import com.CodingBootcamp.repository.EmailServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class EmailServiceTest {
@Autowired
private EmailServiceImpl esi;
	@Test
	void testSendEmailTo() {
		EmailTemplate e=new EmailTemplate();
		e.setSubject("Verification Email");
		e.setMsgBody("This email contains the verification link for the bootcamp Registration");
		esi.sendEmail(e);
	}
}
