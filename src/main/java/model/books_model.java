package model;

import ConnectionProvider.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class books_model {

    private Connection connection;

    public books_model() {
        connection = ConnectionProvider.getConnection();
    }

    public ResultSet fetchBooks(String status) throws SQLException {
        String sql = "SELECT book_id, book_title, author, publication_year, language, copies_available, genre, format, status FROM books WHERE status = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, status);
        return pst.executeQuery();
    }

    public boolean saveBook(String bookTitle, String author, String publicationYear, String language, String copiesAvailable, String genre, String format) {
        try {
            String sql = "INSERT INTO books (book_title, author, publication_year, language, copies_available, genre, format, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'active')";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, bookTitle);
            pst.setString(2, author);
            pst.setString(3, publicationYear);
            pst.setString(4, language);
            pst.setString(5, copiesAvailable);
            pst.setString(6, genre);
            pst.setString(7, format);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saving book: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(String bookId, String bookTitle, String author, String publicationYear, String language, String copiesAvailable, String genre, String format) {
        try {
            String sql = "UPDATE books SET book_title = ?, author = ?, publication_year = ?, language = ?, copies_available = ?, genre = ?, format = ? WHERE book_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, bookTitle);
            pst.setString(2, author);
            pst.setString(3, publicationYear);
            pst.setString(4, language);
            pst.setString(5, copiesAvailable);
            pst.setString(6, genre);
            pst.setString(7, format);
            pst.setString(8, bookId);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating book: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBook(String bookId) {
        try {
            String sql = "UPDATE books SET status = 'inactive' WHERE book_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, bookId);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting book: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet searchBooksById(String searchTerm) throws SQLException {
    String sql = "SELECT book_id, book_title, author, publication_year, language, copies_available, genre, format FROM books WHERE (book_id LIKE ? OR author LIKE ? OR book_title LIKE ?) AND status = 'active'";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    String searchPattern = "%" + searchTerm + "%";
    pstmt.setString(1, searchPattern);
    pstmt.setString(2, searchPattern);
    pstmt.setString(3, searchPattern);
    return pstmt.executeQuery();
}

}
