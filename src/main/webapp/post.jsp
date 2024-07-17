<%@ page import="com.blogapp.model.Post"%>
<%@ page import="java.util.List"%>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${post.title}</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: 20px auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h2 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

p {
	line-height: 1.6;
	color: #666;
}

img, video {
	display: block;
	margin: 20px auto;
	max-width: 100%;
	height: auto;
	border: 1px solid #ccc;
	border-radius: 8px;
}

a {
	display: block;
	text-align: center;
	margin-top: 20px;
	padding: 10px 20px;
	background-color: #007bff;
	color: white;
	text-decoration: none;
	border-radius: 4px;
}

a:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">

		<%
		List<Post> posts = (List<Post>) session.getAttribute("posts");
		if (posts != null && !posts.isEmpty()) {
			for (Post po : posts) {
		%>

		<h2><%=po.getTitle()%></h2>
		<p><%=po.getContent()%></p>

		<div class="media">
			<img src="../images/<%=po.getImage()%>" alt="<%=po.getTitle()%>">
		</div>

		<div class="media">

			<video src="../videos/<%=po.getVideo()%>" controls></video>
		</div>



		<%
		}
		} else {
		%>

		<p>No Posts Available</p>

		<%
		}
		%>
		<p>
			<a href="index.jsp">Back to Posts</a>
		</p>

	</div>
</body>
</html>
