package com.CodingBootcamp;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootcamp.model.User;
import com.CodingBootcamp.repository.UserServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class UserServiceTest {
@Autowired
private UserServiceImpl usi;
	@Test
	void testLogin() {
	User u=new User();
	
	u.setEmail("user@gmail.com");
	u.setContact("8130520044");
	u.setUserName("abc");
	u.setEnabled(true);
	assertEquals("abc", u.getUserName());
	assertEquals("user@gmail.com", u.getEmail());
	usi.login(u);
	}
	
	@Test
	void testNonVerifiedLogin() {
	User u=new User();
	
	u.setEmail("user@gmail.com");
	u.setContact("8130520044");
	u.setUserName("abc");
	u.setEnabled(false);
	assertEquals("abc", u.getUserName());
	assertEquals("user@gmail.com", u.getEmail());
	usi.login(u);
	}
	
	
	@Test
	void testInvalidEmailLogin() {
		User u=new User();
		u.setId(77L);
		u.setEmail("user.gmail.com");
		u.setContact("8130520044");
		u.setUserName("abc");
		usi.login(u);
		}
		
	@Test
	void testNullLogin() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("");
		u.setUserName("");
		assertEquals("", u.getEmail());
		usi.login(u);
		}
	@Test
	void testInvalidusernameLogin() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("@#$^&^$#");
		assertEquals("user@gmail.com", u.getEmail());
		usi.login(u);
		}
	
	
	
	@Test
	void registerTest() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		assertEquals("user@gmail.com", u.getEmail());
		
		String url="htttp://localhost8080";
		usi.register(u, url);
		
	}
	@Test
	void registernegativeContactTest() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("89999");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		assertEquals("user@gmail.com", u.getEmail());
		
		String url="htttp://localhost8080";
		usi.register(u, url);
		
	}
	@Test
	void registerAlphaContactTest() {
		User u=new User();
		u.setEmail("user@gmail.com");
		u.setContact("abc");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		assertEquals("user@gmail.com", u.getEmail());
		
		String url="htttp://localhost8080";
		usi.register(u, url);
		
	}
	
	@Test
	void registerEmptyTest() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("");
		u.setContact("");
		u.setUserName("");
		
		String url="htttp://localhost8080";
		usi.register(u, url);
		
	}
	
	@Test
	void registerInvalidUsernameTest() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("#@$%");
		String url="htttp://localhost8080";
		usi.register(u, url);
		
	}
	@Test
	void registerInvalidEmailTest() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("user,gmail..com");
		u.setContact("8130520044");
		u.setUserName("abc");
		
		String url="htttp://localhost8080";
		usi.register(u, url);
		
	}
	


	
	@Test
	void testsaveLoggedInUser() {
		User u=new User();
		u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		usi.saveLoggedInUser(u);
	}

	@Test
	void testEmailExist() {
		User u=new User();
		u.setEmail("abc@gmail.com");
		assertEquals("abc@gmail.com",u.getEmail());
	
	}

	@Test
	void testverify() {
		String ver="re231ed";
		usi.verify(ver);
	}
	

	
	@Test
	void testgetUserList()
	{
		usi.getUserList();
	}
	
	@Test
	void testGetRegisteredUsers() {
		usi.getRegisteredUsers();
	}
}
