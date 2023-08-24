package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    Choice meterLocation, meterType, phaseCode, billType;
    JButton submit;
    String meterNo;
    MeterInfo(String meterNo){
        this.meterNo = meterNo;

        setSize(720,500);
        setLocation(400,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(150,10,300,20);
        heading.setFont(new Font("monospaced", Font.BOLD,24));
        panel.add(heading);

        JLabel meter = new JLabel("Meter Number");
        meter.setBounds(100,80,100,20);
        panel.add(meter);

        JLabel meterNumber = new JLabel(meterNo);
        meterNumber.setBounds(230,80,100,20);
        panel.add(meterNumber);

        JLabel location = new JLabel("Meter Location");
        location.setBounds(100,120,100,20);
        panel.add(location);

        meterLocation = new Choice();
        meterLocation.setBounds(230,120, 200,20);
        meterLocation.add("SELECT");
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        panel.add(meterLocation);

        JLabel type = new JLabel("Meter Type");
        type.setBounds(100,160,100,20);
        panel.add(type);

        meterType = new Choice();
        meterType.setBounds(230,160,200,20);
        meterType.add("SELECT");
        meterType.add("Electric meter");
        meterType.add("Solar meter");
        meterType.add("Smart meter");
        panel.add(meterType);

        JLabel code = new JLabel("Phase Code");
        code.setBounds(100,200,100,20);
        panel.add(code);

        phaseCode = new Choice();
        phaseCode.setBounds(230,200,200,20);
        phaseCode.add("SELECT");
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        panel.add(phaseCode);

        JLabel bill = new JLabel("Bill Type");
        bill.setBounds(100,240,100,20);
        panel.add(bill);

        billType = new Choice();
        billType.setBounds(230,240,200,20);
        billType.add("SELECT");
        billType.add("Normal");
        billType.add("Commercial");
        panel.add(billType);

        JLabel day = new JLabel("Days");
        day.setBounds(100,280,100,20);
        panel.add(day);

        JLabel days = new JLabel("30 Days");
        days.setBounds(230,280,100,20);
        panel.add(days);

        JLabel note = new JLabel("NOTE");
        note.setBounds(100,320,100,20);
        note.setForeground(Color.RED);
        panel.add(note);

        JLabel notes = new JLabel("By Default Bill is calculated for 30 days only.");
        notes.setBounds(230,320,300,20);
        notes.setForeground(Color.RED);
        panel.add(notes);

        submit = new JButton("SUBMIT");
        submit.setBounds(230,360,100,20);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/human.png"));
        Image i2 = i1.getImage().getScaledInstance(200,300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String meter = meterNo;
            String mLocation = meterLocation.getSelectedItem();
            String mType = meterType.getSelectedItem();
            String pCode = phaseCode.getSelectedItem();
            String bType = billType.getSelectedItem();
            String days = "30";

            String sql = "insert into meter_info values('"+meter+"', '"+mLocation+"', '"+mType+"', '"+pCode+"', '"+bType+"', '"+days+"')";

            try{
                Database database = new Database();
                database.statement.executeUpdate(sql);

                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully...");
                setVisible(false);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        new MeterInfo("");
    }
}
