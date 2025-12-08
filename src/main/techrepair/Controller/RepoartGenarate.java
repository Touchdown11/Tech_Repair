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

public class RepoartGenarate extends JFrame {
    public JPanel BackPane;
    private JButton reportButton;
    private JTable RepoartTable;
    private JScrollPane JRepane;
    private  DefaultTableModel tableModel;

    public RepoartGenarate() {

        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Order Id", "Customer", "Price"});
        RepoartTable.setModel(tableModel);
        JRepane.setViewportView(RepoartTable);

        refreshTable();


        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
    }
        private  void refreshTable() {
            try {
                System.out.println("********.refreshTable().*******");
                Connection connection = DbConnection.getInstance().getConnection();
                String SQL = "SELECT * FROM `Job` ";
                PreparedStatement pstm = connection.prepareStatement(SQL);
                ResultSet resultSet = pstm.executeQuery();
                System.out.println("********.refreshTable().*******");

                // Clear the table model
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    String order_id = resultSet.getString("Id");
                    String customer_name = resultSet.getString("CustomerId");
                    String Price = resultSet.getString("Price");
                    System.out.println("********.refreshTable().*******");


                    System.out.println("***customer_name***** "+customer_name);
                    System.out.println(order_id);
                    System.out.println(Price);

                    tableModel.addRow(new String[]{order_id, customer_name, Price});

                }


            } catch (SQLException ex) {
                // Handle the exception in a user-friendly way
                JOptionPane.showMessageDialog(this, "Error retrieving data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }



}
