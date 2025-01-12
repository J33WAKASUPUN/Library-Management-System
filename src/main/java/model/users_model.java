package model;

import ConnectionProvider.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class users_model {
    private String name;
    private String username;
    private String password;
    private String email;
    private String tp_number;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setTPNumber(String tp_number) {
        this.tp_number = tp_number;
    }

    public String getTPNumber() {
        return this.tp_number;
    }

    public boolean saveUser() {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                return false;
            }

            String sql = "INSERT INTO users (name, username, password, email, tp_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, getName());
            pst.setString(2, getUsername());
            pst.setString(3, getPassword());
            pst.setString(4, getEmail());
            pst.setString(5, getTPNumber());

            int result = pst.executeUpdate();
            pst.close();
            con.close();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}