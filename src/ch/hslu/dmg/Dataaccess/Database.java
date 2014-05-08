package ch.hslu.dmg.Dataaccess;

import java.beans.Statement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * @author Angelo on 07.05.2014.
 */
public class Database<T> {

    private String _connectionString;
    private Connection _connection;
    private String _PropertyDelimiter = ".";

    public Database(String connectionString) {
        this._connectionString = connectionString;
    }

    public boolean connect() {
        try {
            String dbms = "sqlserver";
            String databaseServer = "localhost";
            String databaseName = "DMG_Project";
            //String instanceName = "";
            String userName = "dmg";
            String password = "dmgdmg";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            _connection = DriverManager.getConnection("jdbc:" + dbms + "://" + databaseServer + ";databaseName=" + databaseName, userName, password);
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

    public boolean disconnect() {
        try {
            if (this._connection != null) {
                this._connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public T FillObject(T Type, String sqlCommand) {
        try {
            PreparedStatement statement = _connection.prepareStatement(sqlCommand);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RecordToObject(resultSet, Type);
            }

        } catch (SQLException e) {

        }
        return Type;
    }


    private void RecordToObject(ResultSet resultSet, T dataObject) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Method[] methods = dataObject.getClass().getMethods();
            HashMap<String, Method> methodDictionary = new HashMap<String, Method>();
            for (Method method : methods) {
                methodDictionary.put(method.getName(), method);
            }
            for (int ordinal = 1; ordinal < columnCount; ordinal++) {
                String columnName = metaData.getColumnName(ordinal);
                Object col = null;

                col = resultSet.getObject(ordinal);
//
//                String[] columnName = columnName.split(new[] { this._PropertyDelimiter }, StringSplitOptions.None);
//                for (int index = 0; index < columnName.Length - 1; index++) {
//                }


                String getMethodName = "get_" + columnName;
                Method getMethod = methodDictionary.get(getMethodName);

                Object newData = getMethod.invoke(dataObject);
                Type valueType = getMethod.getReturnType();
                if (!valueType.getClass().isPrimitive()) {
                    newData = valueType.getClass().newInstance();
                }

                String setMethodName = "set_" + columnName;
                Method setMethod = methodDictionary.get(setMethodName);


                try {
                    setMethod.invoke(dataObject, col);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
