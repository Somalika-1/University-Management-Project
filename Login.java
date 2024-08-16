package codes;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener,DocumentListener{

    JButton login, cancel;
    JTextField tfusername, tfpassword;
    Color lBlue = new Color(173, 216, 230);
    Font font = new Font("SansSerif Bold", Font.BOLD, 15);
    Conn c;
    private Splash s;
    
    
    Login () {

        getContentPane().setBackground(lBlue);
        setLayout(null);
        setTitle("Login");
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        lblusername.setFont(font);
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 25);
        add(tfusername);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 20);
        lblpassword.setFont(font);
        tfusername.getDocument().addDocumentListener(this); // Add DocumentListener
        add(lblpassword);
        
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 25);
        tfpassword.getDocument().addDocumentListener(this); // Add DocumentListener
        add(tfpassword);
       
        login = new JButton("Login");
        login.setBounds(40, 160, 120, 40);
        login.setBackground(Color.black);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        login.setEnabled(false);
        add(login);
        

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 160, 120, 40);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login1.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);
        
        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);

        c = new Conn();
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = tfusername.getText();
            String password = tfpassword.getText();
            
            String query = "select * from login where username='"+username+"' and password='"+password+"'";
            
            try {
               
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    setVisible(false);
                    new Project1();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);
                    new Login();
                }
                rs.close();
                //c.close();
                
            } catch (Exception e) {
                System.out.println("caught");
                e.printStackTrace();
            }finally {
                // Close the connection after use
                c.closeConnection();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
          // new Login();
        }
    }
    //modification
    public void insertUpdate(DocumentEvent e) {
        updateLoginButtonState();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateLoginButtonState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateLoginButtonState();
    }

    // Method to update the enabled state of the login button
    private void updateLoginButtonState() {
        String username = tfusername.getText();
        String password = tfpassword.getText();
        boolean enableLogin = !username.isEmpty() && password.length()>=4;
        login.setEnabled(enableLogin);
    }

  
    public static void main(String[] args) {
        new Login();
    }
}
