package codes;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;

    public Connection getConnection() {
        return c;
    }
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }


    Conn () {
        /*try {
           Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception ae){
            System.out.println("caught");
        }*/
        try{
            
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1", "root", "1234");
             s = c.createStatement();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void closeConnection() {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }
