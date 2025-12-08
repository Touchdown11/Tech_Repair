package Controller;

import DBLayer.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryView extends JFrame {
    public JPanel backPane;
    private JTable InTable;
    private JScrollPane Inven;
    private JButton viewInButton;

    private  DefaultTableModel tableModel;


    public InventoryView() {

        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Supplier Name", "Phone number", "Quantity", "Price", "Address"});
        InTable.setModel(tableModel);
        Inven.setViewportView(InTable);

        refreshTable();

        viewInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                refreshTable();
            }
        });
    }

    private void refreshTable() {
        try {
            System.out.println("********.refreshTable().*******");
            Connection connection = DbConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM Supplier";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();

            // Clear the table model before adding new rows
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                String order_id = resultSet.getString("Id");
                String customer_name = resultSet.getString("Name");
                String email = resultSet.getString("Contact");
                String status = resultSet.getString("Quantity");
                String empNo = Integer.toString(resultSet.getInt("Price"));
                String date = resultSet.getString("Address");

                System.out.println("***customer_name***** " + customer_name);
                System.out.println(order_id);
                System.out.println(email);
                System.out.println(status);
                System.out.println(empNo);
                System.out.println(date);

                // Add row to the table model
                tableModel.addRow(new Object[]{order_id, customer_name, email, status, empNo, date});
                System.out.println("count " + tableModel.getRowCount());
            }

        } catch (SQLException ex) {
            // Handle the exception in a user-friendly way
            JOptionPane.showMessageDialog(this, "Error retrieving data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }




}
