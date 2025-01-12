package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.books_model;
import view.books_view;

public class books_controller {
    private books_model model;
    private books_view view;

    public books_controller(books_view view) {
        this.view = view;
        this.model = new books_model();
    }

    public void setupTable() {
        String[] columnNames = {"Book ID", "Book Title", "Author", "Publication Year", "Languge","Copies NO. ", "Genre", "Format"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        view.getBooksTable().setModel(model);
    }
    
    public void fetchAndDisplayBooks(String status) {
        try {
            ResultSet rs = model.fetchBooks(status);
            DefaultTableModel model = (DefaultTableModel) view.getBooksTable().getModel();
            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Object[] row = {
                    rs.getString("book_id"),
                    rs.getString("book_title"),
                    rs.getString("author"),
                    rs.getString("publication_year"),
                    rs.getString("language"),
                    rs.getString("copies_available"),
                    rs.getString("genre"),
                    rs.getString("format")
                };
                model.addRow(row);
            }

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setupTableSelectionListener() {
        view.getBooksTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getBooksTable().getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        populateInputFields(selectedRow);
                        view.getSaveButton().setText("Update");
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(view, "Invalid date format: " + ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "An error occurred: " + ex.getMessage());
                    }
                }
            }
        });
    }
    
     private void populateInputFields(int selectedRow) throws ParseException {
    // Change the column index for book_id from 0 to the correct column
    // Assuming book_id is the first column in the table
    String bookId = view.getBooksTable().getValueAt(selectedRow, 0).toString();
    
    // Set the fields with correct column indices
    view.getBookTitle().setText(view.getBooksTable().getValueAt(selectedRow, 1).toString());
    view.getAuthor().setText(view.getBooksTable().getValueAt(selectedRow, 2).toString());
    view.getPublicationYear().setText(view.getBooksTable().getValueAt(selectedRow, 3).toString());
    view.getLanguage().setText(view.getBooksTable().getValueAt(selectedRow, 4).toString());
    view.getCopiesAvailable().setText(view.getBooksTable().getValueAt(selectedRow, 5).toString());
    view.getGenre().setSelectedItem(view.getBooksTable().getValueAt(selectedRow, 6).toString());
    view.getFormat().setSelectedItem(view.getBooksTable().getValueAt(selectedRow, 7).toString());
    
    // Store the book_id as a temporary variable or in a hidden field
    view.getBookTitle().putClientProperty("bookId", bookId);
}

    public void handleSaveOrUpdate() {
        if (view.getSaveButton().getText().equals("Update")) {
            updateBook();
        } else {
            saveBook();
        }
    }
    
private void updateBook() {
    try {
        // Retrieve the book_id from the client property
        String bookId = (String) view.getBookTitle().getClientProperty("bookId");
        
        if (bookId == null) {
            JOptionPane.showMessageDialog(view, "Please select a book to update!");
            return;
        }
        
        String bookTitle = view.getBookTitle().getText();
        String author = view.getAuthor().getText();
        String publicationYear = view.getPublicationYear().getText();
        String language = view.getLanguage().getText();
        String copiesAvailable = view.getCopiesAvailable().getText();
        String genre = view.getGenre().getSelectedItem().toString();
        String format = view.getFormat().getSelectedItem().toString();
        
        boolean result = model.updateBook(bookId, bookTitle, author, publicationYear, language, copiesAvailable, genre, format);
        
        if (result) {
            JOptionPane.showMessageDialog(view, "Book Updated Successfully!");
            clearFields();
            fetchAndDisplayBooks("active");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update book!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Unexpected error: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void saveBook() {
        try {
            String bookTitle = view.getBookTitle().getText();
            String author = view.getAuthor().getText();
            String publicationYear = view.getPublicationYear().getText();
            String language = view.getLanguage().getText();
            String copiesAvailable = view.getCopiesAvailable().getText();
            String genre = view.getGenre().getSelectedItem().toString();
            String format = view.getFormat().getSelectedItem().toString();

            boolean result = model.saveBook(bookTitle, author, publicationYear, language, copiesAvailable, genre, format);
            if (result) {
                JOptionPane.showMessageDialog(view, "Book Added Successfully!");
                clearFields();
                fetchAndDisplayBooks("active");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add book!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

 public void handleSearch() {
    String searchId = view.getSearchField().getText().trim();
    if (searchId.isEmpty()) {
        clearSearch();
        return;
    }

    try {
        ResultSet rs = model.searchBooksById(searchId);
        DefaultTableModel model = (DefaultTableModel) view.getBooksTable().getModel();
        model.setRowCount(0); // Clear existing rows

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = {
                rs.getString("book_id"),
                rs.getString("book_title"),
                rs.getString("author"),
                rs.getString("publication_year"),
                rs.getString("language"),
                rs.getString("copies_available"),
                rs.getString("genre"),
                rs.getString("format")

            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(view, "No book found with ID: " + searchId);
            clearSearch();
        }

        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Error searching data: " + e.getMessage());
        e.printStackTrace();
    }
}
 
 public void removeBooks(String status) {
    int selectedRow = view.getBooksTable().getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(view, "Please select a book to remove!");
        return;
    }
    
    try {
        // Get the book ID from the selected row
        String bookId = view.getBooksTable().getValueAt(selectedRow, 0).toString();
        
        // Ask for confirmation before deletion
        int confirm = JOptionPane.showConfirmDialog(
            view, 
            "Are you sure you want to remove this book?", 
            "Confirm Removal", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = model.deleteBook(bookId);
            
            if (result) {
                JOptionPane.showMessageDialog(view, "Book removed successfully!");
                fetchAndDisplayBooks("active");  // Refresh the table with active books
                clearFields();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to remove book!");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Error removing book: " + e.getMessage());
        e.printStackTrace();
    }
}


    private void clearFields() {
        view.getBookTitle().setText("");
        view.getAuthor().setText("");
        view.getPublicationYear().setText("");
        view.getLanguage().setText("");
        view.getCopiesAvailable().setText("");
        view.getGenre().setSelectedIndex(0);
        view.getFormat().setSelectedIndex(0);
         view.getSaveButton().setText("Save");
    }

    private void clearSearch() {
        view.getSearchField().setText("");
        fetchAndDisplayBooks("active");
    }
}
