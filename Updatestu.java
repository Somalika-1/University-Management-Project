package codes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Updatestu extends JFrame implements ActionListener{

    private Viewstu vi;
    JTextField tfeducation, tffname, tfaddress, tfphone,tfemail;
    JLabel lblstuid;
    JComboBox cbeducation;
    JButton add, back;
    String stuid;
    Color lBlue = new Color(173, 216, 230);
    Font font = new Font("SansSerif Bold", Font.BOLD, 20);
    Conn c;
    Conn con;
    
    
    Updatestu(String stuid) {
        this.stuid = stuid;
        getContentPane().setBackground(lBlue);
        setLayout(null);
        setTitle("Update Information");
        
        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(340, 30, 500, 50);
        heading.setFont(new Font("Times New Roman Bold",Font.BOLD,30));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/updateb.png"));
        Image i2 = i1.getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(200, 5, 130, 130);
        add(image);
        
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(font);
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(150, 150, 150, 30);
        lblname.setFont(font);
        add(lblname);
        
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
        tfaddress.setBounds(150, 250, 150, 30);
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
        labelemail.setBounds(50, 350, 150, 30);
        labelemail.setFont(font);
        add(labelemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(150, 350, 150, 30);
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
        labelstuid.setBounds(50, 450, 150, 30);
        labelstuid.setFont(font);
        add(labelstuid);
        
        lblstuid = new JLabel();
        lblstuid.setBounds(150, 450, 150, 30);
        lblstuid.setFont(font);
        add(lblstuid);

        JLabel labelbg = new JLabel("Blood group");
        labelbg.setBounds(450, 450, 150, 30);
        labelbg.setFont(font);
        add(labelbg);
        
        JLabel lblbg= new JLabel();
        lblbg.setBounds(600, 450, 150, 30);
        lblbg.setFont(font);
        add(lblbg);
        c=new Conn();
        
        try {
           
            String query = "select * from student where stuid = '"+stuid+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblname.setText(rs.getString("name"));
                lblbg.setText(rs.getString("Bloodgroup"));
                tffname.setText(rs.getString("fname"));
            
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                cbeducation.setSelectedItem(rs.getString("Course"));
                
                lblstuid.setText(rs.getString("stuid"));
                
                
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
        
        add = new JButton("Update Details");
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
        setLocation(300, 50);
        setVisible(true);

        con = new Conn();
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String fname = tffname.getText();
           // String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String)cbeducation.getSelectedItem();
           // String designation = tfdesignation.getText();
            
            try {
                String query = "update student set fname = '"+fname+"', address = '"+address+"', phone = '"+phone+"', email =  '"+email+"',Course='"+education+"' where stuid = '"+stuid+"'";
                con.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
               // new Project1();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                // Close the connection after use
                if (con != null) {
                    con.closeConnection();
                }
            }
            
        } else {
            setVisible(false);
             // vi.setVisible(true);
    }}

    public static void main(String[] args) {
        new Updatestu("");
    }
}