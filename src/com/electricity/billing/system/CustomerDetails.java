package com.electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {
    JButton print, cancel;
    JTable table;
    Database database = new Database();
    ResultSet resultSet = null;
    CustomerDetails(){
        super("Customer Details");

        setSize(800,400);
        setLocation(400,250);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        table = new JTable();
        try{
            resultSet = database.statement.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,750,300);
        add(scrollPane);

        print = new JButton("Print");
        print.setBounds(250,320,80,20);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(400,320,80,20);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == print){
            try{

                table.print();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new CustomerDetails();
    }
}
