package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConUtil {
    Connection con = null;
    public static Connection vacDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/vacations", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : " + ex.getMessage());
            return null;
        }
    }
}
