package com.blogapp.model;

public class Post {

	private int id;
	private String title;
	private String content;
	private String image;
	private String video;

	public Post() {

	}

	public Post(int id, String title, String content, String image, String video) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

}
