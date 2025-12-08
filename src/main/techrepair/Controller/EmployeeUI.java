package Controller;

import DBLayer.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeUI  extends JFrame {
    private JButton updateEmployeeButton;
    private JButton deleteEmployeeButton;
    private JButton ADDEmployeeButton;
    public JPanel backPane;
    private JTable employeeTable;
    private JScrollPane tableScroll;
    private JButton refreshDataBtn;

    private DefaultTableModel tableModel;

    public EmployeeUI() {

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Duty", "Email"});
        employeeTable.setModel(tableModel);
        tableScroll.setViewportView(employeeTable);

        refreshTable();

        ADDEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAddEmployee();
            }
        });
        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateEmp();
            }
        });
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmp();
            }
        });

        refreshDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
    }

    public void refreshTable() {
        System.out.println("********.refreshTable().*******");
        try{
            Connection connection = DbConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM Employee";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.executeQuery();

            tableModel.setRowCount(0);

            while (pstm.getResultSet().next()) {
                String id = pstm.getResultSet().getString("Id");
                String name = pstm.getResultSet().getString("Name");
                String duty = pstm.getResultSet().getString("Duty");
                String email = pstm.getResultSet().getString("Email");
                tableModel.addRow(new String[]{id, name, duty, email});
            }


        } catch (SQLException ex) {
            // Handle the exception in a user-friendly way
            JOptionPane.showMessageDialog(this, "Error retrieving data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void viewAddEmployee() {
        AddToEmployee addToEmployeea = new AddToEmployee();
        addToEmployeea.setContentPane(addToEmployeea.backpane);
        addToEmployeea.setTitle("Add Employee");
        addToEmployeea.setSize(800,600);
        addToEmployeea.setVisible(true);
    }

    public void UpdateEmp(){

        UpdateEmployee updateEmployee = new UpdateEmployee();
        updateEmployee.setContentPane(updateEmployee.backPane);
        updateEmployee.setTitle("Update Employee");
        updateEmployee.setSize(800,600);
        updateEmployee.setVisible(true);
    }

    public void deleteEmp(){

        DeleteEmp deleteEmp = new DeleteEmp();
        deleteEmp.setContentPane(deleteEmp.backPane);
        deleteEmp.setTitle("Delete Employee");
        deleteEmp.setSize(800,600);
        deleteEmp.setVisible(true);
    }
}
