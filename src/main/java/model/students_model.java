package model;

import ConnectionProvider.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class students_model {
    private Connection connection;

    public students_model() {
        connection = ConnectionProvider.getConnection();
    }

    public ResultSet fetchStudents(String status) throws SQLException {
        String sql = "SELECT student_id, student_name, tp_number, address, course, expiration_date, status FROM students WHERE status = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, status);
        return pst.executeQuery();
    }

    public int updateStudent(String studentId, String studentName, String tpNumber, String address, String course, Date expirationDate) throws SQLException {
        String sql = "UPDATE students SET student_name=?, tp_number=?, address=?, course=?, expiration_date=? WHERE student_id=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, studentName);
        pst.setString(2, tpNumber);
        pst.setString(3, address);
        pst.setString(4, course);
        pst.setDate(5, new java.sql.Date(expirationDate.getTime()));
        pst.setString(6, studentId);
        return pst.executeUpdate();
    }

    public int insertStudent(String studentId, String studentName, String tpNumber, String address, String course, Date expirationDate) throws SQLException {
        String sql = "INSERT INTO students (student_id, student_name, tp_number, address, course, expiration_date) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, studentId);
        pst.setString(2, studentName);
        pst.setString(3, tpNumber);
        pst.setString(4, address);
        pst.setString(5, course);
        pst.setDate(6, new java.sql.Date(expirationDate.getTime()));
        return pst.executeUpdate();
    }

    public int updateMemberStatus(String studentId, String newStatus) throws SQLException {
        String sql = "UPDATE students SET status = ? WHERE student_id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, newStatus);
        pst.setString(2, studentId);
        return pst.executeUpdate();
    }
    
    public ResultSet searchStudentById(String studentId) throws SQLException {
    String query = "SELECT * FROM students WHERE student_id = ?";
    PreparedStatement pstmt = connection.prepareStatement(query);
    pstmt.setString(1, studentId);
    return pstmt.executeQuery();
}
}
