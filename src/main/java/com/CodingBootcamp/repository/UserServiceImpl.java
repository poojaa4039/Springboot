package com.CodingBootcamp.repository;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.CodingBootcamp.customException.InvalidContactNoException;
import com.CodingBootcamp.customException.InvalidEmailFormatException;
import com.CodingBootcamp.customException.InvalidUserNameException;
import com.CodingBootcamp.dao.LoggedInUserDAO;
import com.CodingBootcamp.dao.UserDAO;
import com.CodingBootcamp.model.LoggedInUsers;
import com.CodingBootcamp.model.User;
import com.CodingBootcamp.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LoggedInUserDAO loggedInUsers;
	@Autowired
	private JavaMailSender mailSender;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public String login(User user) {
	
		String name = user.getUserName();
		String email = user.getEmail();

		try {
			logger.info("login Successful.");
			if (name.equals("") || email.equals(""))
				return "Please enter all the details!!!";
			else if (!(user.getUserName().matches("^[a-zA-Z_ ]*$"))) {
				throw new InvalidUserNameException("User Name must contain only alphabets!!! ");
			}

			else if (!(Pattern.compile(
					"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}")
					.matcher(user.getEmail()).matches())) {
				throw new InvalidEmailFormatException("Invalid email!!!! ");
			}

		} catch (InvalidUserNameException e) {
			logger.error("Invalid username");

			return e.getMessage();
		} catch (InvalidEmailFormatException e) {
			logger.error("Invalid Email Format");

			return e.getMessage();
		}

		List<User> userList = new ArrayList<User>();
		userList = userDAO.findAll();

		for (User user1 : userList) {

			if ((user1.getUserName().equalsIgnoreCase(name) && user1.getEmail().equalsIgnoreCase(email)) && (user1.isEnabled())) {
				saveLoggedInUser(user1);
				return "success";
			}
		}

		return "Not a verified user!!!";
	}

	public void saveLoggedInUser(User user) {
		logger.info("Save Logged In Users function called");
		int count = 0;
		List<LoggedInUsers> userList = loggedInUsers.findAll();
		for (LoggedInUsers list : userList) {
			if (list.getEmail().equals(user.getEmail())) {
				count++;
			}

		}
		if (count == 0) {
			LoggedInUsers users = new LoggedInUsers();
			users.setUserName(user.getUserName());
			users.setContact(user.getContact());
			users.setEmail(user.getEmail());
			loggedInUsers.save(users);
		}

	}

	// user registration will successful only if email exist

	@Override
	public String emailExist(User user) {
		logger.info("Email Exists");
		List<User> userList = new ArrayList<User>();
		userList = userDAO.findAll();
		for (User user1 : userList) {

			if ((user1.getEmail().equalsIgnoreCase(user.getEmail())) && user1.isEnabled()) {
				saveLoggedInUser(user1);
				return "success";
			}
		}

		return "Please login with verified email!!!";
	}

	@Override
	public List<LoggedInUsers> getUserList() {
		logger.info("Logged In User List Retrieved");
		return loggedInUsers.findAll();
	}

	@Override
	public List<User> getRegisteredUsers() {
		logger.info("Registered Users Retrieved");
		List<User> list = userDAO.findAll();
		List<User> list1 = new ArrayList<User>();
		for (User user : list) {
			if (user.isEnabled())
				list1.add(user);
		}
		return list1;
	}

	@Override
	public String register(User user, String siteURL) {
		

		try {
			logger.info("Registration Successful.");
			if (user.getEmail().equals("") || user.getContact().equals("") || user.getUserName().equals(""))
				return "Please enter all the details!!!";
			// checking the format of contact no i.e it should only contain Number
			Long.parseLong(user.getContact());

			// checking the no. of digit in contact no
			if (user.getContact().length() != 10) {
				throw new InvalidContactNoException(" Contact number should be of 10 digits!!! ");
			}

			// user name should only contain alphabets
			else if (!(user.getUserName().matches("^[a-zA-Z_ ]*$"))) {
				throw new InvalidUserNameException(" User Name must contain only alphabets!!! ");
			}

			// checking the correct format of email
			else if (!(Pattern.compile(
					"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}")
					.matcher(user.getEmail()).matches())) {
				throw new InvalidEmailFormatException(" Invalid email!!!! ");
			}

		} catch (InvalidContactNoException e) {
			logger.error("Invalid Contact");
			System.out.println(e);
			return e.getMessage();
		} catch (InvalidUserNameException e) {
			logger.error("Invalid Username");
			System.out.println(e);
			return e.getMessage();
		} catch (NumberFormatException e) {
			logger.error("Invalid Contact Number");
			return "Error: Contact number can contain digits only!!!";
		} catch (InvalidEmailFormatException e) {
			logger.error("Invalid Email Format");
			System.out.println(e);
			return e.getMessage();
		}

		int count = 0;
		List<User> userList = userDAO.findAll();
		for (User user1 : userList)
			if ((user1.getEmail().equalsIgnoreCase(user.getEmail())) && (user1.isEnabled()))
				count++;

		if (count == 0) {
			String randomCode = RandomString.make(64);
			user.setVerificationCode(randomCode);
			user.setEnabled(false);
			userDAO.save(user);
			sendVerificationEmail(user, siteURL);
			return "Check your email for confirmation link";

		} else
			return "Email already registered!!";

	}

	private void sendVerificationEmail(User user, String siteURL) {
		logger.info("verification Email Sent");
		String toAddress = user.getEmail();
		String fromAddress = "coderalpha41@gmail.com";
		String senderName = "RealcoderZ Pvt Ltd";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you.<br/>RealcoderZ Pvt Ltd<br>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		content = content.replace("[[name]]", user.getUserName());
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

		content = content.replace("[[URL]]", verifyURL);

		try {
			helper.setFrom(fromAddress, senderName);
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(content, true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			logger.error("Invalid Message");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mailSender.send(message);

		System.out.println("Email has been sent");
	}

	@Override
	public boolean verify(String verificationCode) {
		logger.info("Verification Code Analysed");

		User user = userDAO.findByVerificationCode(verificationCode);
		if (user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			userDAO.save(user);
			return true;
		}

	}

}
