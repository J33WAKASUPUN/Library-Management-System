package controller;

import ConnectionProvider.ConnectionProvider;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.issueBooks_model;

public class issueBooks_controller {
    private issueBooks_model model;

    public issueBooks_controller() {
        this.model = new issueBooks_model();
    }

    public String getStudentName(String studentId) {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                return null;
            }

            String sql = "SELECT student_name FROM students WHERE student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, studentId);

            ResultSet rs = pst.executeQuery();

            String studentName = rs.next() ? rs.getString("student_name") : "";

            pst.close();
            rs.close();
            con.close();

            return studentName;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public String getBookTitle(String bookId) {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                return null;
            }

            String sql = "SELECT book_title FROM books WHERE book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookId);

            ResultSet rs = pst.executeQuery();

            String bookTitle = rs.next() ? rs.getString("book_title") : "";

            pst.close();
            rs.close();
            con.close();

            return bookTitle;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public boolean saveIssuedBook(issueBooks_model book) {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                return false;
            }

            String sql = "INSERT INTO issued_books (student_id, student_name, book_id, book_name, issued_date, due_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, book.getStudentId());
            pst.setString(2, book.getStudentName());
            pst.setString(3, book.getBookId());
            pst.setString(4, book.getBookTitle());
            pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(book.getIssuedDate()));
            pst.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(book.getDueDate()));

            int result = pst.executeUpdate();
            pst.close();
            con.close();

            return result > 0;
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    public DefaultTableModel fetchIssuedBooks() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                return model;
            }

            String sql = "SELECT issued_id, student_id, student_name, book_id, book_name, issued_date, due_date FROM issued_books";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            String[] columnNames = {"Issue ID", "Student ID", "Student Name", "Book ID", "Book Name", "Issued Date", "Due Date"};
            model.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("issued_id"),
                    rs.getString("student_id"),
                    rs.getString("student_name"),
                    rs.getString("book_id"),
                    rs.getString("book_name"),
                    rs.getString("issued_date"),
                    rs.getString("due_date")
                };
                model.addRow(row);
            }

            pst.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return model;
    }

    public DefaultTableModel searchIssuedBooks(String searchTerm) {
        DefaultTableModel model = new DefaultTableModel();
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                return model;
            }

            String sql = "SELECT issued_id, student_id, student_name, book_id, book_name, issued_date, due_date "
                    + "FROM issued_books "
                    + "WHERE student_id LIKE ? OR student_name LIKE ? OR book_id LIKE ? OR book_name LIKE ?";

            PreparedStatement pst = con.prepareStatement(sql);

            String searchPattern = "%" + searchTerm + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            pst.setString(4, searchPattern);

            ResultSet rs = pst.executeQuery();

            String[] columnNames = {"Issue ID", "Student ID", "Student Name", "Book ID", "Book Name", "Issued Date", "Due Date"};
            model.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("issued_id"),
                    rs.getString("student_id"),
                    rs.getString("student_name"),
                    rs.getString("book_id"),
                    rs.getString("book_name"),
                    rs.getString("issued_date"),
                    rs.getString("due_date")
                };
                model.addRow(row);
            }

            pst.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return model;
    }

    private void handleSQLException(SQLException e) {
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        System.out.println("Error Message: " + e.getMessage());
    }
}
