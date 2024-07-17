package com.blogapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImpl.PostDaoImpl;
import com.blogapp.model.Post;

@WebServlet("/viewPost")
public class ViewPostDetailServlet extends HttpServlet {

	private PostDaoImpl postDaoImpl;

	@Override
	public void init() throws ServletException {
		postDaoImpl = new PostDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idParam = req.getParameter("id");
		System.out.println("Recieved Id parameter: " + idParam);

		if (idParam != null && !idParam.isEmpty()) {

			try {
				int id = Integer.parseInt(idParam);
				Post post = postDaoImpl.getPostById(id);
				
				if (post != null) {
					
					List<Post> posts = new ArrayList<>();
					posts.add(post);
					
					req.getSession().setAttribute("posts", posts);
					req.getRequestDispatcher("post.jsp").forward(req, resp);
				} else {

					resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");

				}
			} catch (NumberFormatException e) {

				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
		}
	}
}
