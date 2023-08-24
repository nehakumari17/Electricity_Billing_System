package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateReceipt extends JFrame implements ActionListener {
    JButton generateBill;
    Choice monthChoice;
    String meterNumber;
    JTextArea area;
    Database database = new Database();
    ResultSet resultSet;
    GenerateReceipt(String meterNumber){
        this.meterNumber = meterNumber;

        setBounds(500,100,500,700);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate bill");

        JLabel meter = new JLabel(meterNumber);

        monthChoice = new Choice();
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
        monthChoice.setBounds(400,200,200,20);


        area = new JTextArea(50,15);
        area.setText("\n\n  -----------Click on the Generate Bill button------------\n\tto get the bill of the\n\tselected month..");
        area.setFont(new Font("serif", Font.ITALIC, 20));

        JScrollPane scrollPane = new JScrollPane(area);

        generateBill = new JButton("Generate Bill");
        generateBill.addActionListener(this);
        add(generateBill, "South");

        panel.add(heading);
        panel.add(meter);
        panel.add(monthChoice);
        add(panel, "North");

        add(scrollPane, "Center");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            String month = monthChoice.getSelectedItem();
            area.setText("\tELECTRICITY BILL GENERATED\n\tFOR THE MONTH OF "+month+", 2023\n");

            resultSet = database.statement.executeQuery("select * from customer where meter_no = '"+meterNumber+"'");
            if (resultSet.next()){
                area.append("\n    Customer Name :  " + resultSet.getString("name"));
                area.append("\n    Meter Number :    " + resultSet.getString("meter_no"));
                area.append("\n    Address :              " + resultSet.getString("address"));
                area.append("\n    City :                    " + resultSet.getString("city"));
                area.append("\n    State :                  " + resultSet.getString("state"));
                area.append("\n    Email :                " + resultSet.getString("gmail"));
                area.append("\n    Phone Number : " + resultSet.getString("mobile_no"));
                area.append("\n------------------------------------------------------------");
                area.append("\n");
            }

            resultSet = database.statement.executeQuery("select * from meter_info");
            if (resultSet.next()){
                area.append("\n    Meter Location :  " + resultSet.getString("meter_location"));
                area.append("\n    Meter Type :         " + resultSet.getString("meter_type"));
                area.append("\n    Phase Code :        " + resultSet.getString("phase_code"));
                area.append("\n    Bill Type :             " + resultSet.getString("bill_type"));
                area.append("\n    Days :                   " + resultSet.getString("days"));
                area.append("\n-----------------------------------------------------------");
                area.append("\n");
            }

            resultSet = database.statement.executeQuery("select * from charge");
            if (resultSet.next()){
                area.append("\n    Cost per unit :         " + resultSet.getString("cost_per_unit"));
                area.append("\n    Meter rent :           " + resultSet.getString("meter_rent"));
                area.append("\n    Service charge :   " + resultSet.getString("service_charge"));
                area.append("\n    Fixed tax :            " + resultSet.getString("fixed_tax"));
                area.append("\n");
            }

            resultSet = database.statement.executeQuery("select * from bill where meter_no = '"+meterNumber+"' AND month = '"+monthChoice.getSelectedItem()+"'");
            if (resultSet.next()){
                area.append("\n    Current Month :    " + resultSet.getString("month"));
                area.append("\n    Units Consumed :  " + resultSet.getString("units"));
                area.append("\n    Total Charges :   " + resultSet.getString("total_bill"));
                area.append("\n-----------------------------------------------------------");
                area.append("\n    Total Payable :   " + resultSet.getString("total_bill"));
                area.append("\n");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        new GenerateReceipt("");
    }
}
