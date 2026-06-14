import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = Database.getConnection();
        if (conn == null) {
            System.out.println("Exiting program due to database connection error.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
    
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        sc.nextLine(); // consume newline
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter age: ");
                        int age = sc.nextInt();

                        sc.nextLine();
                        System.out.print("Enter email: ");
                        String email = sc.nextLine();

                        String insertSQL = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                        pstmt.setString(1, name);
                        pstmt.setInt(2, age);
                        pstmt.setString(3, email);
                        pstmt.executeUpdate();

                        System.out.println("Student added successfully!");
                        break;

                    case 2:
                        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
                        System.out.println("\n--- Student List ---");
                        while (rs.next()) {
                            System.out.println(
                                    rs.getInt("id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getInt("age") + " | " +
                                    rs.getString("email")
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter student ID to delete: ");
                        int id = sc.nextInt();

                        String deleteSQL = "DELETE FROM students WHERE id = ?";
                        PreparedStatement delStmt = conn.prepareStatement(deleteSQL);
                        delStmt.setInt(1, id);

                        int rows = delStmt.executeUpdate();
                        if (rows > 0)
                            System.out.println("Student deleted!");
                        else
                            System.out.println("Student not found!");
                        break;

                    case 4:
                        System.out.print("Enter student ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        // fetch current values
                        PreparedStatement selStmt = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
                        selStmt.setInt(1, uid);
                        ResultSet rsu = selStmt.executeQuery();
                        if (!rsu.next()) {
                            System.out.println("Student not found!");
                            break;
                        }

                        String currName = rsu.getString("name");
                        int currAge = rsu.getInt("age");
                        String currEmail = rsu.getString("email");

                        System.out.print("Enter new name (leave blank to keep: " + currName + "): ");
                        String newName = sc.nextLine();

                        System.out.print("Enter new age (0 to keep: " + currAge + "): ");
                        int newAge = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new email (leave blank to keep: " + currEmail + "): ");
                        String newEmail = sc.nextLine();

                        if (newName.isEmpty()) newName = currName;
                        if (newAge == 0) newAge = currAge;
                        if (newEmail.isEmpty()) newEmail = currEmail;

                        String updateSQL = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";
                        PreparedStatement upStmt = conn.prepareStatement(updateSQL);
                        upStmt.setString(1, newName);
                        upStmt.setInt(2, newAge);
                        upStmt.setString(3, newEmail);
                        upStmt.setInt(4, uid);

                        int updated = upStmt.executeUpdate();
                        if (updated > 0)
                            System.out.println("Student updated successfully!");
                        else
                            System.out.println("Update failed.");

                        break;

                    case 5:
                        System.out.println("Exiting...");
                        conn.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}