package schedule.builder.database;

import objects.ClassRoom;
import objects.Course;
import objects.Demand;
import objects.Teacher;

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

    ArrayList<ClassRoom> getAllClassRooms();

    HashMap<Integer, Demand> getAllDemands();

    HashMap<Integer, Teacher> getAllTeachersCourse();

    HashMap<Integer, Teacher> getAllTeachers();

    ArrayList<Demand> getDemandOfTeacher(String teacherID);

    Course getCourseByName(String courseName);

    Teacher getTeacherByName(String teacherName);

    ArrayList<Integer> getTeacherForCourse(String courseName);

}
