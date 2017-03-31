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

    public static void fillDB(String folderName){
        folderName = "Reports/" + folderName;

        System.out.println("Filling DB with data from Reports folder");

        // parse and insert into tables
        ClassParserReport cpr = new ClassParserReport();
        cpr.startParse(folderName + "/ClassesReport.xls");
        dao.insertToTableClasses(cpr.getReport());

        CourseParserReport courseParse = new CourseParserReport();
        courseParse.startParse(folderName + "/CourseReport.xls");
        dao.insertToTableCourse(courseParse.getReport());

        DemandReportParser drp = new DemandReportParser();
        drp.startParse(folderName + "/DemandsReport.xls");
        dao.insertToTableDemand(drp.getReport());

        LectureCoursesParser lcp = new LectureCoursesParser();
        lcp.startParse(folderName + "/LecturerReport.xls");
        dao.insertToTableTeacherCourses(lcp.getReport());

        System.out.println("Done fill DB.");
    }

}
