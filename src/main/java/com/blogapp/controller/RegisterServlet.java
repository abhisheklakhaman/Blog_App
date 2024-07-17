package com.blogapp.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImpl.UserDaoImpl;
import com.blogapp.model.User;

@WebServlet("/CallingRegisterServlet")
public class RegisterServlet extends HttpServlet {

	private UserDaoImpl userDaoImpl;

	@Override
	public void init() throws ServletException {

		userDaoImpl = new UserDaoImpl();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		String hashedPassword = hashPassword(password);

		if (name.isEmpty() || email.isEmpty() || hashedPassword.isEmpty() || role.isEmpty()) {
			resp.sendRedirect("login.jsp?error=missingField");
			return;
		}

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(hashedPassword);
		user.setRole(role);

		userDaoImpl.registerUser(user);

		resp.sendRedirect("login.jsp");
	}

	private String hashPassword(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

}
