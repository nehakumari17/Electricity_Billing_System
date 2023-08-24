package com.electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paytm extends JFrame implements ActionListener {
    String meterNo;
    JButton back;
    Paytm(String meterNo){
        this.meterNo = meterNo;

        setBounds(400,150,800,600);

        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setEditable(false);

        try{
            jEditorPane.setPage("https://paytm.com/online-payments");
        }catch (Exception e){
            jEditorPane.setContentType("text/html");
            jEditorPane.setText("<html>Could not Load<html>");
        }

        JScrollPane scrollPane = new JScrollPane(jEditorPane);
        add(scrollPane);

        back = new JButton("BACK");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        jEditorPane.add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new PayBill(meterNo);
    }

    public static void main(String[] args){
        new Paytm("");
    }
}
