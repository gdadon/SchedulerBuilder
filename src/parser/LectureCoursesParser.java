package parser;

import objects.LectureCourseToDB;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class LectureCoursesParser implements ParserInterface {

    @Override
    //TODO change path to fileName
    public void startParse(String fileName) {
        try {
            HashMap<String, LectureCourseToDB> lectureCourseToDBHashMap = new HashMap<>();
            //Add load dialog
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Tomer\\Desktop\\4.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0); //Sheet in index 0
            Row row = null; //Row
            Cell cell = null; //Column
            //Starting parse from C2 -> System.out.println(cell.getAddress());
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i < lastRowNum + 1; i++) {
                row = sheet.getRow(i);
                int j = 0;
                cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String lectureID = cell.getRichStringCellValue().getString();
                ++j;
                cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                String lectureName = cell.getRichStringCellValue().getString();
                lectureCourseToDBHashMap.put(lectureID,new LectureCourseToDB(lectureID,lectureName));
                ++j;
                while ((cell = row.getCell(j))!=null){
                    cell.setCellType(CellType.STRING);
                    if(cell.getRichStringCellValue().getString().equals("")) {
                        break;
                    }
                    String courseID = cell.getRichStringCellValue().getString();
                    lectureCourseToDBHashMap.get(lectureID).addCourse(courseID);
                    ++j;
                }
            }
            file.close();
            for (HashMap.Entry<String, LectureCourseToDB> entry : lectureCourseToDBHashMap.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LectureCoursesParser a = new LectureCoursesParser();
        a.startParse("a");
    }
}
