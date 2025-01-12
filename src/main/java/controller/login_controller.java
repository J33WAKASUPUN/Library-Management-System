package controller;

import javax.swing.JOptionPane;
import model.login_model;
import view.home_view;
import view.login_view;

public class login_controller {
    
    private login_view view;
    private login_model model;

    public login_controller(view.login_view view, model.login_model model) {
        this.view = view;
        this.model = model;
    }

    public void handleLoginRequest(String username, String password) {
        if (model.authenticateUser(username, password)) {
            
            // Open the home window
            
            home_view homeWindow = new home_view();
            homeWindow.setVisible(true);
            view.dispose();
            
        } else {
            // Display an error message
            JOptionPane.showMessageDialog(view, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
}
