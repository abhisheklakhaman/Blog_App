<%@ page import="java.util.List"%>
<%@ page import="com.blogapp.model.Post"%>
<%@ page import="com.blogapp.model.User"%>

<%
User user = (User) session.getAttribute("user");
boolean isLoggedIn = (user != null);
%>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<style>
body {
	font-family: 'Roboto', sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: 30px auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h2 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

form {
	display: flex;
	justify-content: center;
	margin-bottom: 20px;
}

input[type="text"], input[type="date"] {
	padding: 10px;
	margin-right: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
}

button {
	padding: 10px 20px;
	background-color: #007bff;
	border: none;
	border-radius: 4px;
	color: white;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #0056b3;
}

.post {
	margin-bottom: 20px;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fff;
	box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
	transition: box-shadow 0.3s ease;
}

.post:hover {
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.post h3 {
	margin-top: 0;
	color: #007bff;
}

.post p {
	color: #666;
}

.post a {
	color: #007bff;
	text-decoration: none;
	transition: color 0.3s ease;
}

.post a:hover {
	color: #0056b3;
	text-decoration: underline;
}

.pagination {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.pagination a {
	padding: 10px 15px;
	margin: 0 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	text-decoration: none;
	color: #333;
	transition: background-color 0.3s ease, color 0.3s ease;
}

.pagination a.active {
	background-color: #007bff;
	color: white;
	border-color: #007bff;
}

.pagination a:hover {
	background-color: #0056b3;
	color: white;
	border-color: #0056b3;
}

header {
	background-color: #003366;
	padding: 10px 20px;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
	border-radius: 0 0 8px 8px;
}

header h1 {
	margin: 0;
	font-size: 24px;
}

header nav ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
	display: flex;
}

header nav ul li {
	margin-left: 10px;
}

header nav ul li a {
	color: white;
	text-decoration: none;
	padding: 10px 20px;
	background-color: #0056b3;
	border-radius: 4px;
	transition: background-color 0.3s ease, text-decoration 0.3s ease;
}

header nav ul li a:hover {
	background-color: #003366;
	text-decoration: underline;
}
</style>

</head>
<body>

<header>
		<h1>WELCOME TO BLOG APPLICATION</h1>
		<nav>
			<ul>
				<li><a href="login.jsp" role="button">LogOut</a></li>
			</ul>
		</nav>
	</header>

	<div class="container">
		<h2>Blog Posts</h2>
		<form action="ViewPostServlet" method="get">
			<input type="text" name="searchTitle" placeholder="Search by title">
			<input type="date" name="searchDate" placeholder="Search by date">
			<button type="submit">Search</button>
		</form>

		<%
		List<Post> posts = (List<Post>) session.getAttribute("PostList");
		if (posts != null && !posts.isEmpty()) {
			for (Post p1 : posts) {
		%>

		<div class="post">
			<h3>
				<a href="viewPost?id=<%= p1.getId() %>"><%= p1.getTitle() %></a>
			</h3>
			<p><%= p1.getContent() %></p>
		</div>
		<%
			}
		} else {
		%>
		<p>No Post Available</p>
		<%
		}
		%>

		<div class="pagination">
			<c:if test="${currentPage > 1}">
				<a href="ViewPostServlet?page=${currentPage - 1}">Previous</a>
			</c:if>
			<c:forEach var="i" begin="1" end="${totalPages}">
				<a href="ViewPostServlet?page=${i}"
					class="${i == currentPage ? 'active' : ''}">${i}</a>
			</c:forEach>
			<c:if test="${currentPage < totalPages}">
				<a href="ViewPostServlet?page=${currentPage + 1}">Next</a>
			</c:if>
		</div>
	</div>


</body>
</html>