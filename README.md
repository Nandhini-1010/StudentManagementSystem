Student Management System

A Java-based Command Line Interface (CLI) application that performs CRUD (Create, Read, Update, Delete) operations on student records using JDBC and MySQL. The application automatically creates the required database and table at startup, making setup simple and convenient.

### Features

Add new student records
View all student records
Update existing student details
Delete student records
Automatic creation of database and table
Secure database operations using PreparedStatement
Exception handling for reliable execution
User-friendly command-line interface

### Technologies Used

Java
JDBC (MySQL Connector/J)
MySQL
SQL

### Project Structure

StudentManagementSystem/
│
├── src/
│   ├── Main.java
│   ├── DatabaseConnection.java
│   └── StudentDAO.java
│
├── mysql-connector-j.jar
└── README.md


### Database Details

The application automatically creates:

Database: student_db 

Table: students 

```
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL
); 
```

### Prerequisites

Before running the project, ensure you have:

Java JDK 8 or higher
MySQL Server
MySQL Connector/J (JDBC Driver)

### Setup Instructions
1. Clone the Repository
   
```
git clone https://github.com/Nandhini-1010/StudentManagementSystem.git
cd StudentManagementSystem
```

3. Configure MySQL

Update the database credentials in the source code:

```
String url = "jdbc:mysql://localhost:3306/";
String username = "root";
String password = "your_password"; 
```

4. Add JDBC Driver

Download MySQL Connector/J and add it to the project's classpath.

5. Compile the Project
   
``` javac *.java ```

6. Run the Application
   
``` java Main```

### Sample Menu
```

===== Student Management System =====

1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Exit

Enter your choice:

```

### Security

The application uses PreparedStatement for all database operations to help prevent SQL injection attacks and improve query execution.

Through this project, I gained hands-on experience with:

* Java programming
* JDBC database connectivity
* MySQL database management
* SQL queries and CRUD operations
* Exception handling
* Secure database programming using PreparedStatement


### Author

Developed by Nandhini as a learning project to demonstrate Java, JDBC, MySQL, and database management concepts.
