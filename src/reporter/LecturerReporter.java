package reporter;

import objects.Lesson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tomer on 5/27/2017.
 */
public class LecturerReporter {
    private String[] days = {"יום ראשון","יום שני","יום שלישי","יום רביעי","יום חמישי","יום שישי"};
    private FileOutputStream out = null;
    private String fileName;
    private Workbook workbook;
    private Sheet[] semesterSheets;
    private XLSStyles styles;
    private HashMap<Integer,Integer> dayMap;

    public LecturerReporter(String fileName, ArrayList<Lesson> courcesForTeacher) {
        this.fileName = fileName;
        styles = new XLSStyles();
        this.initScheduleReport();
        dayMap = new HashMap<>();
        initHashMaps();
        createReport(courcesForTeacher);
    }

    private void initHashMaps(){
        dayMap.put(1,5);
        dayMap.put(2,4);
        dayMap.put(3,3);
        dayMap.put(4,2);
        dayMap.put(5,1);
        dayMap.put(6,0);
    }

    public void createReport(ArrayList<Lesson> courcesForTeacher){
        CellStyle style = workbook.createCellStyle();
        CellStyle style1 = workbook.createCellStyle();
        CellStyle style2 = workbook.createCellStyle();
        CellStyle style3 = workbook.createCellStyle();
        style = styles.colunm1BackgroundColor(style);
        style1 = styles.colunm1BackgroundColor(style1);
        style2 = styles.colunm1BackgroundColor(style2);
        style3 = styles.colunm1BackgroundColor(style3);
        CellStyle bottomOpenBorder = styles.bottomOpenCellBorder(style);
        CellStyle bottomTopOpenBorder = styles.bottomTopOpenCellBorder(style1);
        CellStyle topOpenBorder = styles.topOpenCellBorder(style2);
        CellStyle boxBorder = styles.allBorderCellStyle(style3);
        try {
            out = new FileOutputStream(fileName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Row row;
        Cell cell;
        for (Lesson lesson: courcesForTeacher) {
            int semster = lesson.getCourse().getSemester();
            int day = lesson.getClassRoom().getDay();
            int duration = lesson.getCourse().getDuration();
            int startingHour = lesson.getClassRoom().getHour();
            int freeHour = getEmptyCellLocation(semster,day,duration,startingHour);
            String courseName = lesson.getCourse().getName();
            for (int j = 0; j < duration; j++) {
                row = semesterSheets[semster-1].getRow(startingHour - 7 + j);
                cell = row.getCell(freeHour);
                cell.setCellValue(courseName);
                cell.setCellStyle(style);
                semesterSheets[semster-1].autoSizeColumn(cell.getColumnIndex());
                if (j == 0 && duration == 1) {
                    cell.setCellStyle(boxBorder);
                } else if (j == 0 && duration > 1) {
                    cell.setCellStyle(bottomOpenBorder);
                } else if (j == (duration - 1)) {
                    cell.setCellStyle(topOpenBorder);
                } else {
                    cell.setCellStyle(bottomTopOpenBorder);
                }
            }
            try {
                workbook.write(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getEmptyCellLocation(int semster, int day, int duration, int startingHour){
        Row row;
        Cell cell;
        for (int i = 0; i < 1; i++) {
            int counter = 0;
            for (int j = 0; j < duration; j++) {
                row = semesterSheets[semster-1].getRow(startingHour-7+j);
                cell = row.getCell(dayMap.get(day)-i);
                System.out.println(cell.getStringCellValue());
                if(cell.getStringCellValue().equals("")){
                    counter++;
                }
            }
            if (counter == duration){
                return dayMap.get(day)-i;
            }
        }
        return -1;
    }

    private void initScheduleReport() {
        try {
            out = new FileOutputStream(fileName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        workbook = new HSSFWorkbook();
        semesterSheets = new Sheet[2];
        for (int i = 0; i < 2; i++) {
            semesterSheets[i] = workbook.createSheet();
            workbook.setSheetName(i, "Semster " + (i+1));
        }
        CellStyle style = workbook.createCellStyle();
        CellStyle margeStyle = workbook.createCellStyle();
        CellStyle leftBorder = workbook.createCellStyle();
        CellStyle leftBottomBorder = workbook.createCellStyle();
        style = styles.alignmentCellStyle(style);
        margeStyle = styles.allBorderCellStyle(margeStyle);
        leftBorder = styles.leftBorderCellStyle(leftBorder);
        leftBottomBorder = styles.leftBorderCellStyle(leftBottomBorder);
        leftBottomBorder = styles.bottomBorderCellStyle(leftBottomBorder);
        Row row;
        Cell cell;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 15; j++) {
                row = semesterSheets[i].getRow(j);
                if (row == null) {
                    row = semesterSheets[i].createRow(j);
                }
                for (int k = 0; k < 7; k++) {
                    cell = row.createCell(k);
                    cell.setCellType(CellType.STRING);
                    cell.setCellStyle(style);
                    if (k == 6) {
                        cell.setCellStyle(margeStyle);
                        if (j == 0) {
                            cell.setCellValue("שעה / יום");
                        } else {
                            cell.setCellValue((j + 7) + ":00");
                        }
                    }
                    else {
                        if (j == 14) {
                            cell.setCellStyle(leftBottomBorder);
                        } else {
                            cell.setCellStyle(leftBorder);
                        }
                    }
                }
            }
            int x = 5;
            for (int j = 0; j < 6; j++) {
                row = semesterSheets[i].getRow(0);
                cell = row.getCell(j);
                cell.setCellValue(days[x--]);
                cell.setCellStyle(margeStyle);
            }
        }
        try {
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LecturerReporter lr = new LecturerReporter("test1",null);
    }

}
