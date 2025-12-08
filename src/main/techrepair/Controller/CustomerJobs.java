package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBLayer.DbConnection;
import DTO.CustomerJobDto;
import DTO.OrderDto;
import Model.CustomerJobModel;
import Utils.CustomerNotification;

public class CustomerJobs extends JFrame {

    public JPanel backPane;
    private JTable CustomerJobsTable;
    private JTextField OrderIDDDD;
    private JButton markFinshButton;
    private JTextField txtorderId;
    private JTextField txtCustomerId;
    private JTextField txtEmpId;
    private JTextField txtProblm;
    private JButton saveButton;
    private JButton clearButton;
    private JTextField textCname;
    private JTextField textPrice;
    private JScrollPane scrollTable;
    private JButton refreshDataButton;
    CustomerJobModel customerJobModel = new CustomerJobModel();
    private DefaultTableModel tableModel;

    public CustomerJobs() {

        cretaTableUI();
        refreshTable();

        CustomerNotification notification = new CustomerNotification();

        markFinshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   String orderId = OrderIDDDD.getText();

;                try {
                    boolean isOrderExist = customerJobModel.isOrderExist(orderId);
                    if(isOrderExist) {
                        // Get customer Email
                        try {
                            String email = customerJobModel.getCustomerEmailbyOrderId(orderId);
                            notification.updateOrderState(email, "Customer", orderId, "Finished");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        customerJobModel.deleteCustomerOrder(orderId);
                        JOptionPane.showMessageDialog(null, "Fishid the order place send Email to customer", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Suppliers ID not matched!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                    }
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oid = txtorderId.getText();
                String CustomerId = txtCustomerId.getText();
                String problm = txtProblm.getText();
                String EmpId = txtEmpId.getText();
                Double price = Double.parseDouble(textPrice.getText());


                var CustomerJobDyo = new CustomerJobDto(oid,CustomerId,EmpId,problm,price);
                try{
                    boolean isSave = customerJobModel.saveCustomerJob(CustomerJobDyo);
                    if (isSave){
                        JOptionPane.showMessageDialog(null, "Customer job member has been saved!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        // Get customer Email
                        try {
                            String email = customerJobModel.getCustEmail(CustomerId);
                            notification.invoiceEmail(email,"Customer",oid,price);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }catch (SQLException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtorderId.setText("");
                txtCustomerId.setText("");
                txtProblm.setText("");
                txtEmpId.setText("");
                textPrice.setText("");
            }
        });

        refreshDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
    }

    public  void cretaTableUI(){
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Order ID", "Customer ID", "Employee ID", "Problem","Price"});
        CustomerJobsTable.setModel(tableModel);
        scrollTable.setViewportView(CustomerJobsTable);
    }

    public void refreshTable() {
        System.out.println("********.refreshTable().*******");
        try{
            Connection connection = DbConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM `Job`";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.executeQuery();

            tableModel.setRowCount(0);

            while (pstm.getResultSet().next()) {
                String order_id = pstm.getResultSet().getString("Id");
                String customer_id = pstm.getResultSet().getString("CustomerId");
                String employee_id = pstm.getResultSet().getString("EmployeeId");
                String problem = pstm.getResultSet().getString("Problem");
                Double price = pstm.getResultSet().getDouble("Price");
                tableModel.addRow(new String[]{order_id, customer_id, employee_id, problem, "Rs."+ String.valueOf(price)});
            }


        } catch (SQLException ex) {
            // Handle the exception in a user-friendly way
            JOptionPane.showMessageDialog(this, "Error retrieving data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


}
