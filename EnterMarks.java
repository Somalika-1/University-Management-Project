package codes;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class EnterMarks extends JFrame implements ActionListener,ItemListener {

    Choice crollno;
    JTextField tfsub1, tfsub2,tfsub3,tfsub4,tfsub5,tfmarks1,tfmarks2,tfmarks3,tfmarks4,tfmarks5;
    JButton cancel, submit;
    Conn c;
    String course;
    JLabel Course;
    Color lBlue = new Color(173, 216, 230);
    
    EnterMarks() {
        
        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);
        setTitle("Enter Marks");
        
        getContentPane().setBackground(lBlue);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 40, 400, 300);
        add(image);
        
        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 70, 150, 20);
        add(lblrollnumber);
        
        crollno = new Choice();
        crollno.setBounds(200, 70, 150, 20);
        add(crollno);
        crollno.addItemListener(this); 
        c = new Conn();
    
        try {
           
            ResultSet rs = c.s.executeQuery("SELECT s.stuid,s.Course FROM student s LEFT JOIN marks m ON s.stuid = m.stu_id WHERE m.stu_id IS NULL");
            while(rs.next()) {
                crollno.add(rs.getString("stuid"));
                course=rs.getString("Course");   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the connection after use
            if (c != null) {
                c.closeConnection();
            }
        }

        Course = new JLabel("Course    "+course);
        Course.setBounds(50, 100, 200, 40);
        add(Course);
            
        JLabel lblentersubject = new JLabel("Enter Subject");
        lblentersubject.setBounds(100, 150, 200, 40);
        add(lblentersubject);
        
        JLabel lblentermarks = new JLabel("Enter Marks");
        lblentermarks.setBounds(270, 150, 200, 40);
        add(lblentermarks);

        
        tfsub1 = new JTextField();
        tfsub1.setBounds(50, 200, 150, 20);
        add(tfsub1);
        
        tfsub2 = new JTextField();
        tfsub2.setBounds(50, 230, 150, 20);
        add(tfsub2);
        
        tfsub3 = new JTextField();
        tfsub3.setBounds(50, 260, 150, 20);
        add(tfsub3);
        
        tfsub4 = new JTextField();
        tfsub4.setBounds(50, 290, 150, 20);
        add(tfsub4);
        
        tfsub5 = new JTextField();
        tfsub5.setBounds(50, 320, 150, 20);
        add(tfsub5);
        
        tfmarks1 = new JTextField();
        tfmarks1.setBounds(250, 200, 100, 20);
        add(tfmarks1);
        
        tfmarks2 = new JTextField();
        tfmarks2.setBounds(250, 230, 100, 20);
        add(tfmarks2);
        
        tfmarks3 = new JTextField();
        tfmarks3.setBounds(250, 260, 100, 20);
        add(tfmarks3);
        
        tfmarks4 = new JTextField();
        tfmarks4.setBounds(250, 290, 100, 20);
        add(tfmarks4);
        
        tfmarks5 = new JTextField();
        tfmarks5.setBounds(250, 320, 100, 20);
        add(tfmarks5);
        
        submit = new JButton("Submit");
        submit.setBounds(70, 360, 150, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Back");
        cancel.setBounds(280, 360, 150, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                Conn c = new Conn();
                
                String query1 = "insert into subject values('"+crollno.getSelectedItem()+"', '"+tfsub1.getText()+"', '"+tfsub2.getText()+"', '"+tfsub3.getText()+"', '"+tfsub4.getText()+"', '"+tfsub5.getText()+"')";
                String query2 = "insert into marks values('"+crollno.getSelectedItem()+"', '"+tfmarks1.getText()+"', '"+tfmarks2.getText()+"', '"+tfmarks3.getText()+"', '"+tfmarks4.getText()+"', '"+tfmarks5.getText()+"')";
            
                int ra1=c.s.executeUpdate(query1);
                int ra2=c.s.executeUpdate(query2);
                
                if (ra1 > 0 && ra2 > 0) {
                    JOptionPane.showMessageDialog(null, "Marks Inserted Successfully");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to insert marks. Please enter valid values");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                // Close the connection after use
                if (c != null) {
                    c.closeConnection();
                }
            }
            
        } else {
            setVisible(false);
        }
    }
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == crollno && e.getStateChange() == ItemEvent.SELECTED) {
            // Get the selected roll number
            String selectedRollNumber = crollno.getSelectedItem();
            // Set the course corresponding to the selected roll number in the label
            Course.setText("Course: " + getCourse(selectedRollNumber));
        }
    }

    // Method to retrieve the course corresponding to the roll number
    private String getCourse(String rollNumber) {
        String course = "";
        try {
            Conn c = new Conn();
            PreparedStatement ps = c.prepareStatement("select Course from student where stuid = ?");
            ps.setString(1, rollNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = rs.getString("Course");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            // Close the connection after use
            if (c!= null) {
                c.closeConnection();
            }
        }
        return course;
    }

    public static void main(String[] args) {
        new EnterMarks();
    }
}


/* 
import javax.swing.*;
import java.awt.*;
import java.sql.*;
//import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame {
    
    FeeStructure() {
            setTitle("Fee Details");
            setSize(700, 700);
           // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(400,0); // Center the frame on the screen
            
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fees.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 700, 700);
        add(image);
            
         
           
            
            setVisible(true); // Make the frame visible
        }
        
        public static void main(String[] args) {
            // Create and display the frame on the event dispatch thread
            new FeeStructure();
        }
    }*/
    