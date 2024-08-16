package codes;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    
    Thread t;
    Color blue=new Color(0,0,128);

    Splash () {

        setTitle("University Management System");
        
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo1.png"));
        Image scaledIcon = logo.getImage().getScaledInstance(150, 80, Image.SCALE_DEFAULT);
        ImageIcon scaledIconImage = new ImageIcon(scaledIcon);
        JLabel logoLabel = new JLabel(scaledIconImage);
        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setBounds(270, 10, 880, 70);
        JLabel heading = new JLabel("University Management System");
        heading.setForeground(blue);
        heading.setFont(new Font("Algerian", Font.BOLD, 40));

        // Add logo and heading labels to the headingPanel
        headingPanel.add(logoLabel, BorderLayout.WEST);
        headingPanel.add(heading, BorderLayout.CENTER);
        add(headingPanel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first1.jpg"));
        //loading image and store it in imageicon i1
        Image i2 = i1.getImage().getScaledInstance(1500, 700, Image.SCALE_DEFAULT);
        //getting image from i1,resizing it and then storing to image i2
        ImageIcon i3 = new ImageIcon(i2);
        //again converting image i1 to imageicon(i3)

        JLabel image = new JLabel(i3);
        //Creates a JLabel component image containing the scaled imagecon i3
        add(image);
        //adding image to frame
        setUndecorated(false);   
    
        
        t = new Thread(this);
        //creatng a thread for animations(we are controlling main thread) 
        t.start(); //starting thread
        
        setVisible(true); //make frame visible on screen
     
        //animation
        int x = 1;
        for (int i = 2; i <= 600; i+=4, x+=1) {
            setLocation(600 - ((i + x)/2), 350 - (i/2));
            setSize(i + 3*x, i + x/2);
            //setUndecorated(false);
            
            try {
                Thread.sleep(10); //gap of 10ms b/tw each iteration
            } catch (Exception e) {}
        }  
        //modification   
        setExtendedState(JFrame.MAXIMIZED_BOTH);}
        
    
    public void run() {
        try {
            Thread.sleep(5000);
            //setVisible(false); //moving to next frame
            
            // Next Frame
            new Login();
            //dispose();
        } catch (Exception e) {
            
        }
    }
    
    public static void main(String[] args) {
        new Splash();
    }
} 