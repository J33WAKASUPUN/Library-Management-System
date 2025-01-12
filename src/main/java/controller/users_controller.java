package controller;

import javax.swing.*;
import model.users_model;
import view.users_view;

public class users_controller {
    private users_view view;
    private users_model model;
    
    // Modified constructor to take the view as a parameter
    public users_controller(users_view view) {
        this.view = view;
        model = new users_model();
    }
    
    // Modified saveUser method to work with the view parameter
    public void saveUser(String name, String username, String password, String email, String tpNumber) {
        model.setName(name);
        model.setUsername(username);
        model.setPassword(password);
        model.setEmail(email);
        model.setTPNumber(tpNumber);
        
        if (model.saveUser()) {
            JOptionPane.showMessageDialog(view, "User Added Successfully!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to add user!");
        }
    }
    
    private void clearFields() {
        view.clearTextFields();
    }
}
