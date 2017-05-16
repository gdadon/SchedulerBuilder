package schedule.builder.database;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Guy on 11/05/2017.
 */
public class DBUsersMySqlImpl extends MySql implements DBUsers {

    private static DBUsersMySqlImpl instance = new DBUsersMySqlImpl();

    private DBUsersMySqlImpl(){
        super();
    }

    public static DBUsersMySqlImpl getInstance(){
        return instance;
    }

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
            System.out.println("Connection to DB closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int checkLoginUser(String id, String password) {
        int privilege = -1;
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM users WHERE id='"+ id +"'";
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    privilege = resultSet.getInt("admin");
                }
                else{
                    return -2;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return privilege;
    }
}
