package schedule.builder.database;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Guy on 11/05/2017.
 */
public interface DBUsers {

    void connect() throws ClassNotFoundException, SQLException;

    void closeConnection();

    /**
     * check if user exist in DB
     * @param id
     * @param password
     * @return 0 if user has normal privilege,
     *          1 if user is admin,
     *          -1 if user don't exist
     *          -2 if wrong password
     */
    int checkLoginUser(String id, String password);

    Map<String, String> getAllUsers();



}
