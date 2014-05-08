package ch.hslu.dmg.Dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Angelo on 07.05.2014.
 */
public class Database {

    private String _connectionString;
    private Connection _connection;

    public Database(String connectionString){
        this._connectionString = connectionString;
    }

    public boolean connect(){
        try {
            String dbms = "sqlserver";
            String databaseServer = "localhost";
            String databaseName = "DMG_Project";
            //String instanceName = "";
            String userName = "dmg";
            String password = "dmgdmg";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection( "jdbc:" + dbms + "://" + databaseServer + ";databaseName=" + databaseName, userName, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean disconnect(){
        try {
            if(this._connection != null){
                this._connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }




}
