package com.blogapp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogapp.daoImpl.PostDaoImpl;
import com.blogapp.model.Post;


@WebServlet("/editPost")
public class EditPostServlet extends HttpServlet {

	private PostDaoImpl postDaoImpl;

	@Override
	public void init() throws ServletException {
		postDaoImpl = new PostDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idParam = req.getParameter("id");
		System.out.println("Recieved ID parameter :" + idParam);

		if (idParam != null && !idParam.isEmpty()) {
			try {
				int id = Integer.parseInt(idParam);
				Post postById = postDaoImpl.getPostById(id);

				if (postById != null) {

					req.getSession().setAttribute("post", postById);
					resp.sendRedirect("updatePost.jsp");
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
