package database;

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

    HashMap<String, Course> getAllCourses();

    HashMap<Integer, ClassRoom> getAllClassRooms();

    HashMap<String, ArrayList<Demand>> getAllDemands();

    HashMap<String, TeacherCourse> getAllTeachers();

    ArrayList<Demand> getDemandOfTeacher(String teacherName);

    ClassRoom getClassRoom(int day, int hour, char size);

    Course getCourseByName(String courseName);

    Teacher getTeacherByName(String teacherName);

    ArrayList<Teacher> getTeacherForCourse(String courseName);

}
