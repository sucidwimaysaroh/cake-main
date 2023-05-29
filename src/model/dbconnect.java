
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class dbconnect {
    public Statement stmt;
    public Connection con = null;
    
    String url = "jdbc:mysql://localhost/investme?serverTimezone=UTC";
    String user = "root";
    String pw = "";
    String name = "com.mysql.cj.jdbc.Driver";
    
    public Connection connect() {
        try {
            Class.forName(name);
            con = DriverManager.getConnection(url, user, pw);
            stmt = con.createStatement();
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: "+ cE.toString());
            JOptionPane.showMessageDialog(null, "koneksi gagal "+cE.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
        return con;
    }
}
