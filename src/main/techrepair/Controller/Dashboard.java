package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JButton manageEmployeesButton;
    private JButton manageCustomersButton;
    public JPanel dashboardPane;
    private JButton manageSuppliersButton;
    private JButton inventoryButton;
    private JButton customerJobsButton;
    private JButton monthlyReportsButton;
    private JButton sendEmailButton;
    private JButton customersButton;


    public Dashboard() {

        // Add Icons to buttons
        // manageCustomersButton.setIcon(new ImageIcon("src/main/techrepair/Image/Admin Settings Male.png"));

        manageCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCustomer();
            }
        });
        manageEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmployee();
            }
        });
        manageSuppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSupplires();
            }
        });
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInventory();
            }
        });
        customerJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCustomerJobs();
            }
        });
        monthlyReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gview();
            }
        });
        sendEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmail();
            }
        });
    }

    public void viewCustomer(){
        CustomerUI customerUI = new CustomerUI();
        customerUI.setContentPane(customerUI.backPane);
        customerUI.setTitle("View Customers");
        customerUI.setSize(1000,800);
        customerUI.setVisible(true);
    }

    public void viewEmployee(){
        EmployeeUI employeeUI = new EmployeeUI();
        employeeUI.setContentPane(employeeUI.backPane);
        employeeUI.setTitle("View Employee");
        employeeUI.setSize(1000,800);
        employeeUI.setVisible(true);
    }
    public void viewSupplires(){
        SuppliresUI suppliresUI = new SuppliresUI();
        suppliresUI.setContentPane(suppliresUI.backPane);
        suppliresUI.setTitle("View Suppliers");
        suppliresUI.setSize(1000,800);
        suppliresUI.setVisible(true);
    }
     public void viewInventory(){
         InventoryView inventoryView = new InventoryView();
         inventoryView.setContentPane(inventoryView.backPane);
         inventoryView.setTitle("View Inventory");
         inventoryView.setSize(1000,800);
         inventoryView.setVisible(true);
     }

     public void viewCustomerJobs(){
         CustomerJobs customerJobs = new CustomerJobs();
         customerJobs.setContentPane(customerJobs.backPane);
         customerJobs.setTitle("View Customer Jobs");
         customerJobs.setSize(1000,800);
         customerJobs.setVisible(true);
     }

     public void Gview(){
         RepoartGenarate repoartGenarate = new RepoartGenarate();
         repoartGenarate.setContentPane(repoartGenarate.BackPane);
         repoartGenarate.setTitle("Report Generate");
         repoartGenarate.setSize(800,600);
         repoartGenarate.setVisible(true);
     }

     public void viewEmail(){
         emailSend emailSend = new emailSend();
         emailSend.setContentPane(emailSend.backPane);
         emailSend.setTitle("View Email");
         emailSend.setSize(1000,800);
         emailSend.setVisible(true);
     }
    public void initializeComponents() {
    }
}
