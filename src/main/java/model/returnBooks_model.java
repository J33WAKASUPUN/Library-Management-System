package model;

import ConnectionProvider.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class returnBooks_model {
    public static ResultSet fetchReturnedBooks() {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return null;
            }

            String sql = "SELECT student_id, student_name, book_id, book_name, "
                    + "issue_date, due_date, return_date, condition_on_return, damage_fees "
                    + "FROM returned_books ORDER BY return_date DESC";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public static ResultSet fetchReturnedBookDetails(String studentID, String bookID) {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed!");
            return null;
        }
        
        String sql = "SELECT student_name, book_name, issued_date, due_date " +
                     "FROM issued_books WHERE student_id = ? AND book_id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, studentID);
        pst.setString(2, bookID);
        rs = pst.executeQuery();
        
        // If no results, return null
        if (!rs.isBeforeFirst()) {
            System.out.println("No book found for Student ID: " + studentID + ", Book ID: " + bookID);
            return null;
        }
        
        return rs;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        return null;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

    public static boolean saveReturnedBook(String studentID, String studentName, String bookID, String bookName,
                                          String issueDate, String dueDate, String returnDate, String condition,
                                          String damageFees) {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return false;
            }

            con.setAutoCommit(false);

            // Insert into returned_books
            String insertSql = "INSERT INTO returned_books (student_id, student_name, book_id, book_name, "
                    + "issue_date, due_date, return_date, condition_on_return, damage_fees) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertPst = con.prepareStatement(insertSql);
            insertPst.setString(1, studentID);
            insertPst.setString(2, studentName);
            insertPst.setString(3, bookID);
            insertPst.setString(4, bookName);
            insertPst.setString(5, issueDate);
            insertPst.setString(6, dueDate);
            insertPst.setString(7, returnDate);
            insertPst.setString(8, condition);
            insertPst.setString(9, damageFees);
            insertPst.executeUpdate();

            // Delete from issued_books
            String deleteSql = "DELETE FROM issued_books WHERE student_id = ? AND book_id = ?";
            PreparedStatement deletePst = con.prepareStatement(deleteSql);
            deletePst.setString(1, studentID);
            deletePst.setString(2, bookID);
            deletePst.executeUpdate();

            con.commit();
            insertPst.close();
            deletePst.close();
            con.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
