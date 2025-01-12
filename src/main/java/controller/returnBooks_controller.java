package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import model.returnBooks_model;
import view.returnBooks_view;

public class returnBooks_controller {
    private returnBooks_view view;

    // Main constructor
    public returnBooks_controller(returnBooks_view view) {
        this.view = view;
    }

    public void fetchReturnedBook(String studentID, String bookID) {
    if (studentID.isEmpty() || bookID.length() < 1 || bookID.length() > 4) {
        clearFields();
        return;
    }

    try {
        ResultSet rs = returnBooks_model.fetchReturnedBookDetails(studentID, bookID);
        
        // Check if ResultSet is null or empty
        if (rs == null || !rs.next()) {
            // No book found, clear fields
            clearFields();
            JOptionPane.showMessageDialog(view, 
                "No book found for Student ID: " + studentID + ", Book ID: " + bookID, 
                "Book Not Found", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Proceed with fetching details
        String studentName = rs.getString("student_name");
        String bookName = rs.getString("book_name");
        java.sql.Date issuedDate = rs.getDate("issued_date");
        java.sql.Date dueDate = rs.getDate("due_date");
        
        // Format the dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedIssuedDate = issuedDate != null ? dateFormat.format(issuedDate) : "";
        String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";
        
        // Use the view's setter methods
        view.setStudentName(studentName);
        view.setBookName(bookName);
        view.setIssueDate(formattedIssuedDate);
        view.setDueDate(formattedDueDate);
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, 
            "Database Error: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        clearFields();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, 
            "Error: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        clearFields();
    }
}

    public ResultSet fetchAllReturnedBooks() {
        return returnBooks_model.fetchReturnedBooks();
    }

    public boolean saveReturnedBook(String studentID, String studentName, String bookID, String bookName,
                                    String issueDate, String dueDate, java.sql.Date returnDate, String condition,
                                    String damageFees) {
        // Directly call the model's method instead of trying to call through another controller
        return returnBooks_model.saveReturnedBook(studentID, studentName, bookID, bookName, 
                                                  issueDate, dueDate, 
                                                  returnDate.toString(), // Convert to String
                                                  condition, damageFees);
    }

    private void clearFields() {
//        view.getStudentID().setText("");
//        view.getBookID().setText("");
        view.getStudentName().setText("");
        view.getBookName().setText("");
        view.getIssueDate().setText("");
        view.getDueDate().setText("");
        view.getReturnDate().setDate(null);
        view.getConditionOnReturn().setSelectedIndex(0);
        view.getDamegeFees().setSelectedIndex(0);
    }
}