

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class log1 {
    private String dburl = "jdbc:mysql://localhost:3306/sunk";
    private String dbuname = "root";
    private String dbpassword = "123@Anbu";
    private String dbdriver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(dbdriver); // Register MySQL JDBC driver
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public String insert(log2 users) {
        Connection con = getConnection();
        String result = "Data entered Successfully";
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, users.getEmail());
            ps.setString(2, users.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Data is not entered";
        } finally {
            // Close the connection in finally block to ensure it always gets closed
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}