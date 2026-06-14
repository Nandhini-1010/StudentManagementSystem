import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db?serverTimezone=UTC&useSSL=false"; // your DB
    private static final String URL_ROOT = "jdbc:mysql://localhost:3306/?serverTimezone=UTC&useSSL=false"; // connect to server only
    private static final String USER = "root";                                  // your MySQL username
    private static final String PASSWORD = "Nandhini02@";                     // your MySQL password

    public static Connection getConnection() {
        try {
            // Ensure driver is available
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to server (no DB) and create database if missing
            try (Connection rootConn = DriverManager.getConnection(URL_ROOT, USER, PASSWORD);
                 Statement stmt = rootConn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS student_db");
            }

            // Connect to (now-existing) database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Ensure students table exists
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS students (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "name VARCHAR(100), " +
                                "age INT, " +
                                "email VARCHAR(100)" +
                                ")"
                );
            }

            System.out.println("Database connected successfully!");
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}