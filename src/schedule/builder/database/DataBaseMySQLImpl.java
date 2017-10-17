package schedule.builder.database;

import objects.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBaseMySQLImpl extends MySql implements DataBase {

    private static DataBaseMySQLImpl instance = new DataBaseMySQLImpl();

    private DataBaseMySQLImpl() {
        super();
    }

    public static DataBaseMySQLImpl getInstance() {
        return instance;
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        // Load the MySQL driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager.getConnection(DB_URL + DB, USER, PASS);
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
            if (preparedStatement != null) {
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
        if (connect != null) {
            closeConnection();
        }
        useDatabase("");
        statement = connect.createStatement();
        String sql = "CREATE DATABASE " + dbName;
        statement.executeUpdate(sql);
        closeConnection();
    }

    @Override
    public void dropTable(String tableName) {
        try {
            connect();
            statement = connect.createStatement();
            String sql = "DROP TABLE " + tableName;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void autoDropTables(){
        dropTable("Classes");
        dropTable("Course");
        dropTable("teacher_course");
    }

    @Override
    public void useDatabase(String dbName) throws SQLException {
        if (connect != null) {
            closeConnection();
        }

        connect = DriverManager.getConnection(DB_URL + dbName, USER, PASS);
    }

    @Override
    public void dropDatabase(String dbName) throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            preparedStatement = connect.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public HashMap<String, Course> getAllCourses() {
        HashMap<String, Course> courses = new HashMap<>();
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM course";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Course c = new Course.CourseBuilder().setCode(resultSet.getString("code"))
                        .setName(resultSet.getString("name"))
                        .setYear(Integer.parseInt(resultSet.getString("year")))
                        .setSemester(Integer.parseInt(resultSet.getString("semester")))
                        .setDuration(resultSet.getInt("sem_hours"))
                        .setQuotaStudents(resultSet.getInt("quota"))
                        .setExpectedClasses(resultSet.getInt("groups"))
                        .build();
                courses.put(c.getCode(), c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return courses;
    }

    @Override
    public ArrayList<ClassRoom> getAllClassRooms() {
        ArrayList<ClassRoom> classes = new ArrayList<>();
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM classes";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                ClassRoom c = new ClassRoom.ClassRoomBuilder().setDay(resultSet.getInt("day")).
                        setHour(resultSet.getInt("hour")).
                        setSize(resultSet.getString("size").charAt(0)).build();
                classes.add(c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return classes;
    }

    @Override
    public HashMap<Integer, ArrayList<Demand>> getAllDemands() {
        HashMap<Integer, ArrayList<Demand>> demands = new HashMap<>();
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM demand";
            resultSet = statement.executeQuery(sqlQuery);
            Status[] statusValues = Status.values();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                // create new ArrayList for demands of teachers
                ArrayList<Demand> teacherDemand = null;
                // check if current teacher already exist in map
                if ((teacherDemand = demands.get(id)) == null) {
                    // need to create new array
                    teacherDemand = new ArrayList<>();
                }
                // create demand
                Demand d = new Demand.DemandBuilder().setDay(resultSet.getInt("day")).
                        setStart(resultSet.getInt("startHour")).
                        setEnd(resultSet.getInt("endHour")).
                        setReason(resultSet.getString("cause")).
                        setStatus(statusValues[resultSet.getInt("status")]).
                        build();
                // add it to array
                teacherDemand.add(d);
                // update array at map
                demands.put(id, teacherDemand);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return demands;
    }

    @Override
    public HashMap<Integer, Teacher> getAllTeachersCourse() {
        HashMap<Integer, Teacher> teachers = new HashMap<>();
        HashMap<Integer, String> teachersNames = new HashMap<>();
        HashMap<Integer, ArrayList<String>> teacherCourse = new HashMap<>();
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM TEACHER_COURSE";
            resultSet = statement.executeQuery(sqlQuery);
            ArrayList<String> courses;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String course = resultSet.getString("course");
                String name = resultSet.getString("name");
                if ((courses = teacherCourse.get(id)) == null) {
                    courses = new ArrayList<>();
                }
                courses.add(course);
                teacherCourse.put(id, courses);
                teachersNames.put(id, name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        for (Map.Entry<Integer, ArrayList<String>> tc : teacherCourse.entrySet()) {
            teachers.put(tc.getKey(), new Teacher.TeacherBuilder()
                    .setID(tc.getKey())
                    .setCourses(tc.getValue())
                    .setName(teachersNames.get(tc.getKey()))
                    .build());
        }
        return teachers;
    }

    @Override
    public HashMap<Integer, Teacher> getAllTeachers() {
        // TODO - create teacher table, fill it
        return null;
    }

    @Override
    public ArrayList<Demand> getDemandOfTeacher(String teacherID) {
        ArrayList<Demand> demands = new ArrayList<>();
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM demand WHERE id=" + teacherID;
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("id") == Integer.parseInt(teacherID)) {
                    Status stat = Status.PENDING;
                    switch (resultSet.getInt("status")) {
                        case 0:
                            stat = Status.PENDING;
                            break;
                        case 1:
                            stat = Status.ACCEPT;
                            break;
                        case 2:
                            stat = Status.DECLINE;
                            break;
                    }
                    Demand d = new Demand.DemandBuilder().setDay(resultSet.getInt("day")).
                            setStart(resultSet.getInt("startHour")).
                            setEnd(resultSet.getInt("endHour")).
                            setReason(resultSet.getString("cause")).
                            setStatus(stat).
                            build();
                    demands.add(d);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return demands;
    }

    @Override
    public int getPendingDemand() {
        ArrayList<Demand> demands = new ArrayList<>();
        int pendingCounter = 0;
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM demand WHERE status = 0";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                pendingCounter++;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return pendingCounter;
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
    public ArrayList<Integer> getTeacherForCourse(String courseName) {
        ArrayList<Integer> IDs = new ArrayList<>();
        try {
            connect();
            statement = connect.createStatement();
            String sqlQuery = "SELECT * FROM TEACHER_COURSE WHERE course='" + courseName + "'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                IDs.add(resultSet.getInt("id"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return IDs;
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
                "cause varchar(255)," +
                "status int(1) default 0," +
                "primary key(id, day, startHour, endHour)" +
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
        String sqlCommand = "CREATE TABLE TEACHER_COURSE(" +
                "id int(9)," +
                "name varchar(50)," +
                "course varchar(50)," +
                "primary key(id, course)" +
                ");";
        try {
            runCommand(sqlCommand);
            System.out.println("Succeed create Teacher Course table");
        } catch (SQLException e) {
            System.out.println("Failed create Teacher Course table");
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

            //create users table
            createUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void autoCreateDB() {
        try {
            useDatabase("sbdb");
            // create the database tables
            createCourseTable();
            createTeachersCoursesTable();
            createClassesTable();
            //create users table
//            createUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    private void createUsersTable() {
        String sqlCommand = "CREATE TABLE users(" +
                "id varchar(20) primary key," +
                "name varchar(100)," +
                "password varchar(20)," +
                "admin int(1) DEFAULT 0" +
                ");";
        try {
            runCommand(sqlCommand);
            System.out.println("Succeed create Users table");
        } catch (SQLException e) {
            System.out.println("Failed to create Users table");
            e.printStackTrace();
        }
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
        System.out.println("Done insert Course data.");
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

            String sql = "INSERT INTO DEMAND (id, day, startHour, endHour, cause, status) values" +
                    "(?,?,?,?,?,?)";
            PreparedStatement stmt = getPreparedStatement(sql);
            for (Demand demand : demandsList) {
                try {
                    stmt.setInt(1, Integer.parseInt(id));
                    stmt.setInt(2, demand.getDay());
                    stmt.setInt(3, demand.getStart());
                    stmt.setInt(4, demand.getEnd());
                    stmt.setString(5, demand.getReason());
                    stmt.setInt(6, 0);
                    stmt.executeUpdate();
                    rand = Math.random();
                    if (rand >= 0.25) {
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

    public void insertToTableTeacherCourses(HashMap<Integer, Teacher> lecturerCourse) {
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
        System.out.println("Start insert into Teacher Course table");
        for (Map.Entry<Integer, Teacher> entry : lecturerCourse.entrySet()) {
            int id = entry.getKey();
            Teacher teacher = entry.getValue();

            String sql = "INSERT INTO TEACHER_COURSE (id, name, course) values" +
                    "(?,?,?)";
            PreparedStatement stmt = getPreparedStatement(sql);
            for (String course : teacher.getCourses()) {
                try {
                    stmt.setInt(1, teacher.getID());
                    stmt.setString(2, teacher.getName());
                    stmt.setString(3, course);
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
        System.out.println("Done insert Teacher Course data.");
        closeConnection();
    }

    @Override
    public void addDemand(String id, int day, int start, int end, String reason, Status status) throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create the sql records to be insert
        String sql = "INSERT INTO DEMAND (id, day, startHour, endHour, cause, status) values" +
                "(?,?,?,?,?,?)";
        PreparedStatement stmt = getPreparedStatement(sql);
        stmt.setInt(1, Integer.parseInt(id));
        stmt.setInt(2, day);
        stmt.setInt(3, start);
        stmt.setInt(4, end);
        stmt.setString(5, reason);
        stmt.setInt(6, status.ordinal());
        stmt.executeUpdate();
        closeConnection();
    }

    @Override
    public HashMap<Integer, String> getTeacherIdNameMap() {
        HashMap<Integer, String> idToName = new HashMap<>();
        try {
            connect();
            statement = connect.createStatement();
            // create Map to return

            // create the sql records to be insert
            String sqlQuery = "select DISTINCT id, name from teacher_course";
            try {
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    idToName.put(id, name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return idToName;
    }

    public void changeDemandStatus(int id, Demand d, Status s){
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = connect.createStatement();
            String sql = "UPDATE DEMAND " +
                    "SET status=" + s.ordinal() +
                    " WHERE id=" + id + " AND day=" + d.getDay() +" AND startHour=" + d.getStart() +
                    " AND endHour=" +d.getEnd();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }
}
