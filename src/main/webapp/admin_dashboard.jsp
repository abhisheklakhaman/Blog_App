<%@ page import="java.util.List" %>
<%@ page import="com.blogapp.model.Post" %>
<%@ page import="com.blogapp.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    boolean isLoggedIn = (user != null);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <style>
body {
	font-family: 'Roboto', sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.navbar {
	background-color: #343a40;
	padding: 1.5rem;
	color: #ffffff;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar h2 {
	margin: 0;
}

.navbar-buttons {
	display: flex;
	align-items: center;
}

.btn {
	padding: 10px 20px;
	background-color: #007bff;
	color: #ffffff;
	text-decoration: none;
	border-radius: 4px;
	margin-right: 10px;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #0056b3;
}

.container {
	width: 80%;
	margin: 30px auto;
}

.post {
	background-color: #ffffff;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	margin-bottom: 20px;
	padding: 20px;
}

.post h4 {
	color: #007bff;
	font-weight: 500;
	margin-bottom: 10px;
}

.post p {
	color: #495057;
	line-height: 1.6;
}

.media {
	display: flex;
	gap: 20px;
	margin-bottom: 20px;
}

.media img, .media video {
	max-width: 100%;
	height: auto;
	border-radius: 4px;
}

.actions {
	display: flex;
	justify-content: flex-end;
	margin-top: 10px;
}

.actions a {
	padding: 8px 16px;
	border-radius: 4px;
	text-decoration: none;
	font-size: 14px;
	transition: background-color 0.3s ease;
	margin-left: 10px;
}

.actions a.edit {
	background-color: #17a2b8;
	color: #ffffff;
}

.actions a.edit:hover {
	background-color: #138496;
}

.actions a.delete {
	background-color: #dc3545;
	color: #ffffff;
}

.actions a.delete:hover {
	background-color: #c82333;
}

.logout-box {
	display: flex;
	align-items: center;
}

.logout-button {
	padding: 10px 20px;
	background-color: #007bff;
	color: #ffffff;
	text-decoration: none;
	border-radius: 4px;
	transition: background-color 0.3s ease;
}

.logout-button:hover {
	background-color: #001f4d;
}
</style>
</head>
<body>

<nav class="navbar">
    <h2>Admin Dashboard</h2>
    <div class="navbar-buttons">
        <a href="createPost.jsp" class="btn">Create New Post</a>
        <a href="login.jsp" class="logout-button">Log Out</a>
    </div>
</nav>


<div class="container">
    <h3>Manage Posts</h3>

    <% List<Post> posts = (List<Post>) session.getAttribute("PostList");
       if (posts != null && !posts.isEmpty()) {
           for (Post post : posts) { %>

    <div class="post">
        <h4><%= post.getTitle() %></h4>
        <p><%= post.getContent() %></p>
        <div class="media">
            <img src="../images/<%= post.getImage() %>" alt="<%= post.getTitle() %>">
            <video src="../videos/<%= post.getVideo() %>" controls></video>
        </div>
        <div class="actions">
            <a href="editPost?id=<%= post.getId() %>" class="edit">Edit</a>
            <a href="deletePost?id=<%= post.getId() %>" class="delete">Delete</a>
        </div>
    </div>

    <% }
       } else { %>
    		<p>No Posts Available</p>
    <% } %>

</div>

</body>
</html>
