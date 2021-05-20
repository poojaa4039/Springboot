package com.CodingBootcamp.service;
import java.util.List;
import com.CodingBootcamp.model.LoggedInUsers;
import com.CodingBootcamp.model.User;

public interface UserService {

	public String login(User user);
    public String register(User user, String siteURL);
    public String emailExist(User user);
    public List<LoggedInUsers> getUserList();
    public List<User> getRegisteredUsers();
    public boolean verify(String verificationCode);
}
