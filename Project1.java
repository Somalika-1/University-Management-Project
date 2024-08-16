package codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project1 extends JFrame implements ActionListener{

    JButton  add ;
    JButton view, update, exam,fees, exit;
    Font f=new Font("Chalkboard",Font.BOLD,15);
    JLabel addl,viewl,examl,feel,exitl;
    Color c=new Color(173,216,230);
    Color blue=new Color(0,0,128);
    
    Project1() {
        
        setLayout(null);
        setTitle("University Management System");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, screenWidth, screenHeight);
        add(image);
        
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

        image.add(headingPanel);
        image.setBackground(new Color(255, 204, 204, 0)); // Transparent background

    
    
            
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon1.png"));
        Image icon2 = icon.getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT);
        ImageIcon icon3 = new ImageIcon(icon2);
        add = new JButton();
        add.setBounds(350, 120, 120, 100);
        add.setIcon(icon3);
         // Set additional button properties
         add.setToolTipText("New information");
         add.setOpaque(false);
         add.setContentAreaFilled(false);
         add.setBorderPainted(false);
         add.addActionListener(this);
         add.setFont(f);
         image.add(add);
         addl=new JLabel("New information");
         addl.setBounds(360,210,140,30);
         addl.setFont(f);
         image.add(addl);
       

         ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icons/icon8.png"));
        Image icon5 = icon4.getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT);
        ImageIcon icon6 = new ImageIcon(icon5);
        view = new JButton("View Details");
        view.setBounds(610, 110, 120, 100);
        view.setIcon(icon6);
        view.addActionListener(this);
        view.setToolTipText("View information");
        view.setOpaque(false);
        view.setContentAreaFilled(false);
        view.setBorderPainted(false);
        view.setFont(f);
        view.setBackground(c);
        image.add(view);
        viewl=new JLabel("View information");
        viewl.setBounds(600,210,140,30);
        viewl.setFont(f);
         image.add(viewl);
        
        
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icons/icon15.png"));
        Image icon8 = icon7.getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT);
        ImageIcon icon9= new ImageIcon(icon8);
        exam = new JButton();
        exam.setBounds(410, 280, 110, 90);
        exam.setIcon(icon9);
        exam.addActionListener(this);
        exam.setToolTipText("Examination");
        exam.setOpaque(false);
        exam.setContentAreaFilled(false);
        exam.setBorderPainted(false);
        exam.setFont(f);
        exam.setBackground(c);
        image.add(exam);
        examl=new JLabel("Examination");
        examl.setBounds(415,370,140,30);
        examl.setFont(f);
         image.add(examl);
        
    
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icons/icon16.png"));
        Image icon11 = icon10.getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT);
        ImageIcon icon12= new ImageIcon(icon11);
        fees = new JButton();
        fees.setBounds(810, 270, 120, 100);
        fees.setIcon(icon12);
        fees.addActionListener(this);
        fees.setToolTipText("Enter Marks");
        fees.setOpaque(false);
        fees.setContentAreaFilled(false);
        fees.setBorderPainted(false);
        fees.setFont(f);
        fees.setBackground(c);
        image.add(fees);
        feel=new JLabel("Enter Marks");
        feel.setBounds(815,365,140,30);
        feel.setFont(f);
         image.add(feel);
        

        ImageIcon icon13 = new ImageIcon(ClassLoader.getSystemResource("icons/icon12.png"));
        Image icon14 = icon13.getImage().getScaledInstance(100, 110, Image.SCALE_DEFAULT);
        ImageIcon icon15= new ImageIcon(icon14);
        exit = new JButton("");
        exit.setBounds(860, 100, 100, 110);
        exit.setIcon(icon15);
        exit.addActionListener(this);
        exit.setToolTipText("Exit");
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setFont(f);
        exit.setBackground(c);
        image.add(exit);
        exitl=new JLabel("Exit");
        exitl.setBounds(900,200,140,30);
        exitl.setFont(f);
         image.add(exitl);
        
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null); // Center the frame on the screen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maxim
       // setLocation(250, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            //setVisible(false);
            new Addstu();
        } else if (ae.getSource() == view) {
            //setVisible(false);
            new Viewstu();
        } else if(ae.getSource()==exam) {
            //setVisible(false);
            new Exam();
        }
        else if (ae.getSource() == fees) { // Handling fees button action
            new EnterMarks();
            // Perform action fo
    }
        else if (ae.getSource() == exit){
            //dispose();
            //System.exit(0);
            setVisible(false);
        }
}

    public static void main(String[] args) {
        new Project1();
    }
}