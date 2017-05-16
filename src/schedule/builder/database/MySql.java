package schedule.builder.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Guy on 11/05/2017.
 */
public class MySql {

    // JDBC driver name and database URL
    protected final String DB_URL = "jdbc:mysql://localhost/";
    protected final String DB = "sbdb";

    //  Database credentials
    protected final String USER = "root";
    protected final String PASS = "123456";

    protected Connection connect = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;

}
