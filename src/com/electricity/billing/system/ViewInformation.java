package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {
    JButton cancel;
    Database database = new Database();
    ResultSet resultSet;
    ViewInformation(String meter){
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif", Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(70,80,100,20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(250,80,100,20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(70, 140,100,20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(250,140,100,20);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(250,200,100,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(250,260,100,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(450,80,100,20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(550,80,100,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(450,140,100,20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(550,140,300,20);
        add(emailText);

        JLabel phoneNo = new JLabel("Phone Number");
        phoneNo.setBounds(450,200,100,20);
        add(phoneNo);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(550,200,100,20);
        add(phoneText);

        try{
            resultSet = database.statement.executeQuery("select * from customer where meter_no = '"+meter+"'");
            if(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("gmail"));
                phoneText.setText(resultSet.getString("mobile_no"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        cancel = new JButton("Cancel");
        cancel.setBounds(350,340,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        cancel.setForeground(Color.WHITE);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/view customer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20,350,600,300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }

    public static void main(String[] args){
        new ViewInformation("");
    }

}
