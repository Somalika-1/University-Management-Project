package codes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Exam extends JFrame implements ActionListener {

    JTextField search;
    JButton submit, cancel;
    JTable table;
    Color lBlue = new Color(173, 216, 230);
    Conn conn;

    Exam() {
        setSize(1000, 475);
        setLocation(300, 100);
        setLayout(null);
        setTitle("Examination");

        getContentPane().setBackground(lBlue);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/examb.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(60, 5, 100, 100);
        add(image);

        JLabel heading = new JLabel("Check Result:");
        heading.setBounds(200, 15, 400, 50);
        heading.setFont(new Font("Times New Roman Bold", Font.BOLD, 24));
        add(heading);

        search = new JTextField();
        search.setBounds(200, 70, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        submit = new JButton("Result");
        submit.setBounds(500, 70, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(700, 70, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 130, 1000, 310);
        add(jsp);
        conn = new Conn();

        // Connect to the database using Conn class
        try {
           
            ResultSet rs = conn.s.executeQuery("SELECT * FROM student");

            // Get ResultSet metadata for column count
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            // Create DefaultTableModel with column names
            String[] columnNames = new String[columns];
            for (int i = 0; i < columns; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[columns];
                for (int i = 0; i < columns; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }

            // Set the table model
            table.setModel(model);

            // Close resources
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the connection after use
            if (conn != null) {
                conn.closeConnection();
            }
        }
        

        // Set up mouse listener to populate search field
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 5).toString()); // Assuming the value you want to populate is in column index 5
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            setVisible(false);
            new Marks(search.getText());
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Exam();
    }
}


/*import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;


public class Exam extends JFrame implements ActionListener {

    JTextField search;
    JButton submit, cancel;
    JTable table;
    Color lBlue = new Color(173, 216, 230);
    Conn c;
    
    Exam() {
        setSize(1000, 475);
        setLocation(300, 100);
        setLayout(null);
        setTitle("Examination");
        
        getContentPane().setBackground(lBlue);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/examb.png"));
        //loading image and store it in imageicon i1
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        //getting image from i1,resizing it and then storing to image i2
        ImageIcon i3 = new ImageIcon(i2);
        //again converting image i1 to imageicon(i3)
        JLabel image = new JLabel(i3);
        image.setBounds(60, 5, 100, 100);
        add(image);
        
        
        JLabel heading = new JLabel("Check Result:");
        heading.setBounds(200, 15, 400, 50);
        heading.setFont(new Font("Times New Roman Bold", Font.BOLD, 24));
        add(heading);
        
        search = new JTextField();
        search.setBounds(200, 70, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);
        
        submit = new JButton("Result");
        submit.setBounds(500, 70, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Back");
        cancel.setBounds(700, 70, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 130, 1000, 310);
        add(jsp);
        c = new Conn();
        
        // Populate the table with data from the database
        try {
       
            String query = "SELECT * FROM student";
            PreparedStatement pstmt = c.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            // Get ResultSet metadata for column count
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            
            // Create DefaultTableModel with column names
            String[] columnNames = new String[columns];
            for (int i = 0; i < columns; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[columns];
                for (int i = 0; i < columns; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }
            
            // Set the table model
            table.setModel(model);
            
            // Close resources
            rs.close();
            pstmt.close();
            c.getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the connection after use
            if (c != null) {
                c.closeConnection();
            }
        }
        
        // Set up mouse listener to populate search field
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 5).toString());
            }
        });
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            setVisible(false);    
            new Marks(search.getText());
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Exam();
    }
}*/
