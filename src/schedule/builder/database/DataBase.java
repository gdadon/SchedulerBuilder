package schedule.builder.database;

import objects.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guy on 20/01/2017.
 */
public interface DataBase {

    void connect() throws Exception;

    void closeConnection();

    void dropTable(String tableName);

    void createDatabase(String dbName) throws Exception;

    void useDatabase(String dbName) throws SQLException;

    void dropDatabase(String dbName) throws SQLException;

    void createTable(String createTableCommand) throws Exception;

    void runCommand(String sqlCommand) throws Exception;

    PreparedStatement getPreparedStatement(String sql);

    HashMap<String, Course> getAllCourses();

    ArrayList<ClassRoom> getAllClassRooms();

    HashMap<Integer, ArrayList<Demand>> getAllDemands();

    HashMap<Integer, Teacher> getAllTeachersCourse();

    HashMap<Integer, Teacher> getAllTeachers();

    ArrayList<Demand> getDemandOfTeacher(String teacherID);

    Course getCourseByName(String courseName);

    Teacher getTeacherByName(String teacherName);

    ArrayList<Integer> getTeacherForCourse(String courseName);

    void addDemand(String id, int day, int start, int end, String reason, Status status) throws SQLException;

    int getPendingDemand();

    HashMap<Integer, String> getTeacherIdNameMap();
}
