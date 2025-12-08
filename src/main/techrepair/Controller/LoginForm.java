package Controller;

import Model.UserModle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginForm extends JFrame{


    private JTextField userName;
    private JPasswordField txtPassword;
    private JButton BtnLogin;
    private JPanel backPane;

    private static final String HARDCODED_USERNAME = "admin";
    private static final String HARDCODED_PASSWORD = "password123";

    UserModle userModle = new UserModle();
    public LoginForm() {
        BtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userNameText = userName.getText();
                String pw = (txtPassword.getText());

                if (HARDCODED_USERNAME.equals(userNameText) && HARDCODED_PASSWORD.equals(pw)) {
                    viewDasboard();
                    return;
                }

                try {
                    boolean isValid = userModle.isValidUser(userNameText, pw);
                    if (isValid) {
                        viewDasboard();
                    } else {
                        JOptionPane.showMessageDialog(null, "User Name And Password Did Not Matched. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void viewDasboard(){
        Dashboard dashboard = new Dashboard();
        dashboard.setContentPane(dashboard.dashboardPane);
        dashboard.setTitle("Dashboard TechRepair");
        dashboard.setSize(700,800);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.initializeComponents();
        setVisible(false);
        dashboard.setVisible(true);
    }

    public static void main(String[]args){

        LoginForm loginForm = new LoginForm();
        loginForm.setContentPane(loginForm.backPane);
        loginForm.setTitle("Login TechRepair");
        loginForm.setSize(700,600);
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginForm.setVisible(true);

    }
}


