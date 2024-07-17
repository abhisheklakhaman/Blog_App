package com.blogapp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blogapp.dao.PostDao;
import com.blogapp.model.Post;

public class PostDaoImpl implements PostDao {

	final static String INSERT_QUERY = "INSERT INTO posts (title,content,image,video) VALUES (?,?,?,?)";
	final static String SELECT_QUERY = "SELECT * FROM posts WHERE id = ?";
	final static String SELECTALL_QUERY = "SELECT * FROM posts";
	final static String UPDATE_QUERY = "UPDATE posts SET title = ?, content = ?, image = ?, video = ? WHERE id = ?";
	final static String DELETE_QUERY = "DELETE FROM posts WHERE id = ?";
	final static String SELECT_QUERY_TS = "SELECT * FROM posts WHERE 1=1";
	final static String COUNT_QUERY_TS = "SELECT COUNT(*) FROM posts WHERE 1=1";
	
	static Connection connection;

	public PostDaoImpl() {

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
	public void createPost(Post post) {

		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setString(1, post.getTitle());
			prepareStatement.setString(2, post.getContent());
			prepareStatement.setString(3, post.getImage());
			prepareStatement.setString(4, post.getVideo());

			int executeUpdate = prepareStatement.executeUpdate();
			System.out.println(executeUpdate);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Post getPostById(int id) {

		PreparedStatement prepareStatement = null;
		ResultSet res = null;
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);
			prepareStatement.setInt(1, id);

			res = prepareStatement.executeQuery();
			if (res.next()) {
				Post post = new Post();

				post.setId(res.getInt("id"));
				post.setTitle(res.getString("title"));
				post.setContent(res.getString("content"));
				post.setImage(res.getString("image"));
				post.setVideo(res.getString("video"));

				return post;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> getAllPost() {

		List<Post> posts = new ArrayList<>();
		PreparedStatement prepareStatement = null;
		ResultSet res = null;

		try {
			prepareStatement = connection.prepareStatement(SELECTALL_QUERY);

			res = prepareStatement.executeQuery();
			while (res.next()) {
				Post post = new Post();

				post.setId(res.getInt("id"));
				post.setTitle(res.getString("title"));
				post.setContent(res.getString("content"));
				post.setImage(res.getString("image"));
				post.setVideo(res.getString("video"));

				posts.add(post);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public void updatePost(Post post) {

		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);

			prepareStatement.setString(1, post.getTitle());
			prepareStatement.setString(2, post.getContent());
			prepareStatement.setString(3, post.getImage());
			prepareStatement.setString(4, post.getVideo());
			prepareStatement.setInt(5, post.getId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletePost(int id) {
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Post> getPosts(String searchTitle, String searchDate, int offset, int limit) {

		List<Post> posts = new ArrayList<>();
		PreparedStatement prepareStatement = null;
		ResultSet res = null;

		StringBuilder sql = new StringBuilder(SELECT_QUERY_TS);

		if (searchTitle != null && !searchTitle.isEmpty()) {
			sql.append(" AND title LIKE ?");
		}

		if (searchDate != null && !searchDate.isEmpty()) {
			sql.append(" AND DATE(created_time) = ?");
		}
		sql.append(" LIMIT ? OFFSET ?");

		try {
			prepareStatement = connection.prepareStatement(sql.toString());
			int paramIndex = 1;

			if (searchTitle != null && !searchTitle.isEmpty()) {
				prepareStatement.setString(paramIndex++, "%" + searchTitle + "%");
			}

			if (searchDate != null && !searchDate.isEmpty()) {
				prepareStatement.setString(paramIndex++, searchDate);
			}

			prepareStatement.setInt(paramIndex++, limit);
			prepareStatement.setInt(paramIndex++, offset);

			res = prepareStatement.executeQuery();
			while (res.next()) {
				Post post = new Post();

				post.setId(res.getInt("id"));
				post.setTitle(res.getString("title"));
				post.setContent(res.getString("content"));
				post.setImage(res.getString("image"));
				post.setVideo(res.getString("video"));

				posts.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return posts;
	}

	@Override
	public int countPosts(String searchTitle, String searchDate) {
		
		
		PreparedStatement prepareStatement = null;
		ResultSet res = null;
		
		StringBuilder sql = new StringBuilder(COUNT_QUERY_TS);
		
		if(searchTitle != null && !searchTitle.isEmpty())
		{
			sql.append(" AND title LIKE ?");
		}
		
		if(searchDate != null && !searchDate.isEmpty())
		{
			sql.append(" AND DATE(created_time) = ?");
		}
		
		try {
			prepareStatement = connection.prepareStatement(sql.toString());
			int paramIndex = 1;
			if(searchTitle != null && !searchTitle.isEmpty())
			{
				prepareStatement.setString(paramIndex++, "%" + searchTitle + "%");
			}
			
			if(searchDate != null && !searchDate.isEmpty())
			{
				prepareStatement.setString(paramIndex++, searchDate);
			}
			
			res = prepareStatement.executeQuery();
			if(res.next())
			{
				return res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
