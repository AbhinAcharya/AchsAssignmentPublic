package com.achs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 9:36 PM
 */
public class DbConnection {
    private Connection connection = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/achs";
        String uName= "achs";
        String pass= "achs";
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection connection = DriverManager.getConnection(url,uName,pass);
return connection;
    }
    public void close() throws SQLException {
        if(connection!=null & !connection.isClosed()){
            connection.close();
            connection = null;
        }
    }
 boolean copied = true;
        String project = "4bcc4aa6b3db2394edc1a24ded4e939b";
}
