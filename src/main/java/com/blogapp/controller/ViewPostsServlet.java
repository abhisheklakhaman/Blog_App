package com.blogapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImpl.PostDaoImpl;
import com.blogapp.model.Post;

@WebServlet("/ViewPostServlet")
public class ViewPostsServlet extends HttpServlet {

	private static final int POSTS_PER_PAGE = 5;

	private PostDaoImpl postDaoImpl;

	@Override
	public void init() throws ServletException {

		postDaoImpl = new PostDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchTitle = req.getParameter("searchTitle");
		String searchDate = req.getParameter("searchDate");
		int currentPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));

		List<Post> posts = postDaoImpl.getPosts(searchTitle, searchDate, (currentPage - 1) * POSTS_PER_PAGE,
				POSTS_PER_PAGE);
		
		int totalPosts = postDaoImpl.countPosts(searchTitle, searchDate);
		int totalPages = (int) Math.ceil((double) totalPosts / POSTS_PER_PAGE);

		req.setAttribute("posts", posts);
		
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("totalPages", totalPages);

//		req.getRequestDispatcher("index.jsp").forward(req, resp);
		resp.sendRedirect("viewPost");

	}
}
