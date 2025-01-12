package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.students_model;
import view.students_view;

public class students_controller {
    private students_view view;
    private students_model model;

    public students_controller(students_view view) {
        this.view = view;
        this.model = new students_model();
    }

    public void setupTable() {
        String[] columnNames = {"Student ID", "Student Name", "Address", "T.P Number", "Course", "Expiration Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        view.getStudentsTable().setModel(model);
    }

    public void fetchAndDisplayStudents(String status) {
        try {
            ResultSet rs = model.fetchStudents(status);
            DefaultTableModel model = (DefaultTableModel) view.getStudentsTable().getModel();
            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Object[] row = {
                    rs.getString("student_id"),
                    rs.getString("student_name"),
                    rs.getString("address"),
                    rs.getString("tp_number"),
                    rs.getString("course"),
                    rs.getString("expiration_date"),
                    rs.getString("status")
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
        view.getStudentsTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getStudentsTable().getSelectedRow();
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
        view.getStudentID().setText(view.getStudentsTable().getValueAt(selectedRow, 0).toString());
        view.getStudentName().setText(view.getStudentsTable().getValueAt(selectedRow, 1).toString());
        view.getAddress().setText(view.getStudentsTable().getValueAt(selectedRow, 2).toString());
        view.getTPNumber().setText(view.getStudentsTable().getValueAt(selectedRow, 3).toString());
        view.getCourse().setSelectedItem(view.getStudentsTable().getValueAt(selectedRow, 4).toString());

        String dateString = view.getStudentsTable().getValueAt(selectedRow, 5).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        view.getExpirationDate().setDate(date);
    }

    public void handleSaveOrUpdate() {
        if (view.getSaveButton().getText().equals("Update")) {
            updateStudent();
        } else {
            insertStudent();
        }
    }

    private void updateStudent() {
        try {
            String studentId = view.getStudentID().getText();
            String studentName = view.getStudentName().getText();
            String address = view.getAddress().getText();
            String tpNumber = view.getTPNumber().getText();
            String course = view.getCourse().getSelectedItem().toString();
            Date expirationDate = view.getExpirationDate().getDate();

            int result = model.updateStudent(studentId, studentName, tpNumber, address, course, expirationDate);
            if (result > 0) {
                JOptionPane.showMessageDialog(view, "Student Updated Successfully!");
                clearFields();
                fetchAndDisplayStudents("active");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update student! Ensure the student ID exists.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error updating student: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void insertStudent() {
        try {
            String studentId = view.getStudentID().getText();
            String studentName = view.getStudentName().getText();
            String address = view.getAddress().getText();
            String tpNumber = view.getTPNumber().getText();
            String course = view.getCourse().getSelectedItem().toString();
            Date expirationDate = view.getExpirationDate().getDate();

            int result = model.insertStudent(studentId, studentName, tpNumber, address, course, expirationDate);
            if (result > 0) {
                JOptionPane.showMessageDialog(view, "Student Added Successfully!");
                clearFields();
                fetchAndDisplayStudents("active");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add student!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        view.getStudentID().setText("");
        view.getStudentName().setText("");
        view.getAddress().setText("");
        view.getTPNumber().setText("");
        view.getSearchField().setText("");
        view.getCourse().setSelectedIndex(0);
        view.getExpirationDate().setDate(null);
        view.getSaveButton().setText("Save");
    }

    public void handleStatusChange() throws SQLException {
        int selectedRow = view.getStudentsTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a member to change status");
            return;
        }

        String studentId = view.getStudentsTable().getValueAt(selectedRow, 0).toString();
        String currentStatus = view.getStudentsTable().getValueAt(selectedRow, 6).toString();

        String newStatus;
        String confirmationMessage;

        if (currentStatus.equalsIgnoreCase("active")) {
            newStatus = "inactive";
            confirmationMessage = "Are you sure you want to inactivate member with ID: " + studentId + "?";
        } else {
            newStatus = "active";
            confirmationMessage = "Are you sure you want to activate member with ID: " + studentId + "?";
        }

        int confirm = JOptionPane.showConfirmDialog(view, confirmationMessage, "Confirm Status Change", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.updateMemberStatus(studentId, newStatus);
            clearFields();
            fetchAndDisplayStudents(newStatus);
        }
    }

    public void handleSearch() {
    String searchId = view.getSearchField().getText().trim();
    if (searchId.isEmpty()) {
        clearSearch();
        return;
    }

    try {
        // Use a specific method to search by student ID, passing the ID
        ResultSet rs = model.searchStudentById(searchId);
        DefaultTableModel model = (DefaultTableModel) view.getStudentsTable().getModel();
        model.setRowCount(0); // Clear existing rows

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = {
                rs.getString("student_id"),
                rs.getString("student_name"),
                rs.getString("address"),
                rs.getString("tp_number"),
                rs.getString("course"),
                rs.getString("expiration_date"),
                rs.getString("status")
            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(view, "No student found with ID: " + searchId);
            clearSearch();
        }

        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Error searching data: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void clearSearch() {
        view.getSearchField().setText("");
        fetchAndDisplayStudents("active");
    }
}
