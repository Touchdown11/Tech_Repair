package Controller;

import DBLayer.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerUI extends JFrame {
    public JPanel backPane;
    private JButton updateCustomerButton;
    private JButton deleteCustomerButton;
    private JButton ADDCustomerButton;
    private JScrollPane scrollTable;
    private JTable tableCustomers;
    private JButton refreshDataButton;

    private DefaultTableModel tableModel;

    public CustomerUI() {

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Contact", "Email"});
        tableCustomers.setModel(tableModel);
        scrollTable.setViewportView(tableCustomers);

        refreshTable();

        ADDCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAddCustomer();
            }
        });

        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {viewDeleteCustomer();}
        });
        updateCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUpdateCustomer();
            }
        });

        refreshDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
    }

    public void refreshTable() {
        try{
            System.out.println("********.refreshTable().*******");
            Connection connection = DbConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM Customer";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.executeQuery();

            // Clear the table model
            tableModel.setRowCount(0);

            // Populate the table model with data from the database
            var resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                String customerID = resultSet.getString("Id");
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String email = resultSet.getString("email");

                tableModel.addRow(new Object[]{customerID, name, contact, email});
            }


        } catch (SQLException ex) {
            // Handle the exception in a user-friendly way
            JOptionPane.showMessageDialog(this, "Error retrieving data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void viewAddCustomer() {
        AddToCustomer addToCustomer = new AddToCustomer();
        addToCustomer.setContentPane(addToCustomer.backPane);
        addToCustomer.setTitle("Add Customer");
        addToCustomer.setSize(800,600);
        addToCustomer.setVisible(true);

    }

    public void viewUpdateCustomer() {
        UpdateCustomer updateCustomer = new UpdateCustomer();
        updateCustomer.setContentPane(updateCustomer.backPane);
        updateCustomer.setTitle("View Customer");
        updateCustomer.setSize(800,600);
        updateCustomer.setVisible(true);

    }

    public void  viewDeleteCustomer(){
        DeleteCustomer deleteCustomer = new DeleteCustomer();
        deleteCustomer.setContentPane(deleteCustomer.backPane);
        deleteCustomer.setTitle("Delete Customer");
        deleteCustomer.setSize(800,600);
        deleteCustomer.setVisible(true);
    }
}
