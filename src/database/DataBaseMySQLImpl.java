package database;

import objects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseMySQLImpl implements DataBase {

    private static DataBaseMySQLImpl instance = new DataBaseMySQLImpl();

    // JDBC driver name and database URL
    private final String DB_URL = "jdbc:mysql://localhost/";
    private final String DB = "sbdb";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "123456";

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static DataBaseMySQLImpl getInstance(){
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
    public void createDatabase(String dbName) throws SQLException {
        if(connect != null){
            closeConnection();
        }
        useDatabase("");
        statement = connect.createStatement();
        String sql = "CREATE DATABASE " + dbName;
        statement.executeUpdate(sql);
        closeConnection();
    }

    @Override
    public void useDatabase(String dbName) throws SQLException {
        if(connect != null) {
            closeConnection();
        }

        connect = DriverManager.getConnection(DB_URL+dbName, USER, PASS);
    }

    @Override
    public void dropDatabase(String dbName) throws SQLException {
        statement = connect.createStatement();
        String sql = "DROP DATABASE " + dbName;
        statement.executeUpdate(sql);
        closeConnection();
    }

    @Override
    public void createTable(String createTableCommand) throws SQLException {
        statement = connect.createStatement();
        statement.executeUpdate(createTableCommand);
    }

    @Override
    public void runCommand(String sqlCommand) throws SQLException {
        statement = connect.createStatement();
        statement.executeUpdate(sqlCommand);
    }

    @Override
    public PreparedStatement getPreparedStatement(String sql){
        try {
            preparedStatement = connect.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public HashMap<String, Course> getAllCourses() {
        return null;
    }

    @Override
    public HashMap<Integer, ClassRoom> getAllClassRooms() {
        return null;
    }

    @Override
    public HashMap<String, ArrayList<Demand>> getAllDemands() {
        return null;
    }

    @Override
    public HashMap<String, TeacherCourse> getAllTeachers() {
        return null;
    }

    @Override
    public ArrayList<Demand> getDemandOfTeacher(String teacherName) {
        return null;
    }

    @Override
    public ClassRoom getClassRoom(int day, int hour, char size) {
        return null;
    }

    @Override
    public Course getCourseByName(String courseName) {
        return null;
    }

    @Override
    public Teacher getTeacherByName(String teacherName) {
        return null;
    }

    @Override
    public ArrayList<Teacher> getTeacherForCourse(String courseName) {
        return null;
    }

    private void createClassesTable() {
        String sqlCommand = "CREATE TABLE Classes(" +
                "size varchar(1)," +
                "day int(1)," +
                "hour int(2)," +
                "primary key(size, day, hour)" +
                ");";
        try {
            runCommand(sqlCommand);
            System.out.println("Succeed create Classes table");
        } catch (SQLException e) {
            System.out.println("Failed to create Classes table");
            e.printStackTrace();
        }
    }

    private void createCourseTable() {
        String sqlCommand = "CREATE TABLE Course(" +
                "code varchar(50) primary key," +
                "name varchar(50)," +
                "year varchar(1)," +
                "semester varchar(1)," +
                "sem_hours int(1)," +
                "points double(2,1)," +
                "expectedStudents int(4)," +
                "quota int(4)," +
                "groups int(2)" +
                ");";
        try {
            runCommand(sqlCommand);
            System.out.println("Succeed create Course table");
        } catch (SQLException e) {
            System.out.println("Failed create Course table");
            e.printStackTrace();
        }
    }

    private void createDemandTable() {
        String sqlCommand = "CREATE TABLE Demand(" +
                "id int(9)," +
                "day int(1)," +
                "startHour int(2)," +
                "endHour int(2)," +
                "totalHours int(2)," +
                "cause varchar(255)," +
                "primary key(id, day, startHour)" +
                ");";
        try {
            runCommand(sqlCommand);
            System.out.println("Succeed create Demand table");
        } catch (SQLException e) {
            System.out.println("Failed create Demand table");
            e.printStackTrace();
        }
    }

    private void createTeachersCoursesTable() {
        String sqlCommand = "CREATE TABLE TEACHER(" +
                "id int(9)," +
                "course varchar(50)," +
                "primary key(id, course)" +
                ");";
        try {
            runCommand(sqlCommand);
            System.out.println("Succeed create Teacher table");
        } catch (SQLException e) {
            System.out.println("Failed create Teacher table");
            e.printStackTrace();
        }
    }

    public void createDB() {
        try {
            // create the database
            createDatabase("sbdb");
            System.out.println("Created DB successfully.");
            useDatabase("sbdb");
            // create the database tables
            createCourseTable();
            createTeachersCoursesTable();
            createDemandTable();
            createClassesTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void insertToTableCourse(HashMap<String, Course> courses) {
        // create the sql records to be insert
        double rand = 0;
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Start insert into Course table");
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            Course course = entry.getValue();

            String sql = "INSERT INTO COURSE (code, name, year, semester, sem_hours," +
                    "points, expectedStudents, quota, groups) VALUES " +
                    "(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = getPreparedStatement(sql);
            try {
                stmt.setString(2, course.getName());
                stmt.setInt(3, course.getYear());
                stmt.setInt(4, course.getSemester());
                stmt.setInt(5, course.getDuration());
                stmt.setDouble(6, course.getPoints());
                stmt.setInt(7, course.getExpectedStudents());
                stmt.setInt(8, course.getQuotaStudents());
                stmt.setInt(9, course.getExpectedClasses());
                for (int i = 0; i < course.getExpectedClasses(); i++) {
                    String code = course.getCode() + "-" + i;
                    stmt.setString(1, code);
                    stmt.executeUpdate();
                    rand = Math.random();
                    if (rand >= 0.5) {
                        System.out.print("#");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        System.out.println();
        System.out.println("Done insert Classes data.");
        closeConnection();
    }

    public void insertToTableClasses(HashMap<Integer, ClassRoom> classRooms) {
        // create the sql records to be insert
        StringBuffer sql = new StringBuffer("");
        double rand = 0;
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Start insert into Classes table:");
        for (Map.Entry<Integer, ClassRoom> entry : classRooms.entrySet()) {
            sql.append("INSERT INTO CLASSES (size, day, hour) VALUES (");
            sql.append("'" + entry.getValue().getSize() + "', ");
            sql.append(entry.getValue().getDay() + ", ");
            sql.append(entry.getValue().getHour() + ")");
            try {
                runCommand(String.valueOf(sql));
                rand = Math.random();
                if (rand >= 0.5) {
                    System.out.print("#");
                }
                sql.delete(0, sql.length());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Done insert Classes data.");
        closeConnection();
    }

    public void insertToTableDemand(HashMap<String, ArrayList<Demand>> demands) {
        // use the sbdb database
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create the sql records to be insert
        double rand = 0;
        System.out.println("Start insert into Demands table");
        for (Map.Entry<String, ArrayList<Demand>> entry : demands.entrySet()) {
            String id = entry.getKey();
            ArrayList<Demand> demandsList = entry.getValue();

            String sql = "INSERT INTO DEMAND (id, day, startHour, endHour, totalHours, cause) values" +
                    "(?,?,?,?,?,?)";
            PreparedStatement stmt = getPreparedStatement(sql);
            for (Demand demand : demandsList) {
                try {
                    stmt.setInt(1, Integer.parseInt(id));
                    stmt.setInt(2, demand.getDay());
                    stmt.setInt(3, demand.getStart());
                    stmt.setInt(4, demand.getEnd());
                    stmt.setInt(5, (demand.getEnd() - demand.getStart()));
                    stmt.setString(6, demand.getReason());
                    stmt.executeUpdate();
                    rand = Math.random();
                    if (rand >= 0.5) {
                        System.out.print("#");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

            }
        }

        System.out.println();
        System.out.println("Done insert Demands data.");
        closeConnection();
    }

    public void insertToTableTeacherCourses(HashMap<String, TeacherCourse> lecturerCourse){
        // use the sbdb database
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create the sql records to be insert
        double rand = 0;
        System.out.println("Start insert into Teachers table");
        for (Map.Entry<String, TeacherCourse> entry : lecturerCourse.entrySet()) {
            String id = entry.getKey();
            TeacherCourse lecturer = entry.getValue();

            String sql = "INSERT INTO TEACHER (id, course) values" +
                    "(?,?)";
            PreparedStatement stmt = getPreparedStatement(sql);
            for (String course : lecturer.getCourses()) {
                try {
                    stmt.setInt(1, Integer.parseInt(lecturer.getID()));
                    stmt.setString(2, course);
                    stmt.executeUpdate();
                    rand = Math.random();
                    if (rand >= 0.5) {
                        System.out.print("#");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

            }
        }

        System.out.println();
        System.out.println("Done insert Lecturer data.");
        closeConnection();
    }

}
