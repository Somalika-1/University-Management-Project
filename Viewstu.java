package codes;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Viewstu extends JFrame implements ActionListener{

    JTable table;
    Choice cstuid;
    JButton search,update,back;
    Color lBlue = new Color(173, 216, 230);
    Font font = new Font("SansSerif Bold", Font.BOLD, 15);
    Conn c,conn;

    Viewstu() {

        getContentPane().setBackground(lBlue);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search by Student Id");
        searchlbl.setBounds(20, 20, 180, 30);
        searchlbl.setFont(new Font("Times New Roman Bold",Font.BOLD,20));
        add(searchlbl);

        cstuid = new Choice();
        cstuid.setBounds(210, 20, 150, 30);
        cstuid.setFont(font);
        add(cstuid);
        c = new Conn();

        try {
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()) {
               cstuid.add(rs.getString("stuid"));
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

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 300);
        jsp.setFont(font);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(430, 20, 100, 30);
        search.addActionListener(this);
        search.setFont(font);
        add(search);

        update = new JButton("Update");
        update.setBounds(630, 20, 100, 30);
        update.addActionListener(this);
        update.setFont(font);
        add(update);
        

        setSize(900, 450);
        setLocation(300, 100);
        setTitle("View Information");
        setVisible(true);
        conn=new Conn();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String studentId = cstuid.getSelectedItem();
            if (studentId != null && !studentId.isEmpty()) {
                try {
                    
                    String query = "select * from student where stuid = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, studentId);
                    ResultSet rs = pstmt.executeQuery();

                    // Populate JTable
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columns = metaData.getColumnCount();
                    DefaultTableModel model = new DefaultTableModel();

                    // Add columns to the model
                    for (int i = 1; i <= columns; i++) {
                        model.addColumn(metaData.getColumnName(i));
                    }

                    // Add rows to the model
                    while (rs.next()) {
                        Object[] rowData = new Object[columns];
                        for (int i = 1; i <= columns; i++) {
                            rowData[i - 1] = rs.getObject(i);
                        }
                        model.addRow(rowData);
                    }

                    table.setModel(model);

                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    // Close the connection after use
                    if (c != null) {
                        c.closeConnection();
                    }
                }
            }
        }
        else if (ae.getSource() == update) {
           // setVisible(false);
            new Updatestu(cstuid.getSelectedItem());
    }
}

    public static void main(String[] args) {
        new Viewstu();
    }
}
