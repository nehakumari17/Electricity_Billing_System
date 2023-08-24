package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton login, signUp, cancel;
    JTextField usernameText, passwordText;
    Choice loginAs;
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel username = new JLabel("Username");
        username.setBounds(300, 20, 100, 20);
        add(username);

        usernameText = new JTextField();
        usernameText.setBounds(400, 20, 150, 20);
        add(usernameText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 60, 100, 20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400, 60, 150, 20);
        add(passwordText);

        JLabel logins = new JLabel("Login as");
        logins.setBounds(300, 100, 100, 20);
        add(logins);

        loginAs = new Choice();
        loginAs.add("Admin");
        loginAs.add("Customer");
        loginAs.setBounds(400, 100, 150, 20);
        add(loginAs);

        login = new JButton("Login");
        login.setBounds(320, 160, 100, 20);
        login.addActionListener(this);
        add(login);

        signUp = new JButton("Sign Up");
        signUp.setBounds(450, 160, 100, 20);
        signUp.addActionListener(this);
        add(signUp);

        cancel = new JButton("Cancel");
        cancel.setBounds(380, 200, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/person.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 250, 250);
        add(image);

        setSize(600, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String userName = usernameText.getText();
            String passWord = passwordText.getText();
            String user = loginAs.getSelectedItem();

            try{
                Database database = new Database();
                String sql = "select * from signup where username = '"+userName+"' and password = '"+passWord+"' and account_type = '"+user+"'";
                ResultSet resultSet = database.statement.executeQuery(sql);

                if(resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    usernameText.setText("");
                    passwordText.setText("");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        } else if (ae.getSource() == signUp) {
            setVisible(false);

            new Signup();
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }

    }

    public static void main(String[] args){
        new Login();
    }
}
