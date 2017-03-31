package schedule.builder.database;

import parser.ClassParserReport;
import parser.CourseParserReport;
import parser.DemandReportParser;
import parser.LectureCoursesParser;

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

    public static void fillDB(){

        System.out.println("Filling DB with data from Reports folder");

        // parse and insert into tables
        ClassParserReport cpr = new ClassParserReport();
        cpr.startParse("Reports/ClassesReport.xls");
        dao.insertToTableClasses(cpr.getReport());

        CourseParserReport courseParse = new CourseParserReport();
        courseParse.startParse("Reports/CourseReport.xls");
        dao.insertToTableCourse(courseParse.getReport());

        DemandReportParser drp = new DemandReportParser();
        drp.startParse("Reports/DemandsReport.xls");
        dao.insertToTableDemand(drp.getReport());

        LectureCoursesParser lcp = new LectureCoursesParser();
        lcp.startParse("Reports/LecturerReport.xls");
        dao.insertToTableTeacherCourses(lcp.getReport());

        System.out.println("Done fill DB.");
    }

}
