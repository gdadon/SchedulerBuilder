package schedule.builder.launcher;

import parser.ClassParserReport;
import parser.CourseParserReport;
import parser.DemandReportParser;
import parser.LectureCoursesParser;
import schedule.builder.database.SBDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Guy on 25/01/2017.
 */
public class Launcher {

    public static void main(String[] args) {

        SBDatabase sbdb = new SBDatabase();

        //create the scheduler db
        sbdb.createDB();

        // parse and insert into tables

        ClassParserReport cpr = new ClassParserReport();
        cpr.startParse("Reports/ClassesReport.xls");
        sbdb.insertToTableClasses(cpr.getReport());

        CourseParserReport courseParse = new CourseParserReport();
        courseParse.startParse("Reports/CourseReport.xls");
        sbdb.insertToTableCourse(courseParse.getReport());

        DemandReportParser drp = new DemandReportParser();
        drp.startParse("Reports/DemandsReport.xls");
        sbdb.insertToTableDemand(drp.getReport());

        LectureCoursesParser lcp = new LectureCoursesParser();
        lcp.startParse("Reports/LecturerReport.xls");
        sbdb.insertToTableLectureCourse(lcp.getReport());

        sbdb.close();

    }
}
