package pack01.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() {
        Connection con = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/pocruit";
        String id = "root";
        String pwd = "1234";

        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url, id, pwd);
            } catch (SQLException e) {
                System.out.println("Connection Failed!");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Connection Failed. Check Driver or URL");
            e.printStackTrace();
        }

        return con;
    }
}