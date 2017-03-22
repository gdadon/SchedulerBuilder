package schedule.builder.launcher;

import database.DataBaseMySQLImpl;
import parser.ClassParserReport;
import parser.CourseParserReport;
import parser.DemandReportParser;
import parser.LectureCoursesParser;
import schedule.builder.database.DatabaseUtil;

public class Launcher {

    public static void main(String[] args) {

    //check if DB exsist, if no build it
        DatabaseUtil.CreateAndFillDB();


    }
}
