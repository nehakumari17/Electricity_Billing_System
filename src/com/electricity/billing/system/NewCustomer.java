package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {

    JLabel meterNumber;
    JTextField customerName1, address1, city1, state1, email, phone;
    JButton next, cancel;
    NewCustomer(){
        setSize(700,500);
        setLocation(400,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("monospaced", Font.BOLD, 24));
        panel.add(heading);

        JLabel customerName = new JLabel("Customer Name");
        customerName.setBounds(100,80,100,20);
        panel.add(customerName);

        customerName1 = new JTextField();
        customerName1.setBounds(240,80,200,20);
        panel.add(customerName1);

        JLabel meter = new JLabel("Meter Number");
        meter.setBounds(100,120,100,20);
        panel.add(meter);

        meterNumber = new JLabel("");
        meterNumber.setBounds(240,120,100,20);
        panel.add(meterNumber);

        Random random = new Random();
        long number = random.nextLong() % 10000000;
        meterNumber.setText(""+ Math.abs(number));

        JLabel address = new JLabel("Address");
        address.setBounds(100,160,100,20);
        panel.add(address);

        address1 = new JTextField();
        address1.setBounds(240,160,200,20);
        panel.add(address1);

        JLabel city = new JLabel("City");
        city.setBounds(100,200,100,20);
        panel.add(city);

        city1 = new JTextField();
        city1.setBounds(240,200,200,20);
        panel.add(city1);

        JLabel state = new JLabel("State");
        state.setBounds(100,240,100,20);
        panel.add(state);

        state1 = new JTextField();
        state1.setBounds(240,240,200,20);
        panel.add(state1);

        JLabel mail = new JLabel("Email");
        mail.setBounds(100,280,100,20);
        panel.add(mail);

        email = new JTextField();
        email.setBounds(240,280,200,20);
        panel.add(email);

        JLabel phoneNumber = new JLabel("Phone Number");
        phoneNumber.setBounds(100,320,100,20);
        panel.add(phoneNumber);

        phone = new JTextField();
        phone.setBounds(240,320,200,20);
        panel.add(phone);

        next = new JButton("Next");
        next.setBounds(200,360,80,20);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(300,360,80,20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/human.png"));
        Image i2 = i1.getImage().getScaledInstance(190, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            String name = customerName1.getText();
            String meterNo = meterNumber.getText();
            String add = address1.getText();
            String aCity = city1.getText();
            String aState = state1.getText();
            String gmail = email.getText();
            String mobileNo = phone.getText();

            String sql1 = "insert into customer values('"+name+"', '"+meterNo+"', '"+add+"', '"+aCity+"', '"+aState+"', '"+gmail+"', '"+mobileNo+"')";
            String sql2 = "insert into signup values('"+meterNo+"', '', '"+name+"', '', '')";

            try{
                Database database = new Database();
                database.statement.executeUpdate(sql1);
                database.statement.executeUpdate(sql2);

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);

                new MeterInfo(meterNo);

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new NewCustomer();
    }
}
