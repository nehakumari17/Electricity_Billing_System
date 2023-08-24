package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project extends JFrame implements ActionListener {
    String meter;
    Project(String userType, String meter){
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/substation.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("monospaced", Font.PLAIN, 18));

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", Font.PLAIN, 16));
        newCustomer.setBackground(Color.WHITE);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/new customer.png"));
        Image image2 = icon1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(image2));
        newCustomer.addActionListener(this);
        menu.add(newCustomer);

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setBackground(Color.WHITE);
        customerDetails.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/customer details.jpg"));
        Image image3 = icon2.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(image3));
        customerDetails.addActionListener(this);
        menu.add(customerDetails);

        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setBackground(Color.WHITE);
        depositDetails.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/deposit details.png"));
        Image image4 = icon3.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(image4));
        depositDetails.addActionListener(this);
        menu.add(depositDetails);

        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setBackground(Color.WHITE);
        calculateBill.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image image5 = icon4.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(image5));
        calculateBill.addActionListener(this);
        menu.add(calculateBill);

        JMenu information = new JMenu("Information");
        information.setFont(new Font("monospaced", Font.PLAIN, 18));

        JMenuItem updateInformation = new JMenuItem("Update Information");
        updateInformation.setBackground(Color.WHITE);
        updateInformation.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image6 = icon5.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        updateInformation.setIcon(new ImageIcon(image6));
        updateInformation.addActionListener(this);
        information.add(updateInformation);

        JMenuItem viewInformation = new JMenuItem("View Information");
        viewInformation.setBackground(Color.WHITE);
        viewInformation.setFont(new Font("monospaced", Font.PLAIN, 16));
        viewInformation.addActionListener(this);
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/new customer.png"));
        Image image7 = icon6.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        viewInformation.setIcon(new ImageIcon(image7));
        information.add(viewInformation);

        JMenu user = new JMenu("User");
        user.setFont(new Font("monospaced", Font.PLAIN, 18));

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setBackground(Color.WHITE);
        payBill.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/deposit details.png"));
        Image image8 = icon7.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(image8));
        payBill.addActionListener(this);
        user.add(payBill);

        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setBackground(Color.WHITE);
        billDetails.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/bill details.png"));
        Image image9 = icon8.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(image9));
        billDetails.addActionListener(this);
        user.add(billDetails);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("monospaced", Font.PLAIN, 18));

        JMenuItem generateBill = new JMenuItem("Generate Bill");
        generateBill.setBackground(Color.WHITE);
        generateBill.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/bill details.png"));
        Image image10 = icon9.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(image10));
        generateBill.addActionListener(this);
        utility.add(generateBill);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image image11 = icon10.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image11));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setBackground(Color.WHITE);
        calculator.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image image12 = icon11.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(image12));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("monospaced", Font.PLAIN, 18));

        JMenuItem logOut = new JMenuItem("Log out");
        logOut.setBackground(Color.WHITE);
        logOut.setFont(new Font("monospaced", Font.PLAIN, 16));
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/log out.png"));
        Image image13 = icon12.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT);
        logOut.setIcon(new ImageIcon(image13));
        logOut.addActionListener(this);
        exit.add(logOut);

        if (userType.equals("Admin")){
            menuBar.add(menu);
        }
        else {
            menuBar.add(information);
            menuBar.add(user);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        switch (msg) {
            case "New Customer" -> new NewCustomer();
            case "Customer Details" -> new CustomerDetails();
            case "Deposit Details" -> new DepositDetails();
            case "Calculate Bill" -> new CalculateBill();
            case "View Information" -> new ViewInformation(meter);
            case "Update Information" -> new UpdateInformation(meter);
            case "Bill Details" -> new BillDetails(meter);
            case "Pay Bill" -> new PayBill(meter);
            case "Generate Bill" -> new GenerateReceipt(meter);
            case "Notepad" -> {try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch(Exception e){
                System.out.println(e.getMessage());}
            }
            case "Calculator" -> {try{
                Runtime.getRuntime().exec("calc.exe");
            }catch(Exception e){
                System.out.println(e.getMessage());}
            }
            case "Log out" -> {
                setVisible(false);
                new Login();
            }
        }
    }

    public static void main(String[] args){
        new Project("", "");
    }

}
