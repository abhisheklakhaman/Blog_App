<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Create Post</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
<style>
    body {
        font-family: 'Roboto', sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 60%;
        margin: 30px auto;
        background: #ffffff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 30px;
        border-radius: 8px;
    }

    h2 {
        color: #333333;
        font-weight: 500;
        margin-bottom: 20px;
        border-bottom: 2px solid #e9ecef;
        padding-bottom: 10px;
    }

    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        margin-bottom: 5px;
        font-weight: 500;
    }

    input[type="text"],
    textarea,
    input[type="file"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ced4da;
        border-radius: 4px;
        font-size: 16px;
        box-sizing: border-box;
    }

    textarea {
        height: 150px;
        resize: vertical;
    }

    input[type="file"] {
        padding: 3px;
    }

    button {
        display: inline-block;
        padding: 12px 24px;
        color: #ffffff;
        background-color: #007bff;
        border: none;
        border-radius: 4px;
        text-decoration: none;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #0056b3;
    }

    a {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        color: #ffffff;
        background-color: #6c757d;
        border: none;
        border-radius: 4px;
        text-decoration: none;
        font-size: 16px;
        transition: background-color 0.3s ease;
    }

    a:hover {
        background-color: #5a6268;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Create New Post</h2>
        <form action="createPost" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" required>
            </div>
            
            <div class="form-group">
                <label for="content">Content</label>
                <textarea id="content" name="content" required></textarea>
            </div>
            
            <div class="form-group">
                <label for="image">Image</label>
                <input type="file" id="image" name="image" accept="image/*">
            </div>
            
            <div class="form-group">
                <label for="video">Video</label>
                <input type="file" id="video" name="video" accept="video/*">
            </div>
            <div class="form-group">
                <button type="submit">Create</button>
            </div>
        </form>
        <a href="admin_dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
