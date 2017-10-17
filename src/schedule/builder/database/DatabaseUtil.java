package schedule.builder.database;

import parser.ClassParserReport;
import parser.CourseParserReport;
import parser.LectureCoursesParser;

import java.sql.SQLException;

/**
 * Created by Guy on 22/03/2017.
 */
public class DatabaseUtil {

    private static DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();

    public static void createDB(){
        System.out.println("Creating DB: Scheduler Builder Data Base (sbdb)");
        //create the scheduler db
        dao.createDB();
        System.out.println("Succeeded creating DB.");
    }

    public static void autoDropTables(){
        // drop previous tables
        dao.autoDropTables();
        System.out.println("Dropped previous tables");
    }

    public static void autoCreateDB(){
        System.out.println("Creating DB: Scheduler Builder Data Base (sbdb)");
        //create the scheduler db
        dao.autoCreateDB();
        System.out.println("Succeeded creating DB.");
    }

    public static void dropDataBase(String dbName){
        try {
            dao.dropDatabase(dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Succeeded drop DB " + dbName);
    }

    public static void autoFillDB(String folderName){
        folderName = "Reports/" + folderName;

        System.out.println("Filling DB with data from Reports folder");

        // parse and insert into tables
        ClassParserReport cpr = new ClassParserReport();
        cpr.startParse(folderName + "/ClassesReport.xls");
        dao.insertToTableClasses(cpr.getReport());

        CourseParserReport courseParse = new CourseParserReport();
        courseParse.startParse(folderName + "/CourseReport.xls");
        dao.insertToTableCourse(courseParse.getReport());

//        DemandReportParser drp = new DemandReportParser();
//        drp.startParse(folderName + "/DemandsReport.xls");
//        dao.insertToTableDemand(drp.getReport());

        LectureCoursesParser lcp = new LectureCoursesParser();
        lcp.startParse(folderName + "/LecturerReport.xls");
        dao.insertToTableTeacherCourses(lcp.getReport());

        System.out.println("Done fill DB.");
    }

    public static void fillDB(String classReport, String CourseReport, String teachersReport){

        System.out.println("Filling DB with data from Reports folder");

        // parse and insert into tables
        // make sure to drop previous table
        ClassParserReport cpr = new ClassParserReport();
        cpr.startParse(classReport);
        dao.insertToTableClasses(cpr.getReport());

        CourseParserReport courseParse = new CourseParserReport();
        courseParse.startParse(CourseReport);
        dao.insertToTableCourse(courseParse.getReport());

        LectureCoursesParser lcp = new LectureCoursesParser();
        lcp.startParse(teachersReport);
        dao.insertToTableTeacherCourses(lcp.getReport());

        System.out.println("Done fill DB.");
    }

}
