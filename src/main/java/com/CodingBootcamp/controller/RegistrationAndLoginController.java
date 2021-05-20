package com.CodingBootcamp.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CodingBootcamp.model.LoggedInUsers;
import com.CodingBootcamp.model.User;
import com.CodingBootcamp.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class RegistrationAndLoginController {

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(RegistrationAndLoginController.class);
	

	@PostMapping("/loginUser")
	public String login(@RequestBody User user) {
		logger.info("user logged in");
		return userService.login(user);
	}

	@PostMapping("/process_register")
	public String processRegister(@RequestBody User user, HttpServletRequest request) {
		logger.info("user registered");
		System.out.println(getSiteURL(request));
		return userService.register(user, getSiteURL(request));
		
		
			}

	public String getSiteURL(HttpServletRequest request) {
		logger.info("site url converted to string");
		String siteURL = request.getRequestURL().toString();
		System.out.println(siteURL.replace(request.getServletPath(), ""));
		return siteURL.replace(request.getServletPath(), "");

	}

	@PostMapping("/email")
	public String emailExist(@RequestBody User user) {
		logger.info("Email Exists");

		return userService.emailExist(user);
	}

	@GetMapping("/userlist")
	public List<LoggedInUsers> getUserList() {
		logger.info("get user List");

		return userService.getUserList();
	}

	@GetMapping("/registered_users")
	public List<User> getRegisteredUser() {
		logger.info("get registereded User");

		return userService.getRegisteredUsers();
	}

}
