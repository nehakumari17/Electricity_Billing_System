package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
    Thread t;
    Splash(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/light.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        setVisible(true);
        int x = 1;
        for (int i = 2; i < 500 ; i+=4, x+=1) {
            setSize((i + x), i);
            setLocation(700 - ((i+x)/2), 400 - (i/2));

        }

        t= new Thread(this);
        t.start();

        setVisible(true);
    }

    public void run(){
        try{
            Thread.sleep(7000);
            setVisible(false);

            new Login();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        new Splash();
    }

}
