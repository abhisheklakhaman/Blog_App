package com.blogapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogapp.daoImpl.PostDaoImpl;
import com.blogapp.daoImpl.UserDaoImpl;
import com.blogapp.model.Post;
import com.blogapp.model.User;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostDaoImpl postDaoImpl = new PostDaoImpl();
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		
		if(currentUser != null)
		{
			String userEmail = currentUser.getEmail();
			
			User user = userDaoImpl.getUserByEmail(userEmail);
			
			
			if(user != null && user.getRole().equals("admin"))
			{
				List<Post> allPost = postDaoImpl.getAllPost();
				session.setAttribute("PostList", allPost);
				
				resp.sendRedirect("admin_dashboard.jsp");
				
			}else if(user != null && user.getRole().equals("viewer"))
			{
				resp.sendRedirect("index1.jsp");
			}else {
				resp.sendRedirect("login.jsp");
			}
		}else {
			resp.sendRedirect("login.jsp");
		}

	}
}
