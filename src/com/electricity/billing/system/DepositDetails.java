package com.electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {
    Choice searchMeter, searchMonth;
    Database database = new Database();
    ResultSet resultSet = null;
    JTable table;
    JButton search, print, close;
    DepositDetails(){
        super("Deposit Details");

        setSize(700,700);
        setLocation(400,100);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel meterNo = new JLabel("Search by Meter Number");
        meterNo.setBounds(20,20,150,20);
        add(meterNo);

        searchMeter = new Choice();
        searchMeter.setBounds(180,20,150,20);
        add(searchMeter);

        try{
            resultSet = database.statement.executeQuery("select * from customer");
            while(resultSet.next()){
                searchMeter.add(resultSet.getString("meter_no"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        JLabel month = new JLabel("Search by Month");
        month.setBounds(400,20,100,20);
        add(month);

        searchMonth = new Choice();
        searchMonth.setBounds(520,20,150,20);
        searchMonth.add("January");
        searchMonth.add("February");
        searchMonth.add("March");
        searchMonth.add("April");
        searchMonth.add("May");
        searchMonth.add("June");
        searchMonth.add("July");
        searchMonth.add("August");
        searchMonth.add("September");
        searchMonth.add("October");
        searchMonth.add("November");
        searchMonth.add("December");
        add(searchMonth);

        table = new JTable();
        try{
            resultSet = database.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0,100,700,600);
        add(scroll);

        search = new JButton("Search");
        search.setBounds(200,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(350,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBounds(500,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            String sql = "select * from bill where meter_no = '"+searchMeter.getSelectedItem()+"' and month = '"+searchMonth.getSelectedItem()+"'";
            try{
                resultSet = database.statement.executeQuery(sql);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else if(ae.getSource() == print){
            try {
                table.print();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new DepositDetails();
    }
}
