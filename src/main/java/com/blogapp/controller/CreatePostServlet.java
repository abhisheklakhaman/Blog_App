package com.blogapp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.blogapp.daoImpl.PostDaoImpl;
import com.blogapp.model.Post;

@WebServlet("/createPost")
@MultipartConfig
public class CreatePostServlet extends HttpServlet{

	
	private PostDaoImpl postDaoImpl;
	@Override
	public void init() throws ServletException {
		
		postDaoImpl = new PostDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Part imagePart = req.getPart("image");
		Part videoPart = req.getPart("video");
		
		String imageName = saveFile(imagePart , "images");
		String videoName = saveFile(videoPart, "videos");
	
		Post post = new Post();
		
		post.setTitle(title);
		post.setContent(content);
		post.setImage(imageName);
		post.setVideo(videoName);
		
		postDaoImpl.createPost(post);
		
		resp.sendRedirect("AdminDashboardServlet");
		
	}
	
	private String saveFile(Part part, String folder) throws IOException{
		
		if(part != null && part.getSize() > 0)
		{
			String fileName = extractFileName(part);
			String folderPath = getServletContext().getRealPath("/") + folder;
			File dir = new File(folderPath);
			
			System.out.println("Saving file to folder : " + folderPath);
			System.out.println("File Name : " + fileName);
			
			if(!dir.exists()){
				boolean dirCreated = dir.mkdir();
				System.out.println("Directory Created : " + dirCreated);
			}
			
			String filePath = folderPath + File.separator + fileName;
			System.out.println("Full file path : " + filePath);
			
			part.write(filePath);
			return fileName;
		}
		return null;
	}

	private String extractFileName(Part part) {
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for(String item : items)
		{
			if(item.trim().startsWith("filename"))
			{
				return item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return "";
	}
}
