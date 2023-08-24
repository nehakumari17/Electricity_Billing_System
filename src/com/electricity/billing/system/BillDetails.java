package com.electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BillDetails extends JFrame {
    Database database = new Database();
    ResultSet resultSet;
    String meterNumber;
    BillDetails(String meterNumber){
        this.meterNumber = meterNumber;

        setBounds(400,150,700,650);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try{
            resultSet = database.statement.executeQuery("select * from bill where meter_no = '"+meterNumber+"'");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,700,650);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args){
        new BillDetails("");
    }

}
