# 📚 BookWise - Library Management System


![bookwise](./screenshots/bookwise.png)

🌟 Features

1.Dashboard Overview

- Real-time statistics for books and members
- Track total books, issued books, and returned books
- Monitor total member count


2.Member Management

- Add new members
- View member details
- Update member information
- Track membership status


3.Book Management

- Add new books to the library
- Track book availability
- Manage book issuance and returns
- Book search functionality

🛠️ Technical Stack

- Backend: Java
- Database: MySQL
- UI Framework: Java Swing
- IDE: Apache NetBeans

Dependencies:

- AbsoluteLayout-RELEASE210.jar
- jcalendar-1.4.jar
- mysql-connector-java-8.0.30.jar
- protobuf-java-3.19.4.jar

📋 Prerequisites

- JDK 21 or higher
- MySQL Server
- NetBeans IDE (recommended)
- Maven (for dependency management)

🚀 Installation & Setup

1.Clone the Repository then extract target.zip

      git clone https://github.com/J33WAKASUPUN/Library-Management-System.git
      
2.Database Setup

- Create a new MySQL database

-- Create Database

      CREATE DATABASE IF NOT EXISTS lms_new;
      USE lms_new;

-- Create Books Table

    CREATE TABLE IF NOT EXISTS books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    book_title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    publication_year YEAR,
    language VARCHAR(50),
    genre VARCHAR(100),
    copies_available INT DEFAULT 1,
    format VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create Students Table

    CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(255) NOT NULL,
    tp_number VARCHAR(50) UNIQUE,
    address TEXT,
    course VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create Issued Books Table

    CREATE TABLE IF NOT EXISTS issued_books (
    issued_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    student_name VARCHAR(255),
    book_id INT,
    book_name VARCHAR(255),
    issued_date DATE,
    due_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
    );

-- Create Returned Books Table

    CREATE TABLE IF NOT EXISTS returned_books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    student_name VARCHAR(255),
    book_id INT,
    book_name VARCHAR(255),
    issue_date DATE,
    due_date DATE,
    return_date DATE,
    condition_on_return VARCHAR(100),
    damage_fees DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
    );
    
4.Configure Database Connection

- Open ConnectionProvider.java
- Update the database credentials:
 
     String url = "jdbc:mysql://localhost:3306/your_database_name";
     String username = "your_username";
     String password = "your_password";

4.Build and Run

- Open the project in NetBeans IDE
- Build the project
- Run login.java to start the application (PW and UserName - admin)

📁 Project Structure

    LMS_new/
    ├── src/
    │   ├── default package/
    │   │   ├── dashboard.java
    │   │   ├── addBooks.java
    │   │   ├── editStudents.java
    │   │   ├── home.java
    │   │   ├── issuedBooks.java
    │   │   ├── login.java
    │   │   ├── newBooks.java
    │   │   ├── newStudents.java
    │   │   └── returnedBooks.java
    │   └── ConnectionProvider.java
    ├── Dependencies/
    │   ├── AbsoluteLayout-RELEASE210.jar
    │   ├── jcalendar-1.4.jar
    │   ├── mysql-connector-java-8.0.30.jar
    │   └── protobuf-java-3.19.4.jar
    └── README.md

👥 Authors

Initial work - [Jeewaka Supun](https://github.com/J33WAKASUPUN)

- 📧 Email: supunprabodha789@gmail.com
- 🔗 LinkedIn: [ https://lk.linkedin.com/in/jeewaka-supun-221780218]



© 2024 [Jeewaka Supun] | Full-Stack Developer





