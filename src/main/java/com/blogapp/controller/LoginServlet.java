package com.blogapp.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogapp.daoImpl.UserDaoImpl;
import com.blogapp.model.User;

@WebServlet("/CallingLoginServlet")
public class LoginServlet extends HttpServlet {

	private UserDaoImpl userDaoImpl;

	@Override
	public void init() throws ServletException {
		userDaoImpl = new UserDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		String hashedPassword = hashPassword(password);

		User user = userDaoImpl.getUserByEmail(email);

		HttpSession session = req.getSession();
		session.setAttribute("currentUser", user);
		
		Integer attempts = (Integer) session.getAttribute("attempts");

		if (attempts == null) {
			attempts = 0;
		}

		if (user != null && !user.getRole().isEmpty()) {
			session.setAttribute("email", email);
			session.setAttribute("role", user.getRole());
			
			if ("admin".equals(user.getRole())) {
				resp.sendRedirect("AdminDashboardServlet");
			} else {
				resp.sendRedirect("AdminDashboardServlet");
			}

		} else {
			attempts++;
			session.setAttribute("attempts", attempts);
			if (attempts >= 3) {
				resp.sendRedirect("login.jsp?error=max_attempts");
			} else {
				resp.sendRedirect("login.jsp?error=invalid_credientials");
			}
		}

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
