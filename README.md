# Blog Application with User Roles and Authentication

## Project Overview
This project is a web application for managing blog posts with user roles and authentication. The application has two user roles: Admin and Viewer.

### Technologies Used
- Java Servlets (backend)
- JSP (frontend)
- MySQL Database

## Project Structure

Sure, here's a README.md file that outlines the steps and details for your Blog Application project:

markdown
Copy code
# Blog Application with User Roles and Authentication

## Project Overview
This project is a web application for managing blog posts with user roles and authentication. The application has two user roles: Admin and Viewer.

### Technologies Used
- Java Servlets (backend)
- JSP (frontend)
- MySQL Database


## Project Structure
BlogApp/
├── src/
│ ├── com/
│ │ ├── blogapp/
│ │ │ ├── controller/
│ │ │ │ ├── LoginServlet.java
│ │ │ │ ├── RegisterServlet.java
│ │ │ │ ├── AdminServlet.java
│ │ │ │ ├── CreatePostServlet.java
│ │ │ │ ├── UpdatePostServlet.java
│ │ │ │ ├── DeletePostServlet.java
│ │ │ ├── dao/
│ │ │ │ ├── UserDao.java
│ │ │ │ ├── PostDao.java
│ │ │ ├── daoImpl/
│ │ │ │ ├── UserDaoImpl.java
│ │ │ │ ├── PostDaoImpl.java
│ │ │ ├── model/
│ │ │ │ ├── User.java
│ │ │ │ ├── Post.java
│ │ │ ├── util/
│ │ │ │ ├── DBUtil.java
├── WebContent/
│ ├── META-INF/
│ ├── WEB-INF/
│ │ ├── lib/
│ │ │ 
│ │ ├── web.xml
│ │
│ │ 
│ ├── js/
│ │ 
│ ├── admin_dashboard.jsp
│ ├── viewer_index.jsp
│ ├── login.jsp
│ ├── register.jsp
│ ├── createPost.jsp
│ ├── updatePost.jsp
│ ├── deletePost.jsp
└── README.md

## Setup Instructions

## Setup Instructions

### Prerequisites
- JDK 8 or higher
- Apache Tomcat 8 or higher
- MySQL Database
- Eclipse IDE for Java EE Developers

### Steps to Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd BlogApp


2. Set up the MySQL Database

Create a new database named blogapp.

Run the following SQL script to create the necessary tables:

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image VARCHAR(255),
    video VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

Import the project into Eclipse

Open Eclipse IDE.
File -> Import -> Existing Projects into Workspace -> Select the BlogApp directory.
Add the BCrypt library

Download the jbcrypt-0.4.jar file.
Copy the JAR file to the WEB-INF/lib directory.
Right-click on the project -> Build Path -> Configure Build Path -> Add JARs -> Select the jbcrypt-0.4.jar from the WEB-INF/lib directory.
Configure the DBUtil class

Open DBUtil.java and update the database connection details.

private static final String URL = "jdbc:mysql://localhost:3306/blogapp";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

Deploy and run the project

Right-click on the project -> Run As -> Run on Server -> Select Apache Tomcat.

Usage

1. Register a new user
  Navigate to http://localhost:8080/BlogApp/register.jsp.
  Fill in the registration form and submit.
2. Login
  Navigate to http://localhost:8080/BlogApp/login.jsp.
  Fill in the login form and submit.
3. Admin Dashboard
  After logging in as an Admin, you will be redirected to the Admin Dashboard (admin_dashboard.jsp).
  Create, update, and delete blog posts from the dashboard.
4.Viewer Interface
  Viewers can browse and search for blog posts on the main page (viewer_index.jsp).

Security Enhancements

Passwords are hashed using BCrypt before storing in the database.
User input is validated and sanitized to prevent SQL injection attacks.
Session management is implemented to track user roles and prevent unauthorized access.
