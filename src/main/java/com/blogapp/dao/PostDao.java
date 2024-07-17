package com.blogapp.dao;

import java.util.List;

import com.blogapp.model.Post;

public interface PostDao {
	
	void createPost(Post post);
	Post getPostById(int id);
	List<Post> getAllPost();
	List<Post> getPosts(String searchTitle, String searchDate, int offset, int limit);
    int countPosts(String searchTitle, String searchDate);
	void updatePost(Post post);
	void deletePost(int id);

}
