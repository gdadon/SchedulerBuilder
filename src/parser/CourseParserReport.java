package parser;

import objects.CourseToDB;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class CourseParserReport implements ParserInterface {

    private HashMap<String, CourseToDB> courseParseToDB = null;

    @Override
    public void startParse(String filePath) {
        try {
            courseParseToDB = new HashMap<>();
            //Add load dialog
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0); //Sheet in index 0
            Row row = null; //Row
            Cell cell = null; //Column
            int lastRowNum = sheet.getLastRowNum();
            //Parsing from A2 -> lastRowNum
            for (int i = 1; i < lastRowNum + 1; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(0);
                cell.setCellType(CellType.STRING);
                String courseCode = cell.getRichStringCellValue().getString();
                cell = row.getCell(1);
                String courseName = cell.getRichStringCellValue().getString();
                cell = row.getCell(2);
                int courseYear = parseYearSemester(cell.getRichStringCellValue().getString());
                cell = row.getCell(3);
                int courseSemester = parseYearSemester(cell.getRichStringCellValue().getString());
                cell = row.getCell(4);
                cell.setCellType(CellType.STRING);
                int courseDuration = Integer.parseInt(cell.getRichStringCellValue().getString());
                cell = row.getCell(5);
                cell.setCellType(CellType.STRING);
                double coursePoints = Double.parseDouble(cell.getRichStringCellValue().getString());
                cell = row.getCell(6);
                cell.setCellType(CellType.STRING);
                int courseExpectedStudents = Integer.parseInt(cell.getRichStringCellValue().getString());
                cell = row.getCell(7);
                cell.setCellType(CellType.STRING);
                int courseQuota = Integer.parseInt(cell.getRichStringCellValue().getString());
                cell = row.getCell(8);
                cell.setCellType(CellType.STRING);
                int courseClassesExpected = Integer.parseInt(cell.getRichStringCellValue().getString());
                courseParseToDB.put(courseCode,new CourseToDB(courseCode,courseName,courseYear,courseSemester,courseDuration,
                                                coursePoints,courseExpectedStudents,courseQuota,courseClassesExpected));
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int parseYearSemester(String stringToParse) {
        switch (stringToParse) {
            case "א":
                return 1;
            case "ב":
                return 2;
            case "ג":
                return 3;
            case "ד":
                return 4;
            case "ה":
                return 5;
            case "ו":
                return 6;
            case "ק":
                return 7;
            default:
                return 0;
        }
    }

    public HashMap<String, CourseToDB> getReport(){
        return courseParseToDB;
    }
}
