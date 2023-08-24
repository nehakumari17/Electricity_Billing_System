package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {
    JTextField addressText, cityText, stateText, emailText, phoneText;
    JButton update, cancel;
    Database database = new Database();
    ResultSet resultSet;
    String meterNumber;
    UpdateInformation(String meterNumber){
        this.meterNumber = meterNumber;
        setBounds(300,150,1050,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(230,70,200,20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,110,100,20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(230,110,200,20);
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,20);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(230,150,200,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(230,190,200,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(230,230,200,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(30,270,100,20);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(230,270,200,20);
        add(emailText);

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(30,310,100,20);
        add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(230,310,200,20);
        add(phoneText);

        try{
            resultSet = database.statement.executeQuery("select * from customer where meter_no = '"+meterNumber+"'");
            if(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update info.png"));
        Image i2 = i1.getImage().getScaledInstance(500, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,10,500,400);
        add(image);

        update = new JButton("Update");
        update.setBounds(200,360,100,25);
        update.setForeground(Color.WHITE);
        update.setBackground(Color.BLACK);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(350,360,100,25);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancel){
            setVisible(false);
        }else {
            String updateAddress = addressText.getText();
            String updateCity = cityText.getText();
            String updateState = stateText.getText();
            String updateEmail = emailText.getText();
            String updatePhone = phoneText.getText();

            try{
                database.statement.executeUpdate("update customer SET address = '"+updateAddress+"', city = '"+updateCity+"', state = '"+updateState+"' , gmail = '"+updateEmail+"', mobile_no = '"+updatePhone+"' where meter_no = '"+meterNumber+"' ");

                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        new UpdateInformation("");
    }
}
