package com.blogapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImpl.PostDaoImpl;

@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {

	private PostDaoImpl postDaoImpl;

	@Override
	public void init() throws ServletException {
		postDaoImpl = new PostDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		postDaoImpl.deletePost(id);

		resp.sendRedirect("AdminDashboardServlet");

	}

}
