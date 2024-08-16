package codes;

import java.awt.*;
import javax.swing.*;
//import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;

public class Addstu extends JFrame implements ActionListener{

    private Project1 pro1;

    //Random ran = new Random();
    //int number = ran.nextInt(999999);
    Color lBlue = new Color(173, 216, 230);
    Font font = new Font("SansSerif Bold", Font.BOLD, 20);
    
    JTextField tfname, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfsalary, tfdesignation,tfbg,tfstuid;
    //JDateChooser dcdob;
    JComboBox cbeducation;
    JLabel lblempId;
    JButton add, back;
    Conn c;
    
    Addstu() {
        setFont(font);
        getContentPane().setBackground(lBlue);
        setLayout(null);
        setTitle("Add Student Details");
        
        JLabel heading = new JLabel("Add Student Details");
        heading.setBounds(350, 30, 500, 50);
        heading.setFont(new Font("Times New Roman Bold",font.BOLD,30));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/new1.png"));
        Image i2 = i1.getImage().getScaledInstance(130, 110, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(210, 0, 130, 130);
        add(image);
        
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(font);
        add(labelname);
        
        tfname = new JTextField();
        tfname.setBounds(170, 150, 150, 30);
        tfname.setFont(font);
        add(tfname);
        
        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(450, 150, 150, 30);
        labelfname.setFont(font);
        add(labelfname);
        
        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        tffname.setFont(font);
        add(tffname);
        
        
        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(font);
        add(labeladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(170, 250, 150, 30);
        tfaddress.setFont(font);
        add(tfaddress);
        
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(450, 250, 150, 30);
        labelphone.setFont(font);
        add(labelphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        tfphone.setFont(font);
        add(tfphone);
        
        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 450, 150, 30);
        labelemail.setFont(font);
        add(labelemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(170, 450, 170, 30);
        tfemail.setFont(font);
        add(tfemail);
        
        JLabel labeleducation = new JLabel("Course");
        labeleducation.setBounds(450, 350, 150, 30);
        labeleducation.setFont(font);
        add(labeleducation);
        
        String courses[] = {"BBA", "BA", "BSC", "BTech", "MBA", "MCA", "MA", "MTech", "MSC","MCom","MSW", "PHD"};
        cbeducation = new JComboBox(courses);
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(600, 350, 150, 30);
        cbeducation.setFont(new Font("SansSerif Bold", Font.BOLD, 15));
        add(cbeducation);
        
       
        JLabel labelstuid = new JLabel("Student id");
        labelstuid.setBounds(450, 450, 150, 30);
        labelstuid.setFont(font);
        add(labelstuid);
        
        tfstuid = new JTextField();
        tfstuid.setBounds(600, 450, 150, 30);
        tfstuid.setFont(font);
        add(tfstuid);
        

        JLabel labelbg = new JLabel("Blood Group");
        labelbg.setBounds(45, 350, 150, 30);
        labelbg.setFont(font);
        add(labelbg);
        
        tfbg = new JTextField();
        tfbg.setBounds(170, 350, 150, 30);
        tfbg.setFont(font);
        add(tfbg);
        
        add = new JButton("Add Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 10);
        setVisible(true);

        c=new Conn();
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String stuId = tfstuid.getText();
            String bg = tfbg.getText();
            String Course =  cbeducation.getSelectedItem().toString();
            
            try {
                String query = "INSERT INTO student VALUES ('"+name+"', '"+fname+"', '"+address+"', '"+phone+"', '"+email+"', '"+stuId+"','"+Course+"','"+bg+"')";
                int rowsAffected = c.s.executeUpdate(query);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Details added successfully");
                    setVisible(false);
                    //new Addstu();
                  //  Project1.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add details");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
            finally {
                // Close the connection after use
                c.closeConnection();
            }
        } else {
            setVisible(false);
           // new Project1();
           pro1.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        
        new Addstu();
    }
}
