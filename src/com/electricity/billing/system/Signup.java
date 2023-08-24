package com.electricity.billing.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
        JButton create, back;
        Choice accountType;
        JTextField meterText, usernameText, nameText, passwordText;
        Database database = new Database();
        ResultSet resultSet = null;
        Signup(){
            setBounds(450, 150, 700, 400);
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);

            JPanel panel = new JPanel();
            panel.setBounds(30, 30, 630, 300);
            panel.setBorder(new TitledBorder(new LineBorder(Color.CYAN), "Create Account"));
            panel.setLayout(null);
            add(panel);

            JLabel heading = new JLabel("Create Account As");
            heading.setBounds(50,50,140,20);
            heading.setFont(new Font("Thoma", Font.BOLD, 14));
            panel.add(heading);

            accountType = new Choice();
            accountType.add("Admin");
            accountType.add("Customer");
            accountType.setBounds(200,50,150,20);
            panel.add(accountType);

            JLabel meter = new JLabel("Meter Number");
            meter.setBounds(50,90,140,20);
            meter.setFont(new Font("Thoma", Font.BOLD, 14));
            meter.setVisible(false);
            panel.add(meter);

            meterText = new JTextField();
            meterText.setBounds(200,90,150,20);
            meterText.setVisible(false);
            panel.add(meterText);

            JLabel username = new JLabel("Username");
            username.setBounds(50,130,140,20);
            username.setFont(new Font("Thoma", Font.BOLD, 14));
            panel.add(username);

            usernameText = new JTextField();
            usernameText.setBounds(200,130,150,20);
            panel.add(usernameText);

            JLabel name = new JLabel("Name");
            name.setBounds(50, 170,140,20);
            name.setFont(new Font("Thoma", Font.BOLD, 14));
            panel.add(name);

            nameText = new JTextField("");
            nameText.setBounds(200,170,150,20);
            panel.add(nameText);

            JLabel password = new JLabel("Password");
            password.setBounds(50,210,140,20);
            password.setFont(new Font("Thoma", Font.BOLD, 14));
            panel.add(password);

            passwordText = new JTextField();
            passwordText.setBounds(200,210,150,20);
            panel.add(passwordText);

            meterText.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {

                }

                @Override
                public void focusLost(FocusEvent fe) {
                    try{
                        resultSet = database.statement.executeQuery("select * from signup where meter_no = '"+meterText.getText()+"'");
                        if (resultSet.next()){
                            nameText.setText(resultSet.getString("name"));
                        }
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            });

            accountType.addItemListener(ie -> {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")){
                    meter.setVisible(true);
                    meterText.setVisible(true);
                    nameText.setEditable(false);
                }else {
                    meter.setVisible(false);
                    meterText.setVisible(false);
                    nameText.setEditable(true);
                }
            });

            create = new JButton("Create");
            create.setBounds(150,250,100,20);
            create.setBackground(Color.BLACK);
            create.setForeground(Color.ORANGE);
            create.addActionListener(this);
            panel.add(create);

            back = new JButton("Back");
            back.setBounds(280,250,100,20);
            back.setBackground(Color.BLACK);
            back.setForeground(Color.ORANGE);
            back.addActionListener(this);
            panel.add(back);

            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
            Image i2 = i1.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(400,30,200,200);
            panel.add(image);

            setVisible(true);
        }

        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == create){
                String aType = accountType.getSelectedItem();
                String userName = usernameText.getText();
                String sName = nameText.getText();
                String passWord = passwordText.getText();
                String meter2 = meterText.getText();

                try{
                    String sql;
                    if(aType.equals("Admin")){
                        sql = "insert into signup value('"+meter2+"', '"+userName+"', '"+sName+"', '"+passWord+"', '"+aType+"')";
                    }else {
                        sql = "update signup set username = '"+userName+"', password = '"+passWord+"', account_type = '"+aType+"' where meter_no = '"+meter2+"'";
                    }

                    database.statement.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Account Created Successfully");

                    setVisible(false);
                    new Login();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (ae.getSource() == back) {
                setVisible(false);
                new Login();
            }
        }

        public static void main(String[] args){
            new com.electricity.billing.system.Signup();
        }
}