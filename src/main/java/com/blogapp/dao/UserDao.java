package com.blogapp.dao;

import java.util.List;

import com.blogapp.model.User;

public interface UserDao {

	void addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User>getAllUser();
	boolean registerUser(User user);
	boolean validateUser(String email,String password);
	User getUserByEmail(String email);
	
}
