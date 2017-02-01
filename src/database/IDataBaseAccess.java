package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guy on 20/01/2017.
 */
public interface IDataBaseAccess {

    void connect() throws Exception;

    void closeConnection();

    void createDatabase(String dbName) throws Exception;

    void dropDatabase(String dbName) throws Exception;

    void useDatabase(String dbName) throws Exception;

    void createTable(String createTableCommand) throws Exception;

    void dropTable(String dbName, String tableName);

    void runCommand(String sqlCommand) throws Exception;

    ResultSet query(String query) throws Exception;
}
