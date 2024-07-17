package com.blogapp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.blogapp.dao.UserDao;
import com.blogapp.model.User;

public class UserDaoImpl implements UserDao {

	final static String INSERT_QUERY = "INSERT into `users` (`name`,`email`,`password`,`role`) values (?,?,?,?)";
	final static String SELECT_QUERY = "SELECT * from `users` WHERE `id` = ?";
	final static String UPDATE_QUERY = "UPDATE `users` SET `name` = ? ,`email` = ? , `password` = ?, `role` = ? WHERE`userId` = ?";
	final static String DELETE_QUERY = "DELETE from `users` WHERE `userId` = ?";
	final static String SELECTALL_QUERY = "SELECT * FROm `users`";
	final static String REGISTER_QUERY = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
	final static String VALIDATE_QUERY = "SELECT * FROM users WHERE email = ? AND password = ?";
	final static String SELECTBYEMAIL = "SELECT * FROM users WHERE email = ?";
	
	static Connection connection;

	public UserDaoImpl() {
		
		String url = "jdbc:mysql://localhost:3306/blog_app";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {

		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getRole());

			prepareStatement.executeQuery();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {

		PreparedStatement prepareStatement = null;
		ResultSet res = null;
		User user = null;

		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);

			prepareStatement.setInt(1, userId);
			res = prepareStatement.executeQuery();

			if (res.next()) {
				String name = res.getString("name");
				String email = res.getString("email");
				String password = res.getString("password");
				String role = res.getString("role");

				user = new User(userId, name, email, password, role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);

			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getRole());

			prepareStatement.setInt(5, user.getId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {

		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, userId);
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUser() {

		Statement statement = null;
		ResultSet res = null;

		ArrayList<User> userList = new ArrayList<User>();

		try {
			statement = connection.createStatement();
			res = statement.executeQuery(SELECTALL_QUERY);

			while (res.next()) {
				int userId = res.getInt("userId");
				String name = res.getString("name");
				String email = res.getString("email");
				String password = res.getString("password");
				String role = res.getString("role");

				User user = new User(userId, name, email, password, role);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override

	public boolean registerUser(User user) {

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(REGISTER_QUERY);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getRole());

			return prepareStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validateUser(String email, String hashedPassword) {

		PreparedStatement prepareStatement = null;
		ResultSet res = null;
		boolean isValid = false;
		
		try {
			prepareStatement = connection.prepareStatement(VALIDATE_QUERY);
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, hashedPassword);

			res = prepareStatement.executeQuery();

			if (res.next()) {
				isValid = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	@Override
	public User getUserByEmail(String email) {
		
		PreparedStatement prepareStatement =  null;
		ResultSet res = null;
		
		try {
			prepareStatement = connection.prepareStatement(SELECTBYEMAIL);
			prepareStatement.setString(1, email);
			
			res = prepareStatement.executeQuery();
			
			if(res.next())
			{
				User user = new User();
				
				user.setId(res.getInt("id"));
				user.setName(res.getString("name"));
				user.setEmail(res.getString("email"));
				user.setPassword(res.getString("password"));
				user.setRole(res.getString("role"));
				
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
