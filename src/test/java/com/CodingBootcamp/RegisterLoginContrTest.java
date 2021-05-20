package com.CodingBootcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.controller.RegistrationAndLoginController;
import com.CodingBootcamp.model.User;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class RegisterLoginContrTest {

	@Autowired
	private RegistrationAndLoginController rlcon;
	
	@Test
	void testLogin() {
		User u=new User();
		u.setEmail("user@gmail.com");
		u.setUserName("abc");
		assertEquals("abc",u.getUserName());
		assertEquals("user@gmail.com", u.getEmail());
		rlcon.login(u);
	}

	@Test
	void testProcessRegister() {
		User u=new User();
		
		u.setContact("9876543210");
		u.setUserName("abc");
		u.setEmail("user@gmail.com");
		MockHttpServletRequest request= new MockHttpServletRequest();
		rlcon.processRegister(u, request);
	}


	@Test
	void testGetURL() {
		rlcon.getSiteURL(new MockHttpServletRequest());
	
	}
	
	@Test
	void testEmailExist() {
		User u=new User();
		u.setEmail("abc@gmail.com");
		assertEquals("abc@gmail.com",u.getEmail());
		rlcon.emailExist(u);
	}

	@Test
	void testGetUserList() {
	rlcon.getUserList();
	}

	@Test
	void testGetRegisteredUser() {
		rlcon.getRegisteredUser();
		}

}
