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

    void insertToTable(String dbName, String tableName, String insertCommand);

    void insertToTable(String dbName, String tableName, HashMap data);

    void insertToTable(String dbName, String tableName, ArrayList data);

    void dropTable(String dbName, String tableName);

    ResultSet query(String query) throws Exception;
}
