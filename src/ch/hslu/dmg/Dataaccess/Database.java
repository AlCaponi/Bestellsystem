package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.CollectionBase;
import sun.org.mozilla.javascript.internal.ast.WhileLoop;

import java.beans.MethodDescriptor;
import java.beans.Statement;
import java.lang.reflect.Field;
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

    public T FillObject(T dataObject, String sqlCommand) {
        try {
            PreparedStatement statement = _connection.prepareStatement(sqlCommand);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.RecordToObject(resultSet, dataObject);
                resultSet.close();
                return dataObject;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataObject;
    }


    public CollectionBase<T> FillList(CollectionBase<T> list, Class<T> elementType, String sqlQuery){
        try{
            PreparedStatement statement = _connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Object dataObject = elementType.newInstance();
                this.RecordToObject(resultSet, (T) dataObject);
                list.add((T) dataObject);
            }
            resultSet.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
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


            for (int ordinal = 1; ordinal < columnCount + 1; ordinal++) {

                String fieldName = "";
                Object data = dataObject;
                Object value = resultSet.getObject(ordinal);

                String columnName = metaData.getColumnName(ordinal);
                String[] ColumnName = columnName.split("\\.");
                for (int index = 0; index < ColumnName.length - 1; index++) {

                    fieldName = ColumnName[index];
                    Method getMethod = methodDictionary.get("get_" + fieldName);
                    Class<?> field = getMethod.getReturnType();
                    //Field field = dataObject.getClass().getField("_" + fieldName);
                    if(field == null){
                        data = null;
                        break;
                    }

                    Object newData = getMethod.invoke(data, null);

                    if(newData == null && value != null)
                    {
                        Type propertyType = getMethod.getReturnType();
                        //todo Überprüfen ob hier wirklich String ausgegeben wird falls diese klasse verwendet wird
                        if (propertyType.getClass().isPrimitive() == false && (!propertyType.getClass().getName().equals("String")) ) {
                            newData = ((Class)propertyType).newInstance();
                            Method setMethod = methodDictionary.get("set_" + fieldName);
                            setMethod.invoke(data, newData);
                        }
                    }
                    data = newData;
                    if(data == null)
                    {
                        break;
                    }
                }
                if(data == null)
                {
                    continue;
                }


                Method getMethod = data.getClass().getMethod("get_" + ColumnName[ColumnName.length - 1]);//methodDictionary.get("get_" + columnName);
                Class<?> setProperty = getMethod.getReturnType();
                //Field setProperty = data.getClass().getDeclaredField("_" + columnName);

                if(setProperty != null){

                    Method [] methodsData = data.getClass().getMethods();
                    Method setMethod = null;
                    for(Method method : methodsData){
                        if(method.getName().equals("set_" + ColumnName[ColumnName.length - 1])){
                            setMethod = method;
                            break;
                        }
                    }
                    //Method setMethod = data.getClass().getMethod("set_" + columnName);
                    if(setMethod != null)
                    {
                        fieldName = setMethod.getName();
                        if(value == null)
                        {
                            setMethod.invoke(data, null);
                        }
                        else if(value != null && value.getClass() == setProperty)
                        {
                            setMethod.invoke(data, value);
                        }
                        else
                        {
                            setMethod.invoke(data, value);
                        }
                    }
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
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
