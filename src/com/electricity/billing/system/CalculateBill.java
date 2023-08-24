package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {

    JLabel customerName, customerAddress;
    Choice meterNumber, months;
    JTextField unitsConsumed;
    JButton submit, cancel;
    Database database = new Database();
    ResultSet resultSet = null;
    CalculateBill(){
        setSize(700,400);
        setLocation(400,150);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 230, 221));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(50,10,400,25);
        heading.setFont(new Font("monospaced", Font.BOLD,24));
        panel.add(heading);

        JLabel meter = new JLabel("Meter Number");
        meter.setBounds(50,60,100,25);
        panel.add(meter);

        meterNumber = new Choice();
        meterNumber.add("SELECT");
        try{
            resultSet = database.statement.executeQuery("select * from customer");
            while (resultSet.next()){
                meterNumber.add(resultSet.getString("meter_no"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        meterNumber.setBounds(170,60,200,25);
        panel.add(meterNumber);

        JLabel name = new JLabel("Name");
        name.setBounds(50,100,100,25);
        panel.add(name);

        customerName = new JLabel("");
        customerName.setBounds(170,100,200,25);
        panel.add(customerName);

        JLabel address = new JLabel("Address");
        address.setBounds(50,140,100,25);
        panel.add(address);

        customerAddress = new JLabel();
        customerAddress.setBounds(170,140,200,25);
        panel.add(customerAddress);

        meterNumber.addItemListener(ie -> {
            try {
                resultSet = database.statement.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
                while(resultSet.next()){
                    customerName.setText(resultSet.getString("name"));
                    customerAddress.setText(resultSet.getString("address"));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        JLabel units = new JLabel("Units Consumed");
        units.setBounds(50,180,100,25);
        panel.add(units);

        unitsConsumed = new JTextField();
        unitsConsumed.setBounds(170,180,200,25);
        panel.add(unitsConsumed);

        JLabel month = new JLabel("Month");
        month.setBounds(50,220,100,25);
        panel.add(month);

        months = new Choice();
        months.setBounds(170,220,200,25);
        months.add("SELECT");
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        panel.add(months);

        submit = new JButton("SUBMIT");
        submit.setBounds(150,300,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.ORANGE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(300,300,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.ORANGE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image i2 = i1.getImage().getScaledInstance(250,200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "East");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String meterNo = meterNumber.getSelectedItem();
            String unitConsumed = unitsConsumed.getText();
            String billMonth = months.getSelectedItem();

            int totalBill = 0;
            int unit_consumed = Integer.parseInt(unitConsumed);

            String sql ="select * from charge";

            try{
                resultSet = database.statement.executeQuery(sql);
                while(resultSet.next()){
                   totalBill += (unit_consumed * Integer.parseInt(resultSet.getString("cost_per_unit")));
                   totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                   totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                   totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

            String sql2 = "insert into bill values('"+meterNo+"', '"+billMonth+"', '"+unitConsumed+"', '"+totalBill+"', 'Not Paid')";

            try{
                database.statement.executeUpdate(sql2);
                
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }else {
            setVisible(false);
        }

    }

    public static void main(String[] args){
        new CalculateBill();
    }
}
