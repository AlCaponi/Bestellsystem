package ch.hslu.dmg.Dataaccess;

import java.beans.Statement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * @author Angelo on 07.05.2014.
 */
public class Database<T> {

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

    public T FillObject(T Type, String sqlCommand){
        try{
            PreparedStatement statement = _connection.prepareStatement(sqlCommand);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Method[] methods = Type.getClass().getMethods();
            HashMap<String, Method> methodDictionary = new HashMap<String, Method>();
            for(Method method : methods){
                methodDictionary.put(method.getName(), method);
            }
            while (resultSet.next()){
                for(int idx = 0; idx < columnCount; idx++){
                    String columnName = metaData.getColumnClassName(idx);
                    Object col = resultSet.getObject(idx);
                    String setMethodName = "set_" + columnName;
                    try {
                        Type.getClass().getMethod(setMethodName).invoke(col);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }

            }

        } catch (SQLException e){

        }
        return Type;
    }



}
