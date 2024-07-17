<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Register</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f4f8; /* Soft blue-gray background for a professional look */
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    background-color: #ffffff; /* White background for the form container */
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    width: 100%;
}

h2 {
    margin-top: 0;
    text-align: center;
    color: #003366; /* Dark blue color for the heading */
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    color: #003366; /* Dark blue color for labels */
}

input[type="text"],
input[type="email"],
input[type="password"],
select {
    width: 100%;
    padding: 12px;
    box-sizing: border-box;
    border: 1px solid #b0c4de; /* Light blue-gray border */
    border-radius: 4px;
    transition: border-color 0.3s;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="password"]:focus,
select:focus {
    border-color: #003366; /* Dark blue on focus */
}

button {
    width: 100%;
    background-color: #003366; /* Dark blue background for buttons */
    color: white;
    padding: 12px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #002244; /* Darker blue on hover */
}

p {
    text-align: center;
    margin-top: 20px;
    color: #002244; /* Darker blue for the text */
}

a {
    color: #003366; /* Dark blue color for links */
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>

<div class="container">
    <h2>Register</h2>
    <form action="CallingRegisterServlet" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required />
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="Admin">Admin</option>
                <option value="Viewer">Viewer</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit">Register</button>
        </div>
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</div>

</body>
</html>
