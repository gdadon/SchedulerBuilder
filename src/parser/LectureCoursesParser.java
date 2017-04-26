package parser;

import objects.Teacher;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class LectureCoursesParser implements ParserInterface {

    HashMap<Integer, Teacher> teacherCourseReport = null;

    @Override
    public void startParse(String fileName) {
        try {
            teacherCourseReport = new HashMap<>();
            //Add load dialog
            FileInputStream file = new FileInputStream(new File(fileName));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0); //Sheet in index 0
            Row row = null; //Row
            Cell cell = null; //Column
            //Starting parse from C2 -> System.out.println(cell.getAddress());
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i < lastRowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;
                cell = row.getCell(j);
                if(cell == null){
                    break;
                }
                cell.setCellType(CellType.STRING);
                int teacherID = Integer.parseInt(cell.getRichStringCellValue().getString());
                ++j;
                cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String name = cell.getRichStringCellValue().getString();
                teacherCourseReport.put(teacherID,new Teacher.TeacherBuilder()
                        .setID(teacherID).setName(name)
                        .build());
                ++j;
                while ((cell = row.getCell(j))!=null){
                    cell.setCellType(CellType.STRING);
                    if(cell.getRichStringCellValue().getString().equals("")) {
                        break;
                    }
                    String courseID = cell.getRichStringCellValue().getString();
                    teacherCourseReport.get(teacherID).addCourse(courseID);
                    ++j;
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap getReport() {
        return teacherCourseReport;
    }

}
