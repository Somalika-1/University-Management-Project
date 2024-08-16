package codes;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Marks extends JFrame implements ActionListener {
    
    String rollno;
    JButton cancel;
    Color lBlue = new Color(206,229,237);
    Conn c;
    
    Marks(String rollno) {
        this.rollno = rollno;
        
        setTitle("Result");
        setSize(500, 600);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        
        setLayout(null); // Set layout manager to null
        
        getContentPane().setBackground(lBlue);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/icon3.png"));
        Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(40, 70, 90, 90);
        add(image);
        
        JLabel heading = new JLabel("Result of Examination 2022");
        heading.setBounds(80, 20, 400, 30);
        heading.setFont(new Font("Times New Roman Bold", Font.BOLD, 30));
        add(heading);
        
        JLabel lblrollno = new JLabel("Roll Number " + rollno);
        lblrollno.setBounds(150, 115, 400, 20);
        lblrollno.setFont(new Font("San Serif Bold", Font.BOLD, 18));
        add(lblrollno);
        
        JLabel sub1 = new JLabel();
        sub1.setBounds(150, 170, 400, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub1);
        
        JLabel sub2 = new JLabel();
        sub2.setBounds(150, 200, 400, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub2);
        
        JLabel sub3 = new JLabel();
        sub3.setBounds(150, 230, 400, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub3);
        
        JLabel sub4 = new JLabel();
        sub4.setBounds(150, 260, 400, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub4);
        
        JLabel sub5 = new JLabel();
        sub5.setBounds(150, 290, 400, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub5);

        JLabel cgpaLabel = new JLabel();
        cgpaLabel.setBounds(150, 350, 400, 20);
        cgpaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(cgpaLabel);

        JLabel rsLabel = new JLabel();
        rsLabel.setBounds(150, 380, 400, 20);
        rsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(rsLabel);
        
        c = new Conn();
      
        
        try {
            
            
            ResultSet rs1 = c.s.executeQuery("select * from subject where stuid = '"+rollno+"'");
            while(rs1.next()) {
                sub1.setText(rs1.getString("sub1"));
                sub2.setText(rs1.getString("sub2"));
                sub3.setText(rs1.getString("sub3"));
                sub4.setText(rs1.getString("sub4"));
                sub5.setText(rs1.getString("sub5"));
            }
            
            ResultSet rs2 = c.s.executeQuery("select * from marks where stu_id = '"+rollno+"'");
            while(rs2.next()) {
                double totalMarks = rs2.getInt("sub1") + rs2.getInt("sub2") + rs2.getInt("sub3") + rs2.getInt("sub4") + rs2.getInt("sub5");
                double cgpa = totalMarks / 50.0; 

                sub1.setText(sub1.getText() + "------------" + rs2.getString("sub1"));
                sub2.setText(sub2.getText() + "------------" + rs2.getString("sub2"));
                sub3.setText(sub3.getText() + "------------" + rs2.getString("sub3"));
                sub4.setText(sub4.getText() + "------------" + rs2.getString("sub4"));
                sub5.setText(sub5.getText() + "------------" + rs2.getString("sub5"));
               // lblsemester.setText("Semester " + rs2.getString("semester"));
               cgpaLabel.setText("CGPA: " + String.format("%.2f", cgpa));
                if(cgpa>5.0){
                    rsLabel.setText("Congratulations! You Passed.");          
                }
                else{
                    rsLabel.setText("You are Failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the connection after use
            if (c!= null) {
                c.closeConnection();
            }
        }
        
        
        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }
    
    public static void main(String[] args) {
        new Marks("");
    }
}

/*import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Marks extends JFrame implements ActionListener {
    
    String rollno;
    JButton cancel;
    Color lBlue = new Color(206,229,237);
    Conn c;
    
    public Marks(String rollno) {
        this.rollno = rollno;
        
        setTitle("Marks");
        setSize(500, 600);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        
        setLayout(null); // Set layout manager to null
        
        getContentPane().setBackground(lBlue);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/icon3.png"));
        Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 5, 90, 90);
        add(image);
        
        JLabel heading = new JLabel("Result of Examination 2022");
        heading.setBounds(120, 20, 400, 30);
        heading.setFont(new Font("Times New Roman Bold", Font.BOLD, 30));
        add(heading);
        
        JLabel lblrollno = new JLabel("Roll Number " + rollno);
        lblrollno.setBounds(50, 120, 400, 20);
        lblrollno.setFont(new Font("San Serif Bold", Font.BOLD, 16));
        add(lblrollno);
        
        JLabel sub1 = new JLabel();
        sub1.setBounds(150, 170, 400, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub1);
        
        JLabel sub2 = new JLabel();
        sub2.setBounds(150, 200, 400, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub2);
        
        JLabel sub3 = new JLabel();
        sub3.setBounds(150, 230, 400, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub3);
        
        JLabel sub4 = new JLabel();
        sub4.setBounds(150, 260, 400, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub4);
        
        JLabel sub5 = new JLabel();
        sub5.setBounds(150, 290, 400, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(sub5);

        JLabel cgpaLabel = new JLabel();
        cgpaLabel.setBounds(150, 350, 400, 20);
        cgpaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(cgpaLabel);

        JLabel rsLabel = new JLabel();
        rsLabel.setBounds(150, 380, 400, 20);
        rsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(rsLabel);
        
        c = new Conn();
      
        try {
            ResultSet rs = c.s.executeQuery("select * from marks where stu_id = '"+rollno+"'");
            if (rs.next()) {
                double totalMarks = rs.getInt("sub1") + rs.getInt("sub2") + rs.getInt("sub3") + rs.getInt("sub4") + rs.getInt("sub5");
                double cgpa = totalMarks / 50.0; 

                //lblsemester.setText("Semester: " + rs.getString("semester"));
                sub1.setText("Subject 1: " + rs.getInt("sub1"));
                sub2.setText("Subject 2: " + rs.getInt("sub2"));
                sub3.setText("Subject 3: " + rs.getInt("sub3"));
                sub4.setText("Subject 4: " + rs.getInt("sub4"));
                sub5.setText("Subject 5: " + rs.getInt("sub5"));

                cgpaLabel.setText("CGPA: " + String.format("%.2f", cgpa));
                if(cgpa>5.0){
                    rsLabel.setText("Congratulations! You Passed.");          
                }
                else{
                    rsLabel.setText("You are Failed");
                }
            } else {
                // Handle the case when no record is found for the given roll number
                JOptionPane.showMessageDialog(this, "No records found for roll number " + rollno, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection after use
            if (c != null) {
                c.closeConnection();
            }
        }
        
        cancel = new JButton("Back");
        cancel.setBounds(180, 480, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Exam();
    }
    
    public static void main(String[] args) {
        new Marks("");
    }
}*/
