/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ieru.utils.rdb;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jaakko
 */
public class MysqlConnector extends Connector{
    private Connection connection;
    public void openConnection(String host, 
                                                    String dbName,
                                                    String dbUser,
                                                    String dbPass)  {
        try {
            connection = null;
            String dburl = "jdbc:mysql://"+host+"/";
            ;
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(dburl + dbName, dbUser,dbPass);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }    

    public Connection getConnection(){
        return connection;
    }
    
    @Override
    public void openConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
