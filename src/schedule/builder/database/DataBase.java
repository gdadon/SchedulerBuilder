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

    void createDatabase(String dbName) throws Exception;

    void useDatabase(String dbName) throws SQLException;

    void dropDatabase(String dbName) throws SQLException;

    void createTable(String createTableCommand) throws Exception;

    void runCommand(String sqlCommand) throws Exception;

    PreparedStatement getPreparedStatement(String sql);

    ArrayList<Course> getAllCourses();

    ArrayList<ClassRoom> getAllClassRooms();

    HashMap<Integer, Demand> getAllDemands();

    ArrayList<TeacherCourse> getAllTeachersCourse();

    ArrayList<Teacher> getAllTeachers();

    ArrayList<Demand> getDemandOfTeacher(String teacherID);

    Course getCourseByName(String courseName);

    Teacher getTeacherByName(String teacherName);

    ArrayList<Teacher> getTeacherForCourse(String courseName);

}
