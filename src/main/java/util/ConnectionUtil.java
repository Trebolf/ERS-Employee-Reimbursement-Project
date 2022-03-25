package util;

import java.sql.*;

public class ConnectionUtil {

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ERS-project";
        String username = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");

        try{
             conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return conn;
    }
}
