package database;

import java.sql.*;

public class MySQLAccess implements IDataBaseAccess{

    // JDBC driver name and database URL
    private final String DB_URL = "jdbc:mysql://localhost/";
    private final String DB = "sbdb";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "123456";

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        // Load the MySQL driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connect != null) {
                connect.close();
            }
            System.out.println("Connection to DB closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDatabase(String dbName) throws SQLException {
//        connect = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connect.createStatement();
        String sql = "CREATE DATABASE " + dbName;
        statement.executeUpdate(sql);
    }

    @Override
    public void dropDatabase(String dbName) throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        statement = connect.createStatement();
        String sql = "DROP DATABASE " + dbName;
        statement.executeUpdate(sql);
        closeConnection();
    }

    @Override
    public void useDatabase(String dbName) throws SQLException {
        closeConnection();

        connect = DriverManager.getConnection(DB_URL+dbName, USER, PASS);
    }

    @Override
    public void createTable(String createTableCommand) throws SQLException {
        statement = connect.createStatement();
        statement.executeUpdate(createTableCommand);
    }

    @Override
    public void dropTable(String dbName, String tableName) {

    }

    @Override
    public void runCommand(String sqlCommand) throws SQLException {
        statement = connect.createStatement();
        statement.executeUpdate(sqlCommand);
    }

    @Override
    public ResultSet query(String query) throws SQLException, ClassNotFoundException {
        connect();
        resultSet = connect.createStatement().executeQuery(query);
        // close database connection
        closeConnection();
        return resultSet;
    }

    public PreparedStatement getPreparedStatement(String sql){
        try {
            preparedStatement = connect.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
