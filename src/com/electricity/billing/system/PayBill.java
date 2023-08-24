package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class PayBill extends JFrame implements ActionListener {
    JButton pay, back;
    Choice monthChoice;
    Database database = new Database();
    ResultSet resultSet;
    String meterNo;
    PayBill(String meterNo){
        this.meterNo = meterNo;

        setLayout(null);
        setBounds(300,150,900,600);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("serif", Font.BOLD, 24));
        heading.setBounds(120,5,400,30);
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(35,80,200,20);
        add(meterNumber);

        JLabel meterText = new JLabel("");
        meterText.setBounds(300,80,200,20);
        add(meterText);

        JLabel name = new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(300,140,200,20);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        monthChoice = new Choice();
        monthChoice.setBounds(300,200,200,20);
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");
        add(monthChoice);

        JLabel units = new JLabel("Units");
        units.setBounds(35,260,200,20);
        add(units);

        JLabel unitText = new JLabel("");
        unitText.setBounds(300,260,200,20);
        add(unitText);

        JLabel total = new JLabel("Total Bill");
        total.setBounds(35,320,200,20);
        add(total);

        JLabel totalBill = new JLabel("");
        totalBill.setBounds(300,320,200,20);
        add(totalBill);

        JLabel status = new JLabel("Payment Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel paymentStatus = new JLabel("");
        paymentStatus.setBounds(300,380,200,20);
        paymentStatus.setForeground(Color.RED);
        add(paymentStatus);

        try{
            resultSet = database.statement.executeQuery("select * from customer where meter_no = '"+meterNo+"'");
            if(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
            }

            resultSet = database.statement.executeQuery("select * from bill where meter_no = '"+meterNo+"' AND month = '"+monthChoice.getSelectedItem()+"'");
            if (resultSet.next()){
                unitText.setText(resultSet.getString("units"));
                totalBill.setText(resultSet.getString("total_bill"));
                paymentStatus.setText((resultSet.getString("payment_status")));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        monthChoice.addItemListener(ie-> {
            try{
                resultSet = database.statement.executeQuery("select * from bill where meter_no = '"+meterNo+"' AND month = '"+monthChoice.getSelectedItem()+"'");
                if (resultSet.next()){
                    unitText.setText(resultSet.getString("units"));
                    totalBill.setText(resultSet.getString("total_bill"));
                    paymentStatus.setText((resultSet.getString("payment_status")));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
                });

        pay = new JButton("PAY");
        pay.setBounds(200,460,100,25);
        pay.setBackground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("BACK");
        back.setBounds(400,460,100,25);
        back.setBackground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(""));
        Image i2 = i1.getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == pay){
            try{
                database.statement.executeUpdate("update bill set payment_status = 'Paid' where meter_no = '"+meterNo+"' AND month = '"+monthChoice.getSelectedItem()+"'");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            setVisible(false);
            new Paytm(meterNo);

        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new PayBill("");
    }
}
