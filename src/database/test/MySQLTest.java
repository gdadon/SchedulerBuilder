package database.test;

import database.DataBaseMySQLImpl;

import java.sql.SQLException;

/**
 * Created by Guy on 20/01/2017.
 */
public class MySQLTest {

    public static void main(String[] args) {
        DataBaseMySQLImpl dao = new DataBaseMySQLImpl();

        System.out.println("Connecting to DB");
        try {
            dao.connect();
            System.out.println("Connected to DB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println("Trying to create new database");
//            dao.createDatabase("test");
//            System.out.println("Create new Database");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            System.out.println("Trying to drop database");
//            dao.dropDatabase("test");
//            System.out.println("Drop Database");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        System.out.println("Trying create table - course");
        String courseTable = "CREATE TABLE Course(" +
                "code int(20) primary key," +
                "name varchar(50)," +
                "year varchar(1)," +
                "semester varchar(1)," +
                "sem_hours int(1)," +
                "points int(1)," +
                "expectedStudents int(4)," +
                "quato int(4)," +
                "groups int(2)," +
                "major varchar(30)," +
                "isSpring bool" +
                ");";
        try {
            dao.createTable(courseTable);
            System.out.println("Succeed create course table");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println("Trying to switch database");
//            dao.useDatabase("world");
//            System.out.println("Succeed switch database");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
