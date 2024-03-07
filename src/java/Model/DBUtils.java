/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TRUNG VI
 */
public class DBUtils {

    private static final String userName = "sa";
    private static final String password = "12345";
    private static final String databaseName = "MyPizzaShop";

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString
                    = "jdbc:sqlserver://localhost:1433;database=" + databaseName + ";instanceName=SQL2019";
            //SQL Server Authentication
            Connection cnn = DriverManager.getConnection(connectionString, userName, password);
            return cnn;

        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

}
