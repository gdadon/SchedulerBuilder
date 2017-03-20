package schedule.builder.database;

import database.MySQLAccess;
import objects.ClassRoom;
import objects.Course;
import objects.Demand;
import objects.TeacherCourse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SBDatabase {

    private MySQLAccess dao = null;

    public SBDatabase() {
        dao = new MySQLAccess();
        try {
            dao.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        dao.closeConnection();
    }

    private void createClassesTable() {
        String sqlCommand = "CREATE TABLE Classes(" +
                "size varchar(1)," +
                "day int(1)," +
                "hour int(2)," +
                "primary key(size, day, hour)" +
                ");";
        try {
            dao.runCommand(sqlCommand);
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
            dao.runCommand(sqlCommand);
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
            dao.runCommand(sqlCommand);
            System.out.println("Succeed create Demand table");
        } catch (SQLException e) {
            System.out.println("Failed create Demand table");
            e.printStackTrace();
        }
    }

    private void createLectureCourseTable() {
        String sqlCommand = "CREATE TABLE Lecturer(" +
                "id int(9)," +
                "course varchar(50)," +
                "primary key(id, course)" +
                ");";
        try {
            dao.runCommand(sqlCommand);
            System.out.println("Succeed create Lecturer table");
        } catch (SQLException e) {
            System.out.println("Failed create Lecturer table");
            e.printStackTrace();
        }
    }

    public void createDB() {
        try {
            // create the database
            dao.createDatabase("sbdb");
            dao.useDatabase("sbdb");
            // create the database tables
            createCourseTable();
            createLectureCourseTable();
            createDemandTable();
            createClassesTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertToTableClasses(ArrayList<ClassRoom> classRooms) {
        // use the sbdb database
        try {
            dao.useDatabase("sbdb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create the sql records to be insert
        StringBuffer sql = new StringBuffer("");
        double rand = 0;
        System.out.println("Start insert into Classes table:");
        for (ClassRoom clas : classRooms) {
            sql.append("INSERT INTO CLASSES (size, day, hour) VALUES (");
            sql.append("'" + clas.getSize() + "', ");
            sql.append(clas.getDay() + ", ");
            sql.append(clas.getHour() + ")");
            try {
                dao.runCommand(String.valueOf(sql));
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
    }

    public void insertToTableCourse(HashMap<String, Course> courses) {
        // use the sbdb database
        try {
            dao.useDatabase("sbdb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create the sql records to be insert
        double rand = 0;
        System.out.println("Start insert into Course table");
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            Course course = entry.getValue();

            String sql = "INSERT INTO COURSE (code, name, year, semester, sem_hours," +
                    "points, expectedStudents, quota, groups) VALUES " +
                    "(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = dao.getPreparedStatement(sql);
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
    }

    public void insertToTableDemand(HashMap<String, ArrayList<Demand>> demands) {
        // use the sbdb database
        try {
            dao.useDatabase("sbdb");
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
            PreparedStatement stmt = dao.getPreparedStatement(sql);
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
    }

    public void insertToTableLectureCourse(HashMap<String, TeacherCourse> lecturerCourse){
        // use the sbdb database
        try {
            dao.useDatabase("sbdb");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // create the sql records to be insert
        double rand = 0;
        System.out.println("Start insert into Lecturer table");
        for (Map.Entry<String, TeacherCourse> entry : lecturerCourse.entrySet()) {
            String id = entry.getKey();
            TeacherCourse lecturer = entry.getValue();

            String sql = "INSERT INTO LECTURER (id, course) values" +
                    "(?,?)";
            PreparedStatement stmt = dao.getPreparedStatement(sql);
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
    }

}