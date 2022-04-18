package org.example.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Nemes Mihnea-Andrei
 * AbstractDAO
 * it implements the methods to execute the queries through reflection techniques
 * @since 27 Apr, 2021
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * constructor
     * initializes the type field
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * createInsertQuery
     * create the insert query based on a parameter of type T, to insert into the database
     *
     * @param t
     * @return String
     * @throws IllegalAccessException
     */
    private String createInsertQuery(T t) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO assign3." + type.getSimpleName() + "(");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            sb.append(name + ",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(") VALUES (");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            T value = (T) field.get(t);
            if (field.getType().getSimpleName().equals("String")) {
                sb.append("\"" + value + "\",");
            } else {
                sb.append(value + ",");
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(");");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * createUpdateQuery
     * creates the update query to change the values of the fields of a given database entry specified into the database by id
     * with the values given as a parameter in the form of the t parameter
     *
     * @param t
     * @param id
     * @return String
     * @throws IllegalAccessException
     */
    private String createUpdateQuery(T t, int id) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName() + " SET ");
        int i = 0;
        for (Field field : t.getClass().getDeclaredFields()) {
            if (i == 0) {
                i++;
                continue;
            }
            field.setAccessible(true);
            String name = field.getName();
            sb.append(name + " = ");
            T value = (T) field.get(t);
            if (field.getType().getSimpleName().equals("String")) {
                sb.append("\"" + value + "\",");
            } else {
                sb.append(value + ",");
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(" WHERE id=" + id);
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * createDeleteQuery
     * creates the delete query to delete an element with id given as a parameter
     *
     * @param id
     * @return String
     */
    private String createDeleteQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append("assign3." + type.getSimpleName());
        sb.append(" WHERE ");
        sb.append("id=");
        sb.append(id);
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * createSelectQuery
     * creates the select query which will return an element by its id
     *
     * @param field
     * @return String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("assign3." + type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * createSelectAllQuery
     * creates the query which will return all the elements from a table
     *
     * @return String
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("assign3." + type.getSimpleName());
        return sb.toString();
    }

    /**
     * findAll
     * executes the findAll query to select all the elements from a table
     *
     * @return List<T>
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * findById
     * executes the query to select an element from a table with id given as a parameter
     *
     * @param id
     * @return T
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * createObjects
     * returns the list of objects created from the ResultSet
     *
     * @param resultSet
     * @return List<T>
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * insert
     * executes the insert query to add an entry to a table with values given as a parameter
     *
     * @param t
     * @throws IllegalAccessException
     */
    public void insert(T t) throws IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * update
     * executes the update query to update the values of a table entry with id given as a parameter
     * using the values from the t parameter
     *
     * @param t
     * @param id
     * @throws IllegalAccessException
     */
    public void update(T t, int id) throws IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t, id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * delete
     * executes the delete query to delete an entry from a table with id given as a parameter
     *
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * getColumnsHead
     * instantiates the columns of a table through reflection techniques
     *
     * @param t
     * @return ObservableList<TableColumn < T, ?>>
     */
    public ObservableList<TableColumn<T, ?>> getColumnsHead(T t) {
        ObservableList<TableColumn<T, ?>> columnsList = FXCollections.observableArrayList();
        for (Field field : t.getClass().getDeclaredFields()) {
            TableColumn currColumn = new TableColumn();
            currColumn.setText(field.getName());
            currColumn.setCellValueFactory(new PropertyValueFactory<T, String>(field.getName()));
            columnsList.add(currColumn);
        }
        return columnsList;
    }
}
