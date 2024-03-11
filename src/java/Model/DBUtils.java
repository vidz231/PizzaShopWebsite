/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author TRUNG VI
 */
public class DBUtils {

    private static final String DATABASE_NAME = "MyPizzaShop";

    public static Connection getConnection() throws Exception {
        Connection cnn;
        try {
            Context currentContext = new InitialContext();
            Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
            DataSource ds = (DataSource) tomcatContext.lookup(DATABASE_NAME);
            cnn = ds.getConnection();
            return cnn;
            
        } catch (SQLException ex) {
            throw ex;
        }
    }

}
