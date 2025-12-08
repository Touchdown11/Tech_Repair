package Controller;

import DBLayer.DbConnection;
import Utils.SendEmail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class emailSend extends JFrame {
    private JButton sendEmailToCustomerButton;
    public JPanel backPane;
    private JTextField txtCustomerID;
    private JTextArea textContent;
    private JButton sendEmailToEmployyButton;

    public emailSend() {

        SendEmail sendEmail = new SendEmail();
        sendEmailToCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String customer = txtCustomerID.getText();
                    String query = "SELECT * FROM Customer WHERE Id = ?";


                    try (Connection connection = DbConnection.getInstance().getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                        preparedStatement.setString(1, customer);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            String email = resultSet.getString("email");
                            System.out.println(email);
                            sendEmail.sendEmail(email,"Email from TechRepair" ,textContent.getText());
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);


                    }

                }catch (RuntimeException exception){
                    System.out.println(exception);
                }
            }
        });

        sendEmailToEmployyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "SELECT * FROM Employee WHERE Id = ?";

                    try (Connection connection = DbConnection.getInstance().getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            String email = resultSet.getString("email");
                            System.out.println(email);
                            sendEmail.sendEmail(email,"Email from TechRepair" ,textContent.getText());
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }catch (RuntimeException exception){
                    System.out.println(exception);
                }
            }
        });
    }



    public static void main(String[] args) {
        emailSend emailSend = new emailSend();
        emailSend.setContentPane(emailSend.backPane);
        emailSend.setTitle("Send Email");
        emailSend.setSize(700,500);
        emailSend.setVisible(true);
    }
}
