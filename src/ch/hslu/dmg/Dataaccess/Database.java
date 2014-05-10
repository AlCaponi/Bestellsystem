package ch.hslu.dmg.Dataaccess;

import ch.hslu.dmg.library.CollectionBase;
import com.sun.deploy.util.StringUtils;

import javax.swing.plaf.nimbus.State;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Angelo on 07.05.2014.
 */
public class Database<T> {

    private String _connectionString;
    protected Connection _connection;
    private String _PropertyDelimiter = "\\.";

    public Database(String connectionString) {
        this._connectionString = connectionString;
    }

    public boolean connect() {
        try {
            String dbms = "sqlserver";
            String databaseServer = "localhost";
            String databaseName = "DMG_Project_Test";
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

    public int ExecuteNonQuery(String sqlQuery) {
        int generatedId = 0;
        try {
            PreparedStatement statement = this._connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public T FillObject(T dataObject, String sqlCommand) {
        try {
            PreparedStatement statement = _connection.prepareStatement(sqlCommand);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.recordToObject(resultSet, dataObject);
                resultSet.close();
                return dataObject;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataObject;
    }


    public CollectionBase<T> FillList(CollectionBase<T> list, Class<T> elementType, String sqlQuery) {
        try {
            PreparedStatement statement = _connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Object dataObject = elementType.newInstance();
                this.recordToObject(resultSet, (T) dataObject);
                list.add((T) dataObject);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    private void recordToObject(final ResultSet resultSet, T dataObject) {
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
                String[] columnName = metaData.getColumnName(ordinal).split(this._PropertyDelimiter);
                for (int index = 0; index < columnName.length - 1; index++) {

                    fieldName = columnName[index];
                    Method getMethod = methodDictionary.get("get_" + fieldName);
                    Class<?> field = getMethod.getReturnType();
                    //Field field = dataObject.getClass().getField("_" + fieldName);
                    if (field == null) {
                        data = null;
                        break;
                    }

                    Object newData = getMethod.invoke(data, null);

                    if (newData == null && value != null) {
                        Type propertyType = getMethod.getReturnType();
                        //todo Überprüfen ob hier wirklich String ausgegeben wird falls diese klasse verwendet wird
                        if (!propertyType.getClass().isPrimitive() && (!propertyType.getClass().getName().equals("String"))) {
                            newData = ((Class) propertyType).newInstance();
                            Method setMethod = methodDictionary.get("set_" + fieldName);
                            setMethod.invoke(data, newData);
                        }
                    }
                    data = newData;
                    if (data == null) {
                        break;
                    }
                }
                if (data == null) {
                    continue;
                }


                Method getMethod = null; //= data.getClass().getMethod("get_" + columnName[columnName.length - 1]);//methodDictionary.get("get_" + columnName);
                for (Method method : data.getClass().getMethods()) {
                    if (method.getName().equals("get_" + columnName[columnName.length - 1])) {
                        getMethod = method;
                        break;
                    }
                }
                Class<?> setProperty = getMethod.getReturnType();
                //Field setProperty = data.getClass().getDeclaredField("_" + columnName);

                if (setProperty != null) {

                    Method[] methodsData = data.getClass().getMethods();
                    Method setMethod = null;
                    for (Method method : methodsData) {
                        if (method.getName().equals("set_" + columnName[columnName.length - 1])) {
                            setMethod = method;
                            break;
                        }
                    }
                    //Method setMethod = data.getClass().getMethod("set_" + columnName);
                    if (setMethod != null) {
                        fieldName = setMethod.getName();
                        if (value == null) {
                            setMethod.invoke(data, null);
                        } else if (value != null && value.getClass() == setProperty) {
                            setMethod.invoke(data, value);
                        } else {
                            setMethod.invoke(data, this.SetFormat(value, value.getClass(), setProperty));
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
        }
    }

    private Object SetFormat(Object value, Class sourceType, Class targetType) {
        if (sourceType == java.lang.Double.class && targetType == float.class) {
            return ((Double) value).floatValue();
        }
        return (T) value;
    }

    /**
     * The Save method to save generic Objects.
     *
     * @param dataObject The object which will be saved
     * @param sqlQuery   The query
     * @param idColumn   If the ID Column is not specified by ID
     */
    public final void save(final Object dataObject, final String sqlQuery, final String idColumn) {
        try {
            PreparedStatement statement = _connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            String entityName = rs.getMetaData().getTableName(1);
            // If there is a next that means the Object which we want to save already exists
            if (rs.next()) {
                String insertQuery = "INSERT INTO %s (%s) VALUES (%s)";
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                HashMap<String, T> columns = new HashMap<String, T>();
                for (int ordinal = 1; ordinal <= columnCount; ordinal++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(ordinal);
                    String columnName = resultSetMetaData.getColumnName(ordinal);

                    String[] columnData = columnLabel.split(this._PropertyDelimiter);
                    String propertyName = columnData[columnData.length - 1];
                    String getPropertyMethodName = "get_" + propertyName;
                    Method getMethod = getMethod(getPropertyMethodName, dataObject);
                    //Method getMethod = dataObject.getClass().getMethod(getPropertyMethodName);
                    Object columnValue = getMethod.invoke(dataObject, null);
                    columns.put(columnName, (T) columnValue);
                }
                String allKeys = StringUtils.join(columns.keySet(), ", ");
                ArrayList<String> allValuesString = new ArrayList<String>();
                for (Object value : columns.values()) {
                    if (value instanceof String) {
                        allValuesString.add(String.format("'%s'", value));
                    } else {
                        allValuesString.add(String.format("%s", value.toString()));
                    }
                }
                String allValues = StringUtils.join(allValuesString, ", ");
                insertQuery = String.format(insertQuery, "Adresse", allKeys, allValues);
                PreparedStatement preparedStatement = _connection.prepareCall(insertQuery);
                preparedStatement.execute();
            }
            // Otherwise there is no Object so we can create an Insert Script
            else {
                String updateQuery = "Update %s SET %s WHERE %s = %d";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void ObjectToRecord(ResultSet next, Object dataObject, String idColumn) {
        Object tempObject;
        Class<?> type = Object.class;
    }

    private Method getMethod(String methodName, Object dataObject) {
        Method[] methods = dataObject.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

}
