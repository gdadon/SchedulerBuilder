package database;

import java.sql.*;

public class MySQLAccess implements IDataBaseAccess{

    // JDBC driver name and database URL
    private final String DB_URL = "jdbc:mysql://localhost/";
    private final String DB = "sbdb";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "123456";

    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        // Load the MySQL driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager.getConnection(DB_URL+DB, USER, PASS);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDatabase(String dbName) throws SQLException {
        statement = connect.createStatement();
        String sql = "CREATE DATABASE " + dbName;
        statement.executeUpdate(sql);
    }

    @Override
    public void dropDatabase(String dbName) throws SQLException {
        statement = connect.createStatement();
        String sql = "DROP DATABASE " + dbName;
        statement.executeUpdate(sql);
    }

    @Override
    public void useDatabase(String dbName) throws SQLException {
        closeConnection();

        connect = DriverManager.getConnection(DB_URL+dbName, USER, PASS);
    }

    @Override
    public void createTable(String createTableCommand) throws SQLException {
        connect.createStatement().executeUpdate(createTableCommand);
    }

    @Override
    public void insertToTable(String dbName, String tableName, String insertCommand) {

    }

    @Override
    public void dropTable(String dbName, String tableName) {

    }

    @Override
    public ResultSet query(String query) throws SQLException, ClassNotFoundException {
        // get database connection
//        connect();

        resultSet = connect.createStatement().executeQuery(query);



        // close database connection
        closeConnection();
        return null;
    }
}
