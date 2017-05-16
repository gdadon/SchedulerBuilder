package reporter;

import java.io.*;
import java.util.HashMap;

import objects.Lesson;
import objects.Pair;
import objects.Scheduler;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class ReporterScheduler {

    private String[] days = {"יום ראשון","יום שני","יום שלישי","יום רביעי","יום חמישי","יום שישי"};
    private FileOutputStream out = null;
    private String fileName;
    private Workbook workbook;
    private Sheet[] semesterSheets;
    private XLSStyles styles;
    private HashMap<Pair,Integer> sheetMap;
    private HashMap<Integer,Integer> dayMap;

    public ReporterScheduler(String fileName, Scheduler scheduler) {
        this.fileName = fileName;
        styles = new XLSStyles();
        this.initScheduleReport();
        sheetMap = new HashMap<>();
        dayMap = new HashMap<>();
        initHashMaps();
        createReport(scheduler);
    }

    private void initHashMaps(){
        sheetMap.put((new Pair(1,1)),0);
        sheetMap.put((new Pair(1,2)),1);
        sheetMap.put((new Pair(2,1)),2);
        sheetMap.put((new Pair(2,2)),3);
        sheetMap.put((new Pair(3,1)),4);
        sheetMap.put((new Pair(3,2)),5);
        dayMap.put(1,35);
        dayMap.put(2,29);
        dayMap.put(3,23);
        dayMap.put(4,17);
        dayMap.put(5,11);
        dayMap.put(6,5);
    }

    private void createReport(Scheduler scheduler){

        for (Lesson lesson: scheduler.getLessons()) {
            int year = lesson.getCourse().getYear();
            int semster = lesson.getCourse().getSemester();
            int day = lesson.getClassRoom().getDay();
            int duration = lesson.getCourse().getDuration();
            int startingHour = lesson.getClassRoom().getHour();
            String courseName = lesson.getCourse().getName();
            String teacherName = lesson.getTeacher().getName();
            try {
                out = new FileOutputStream(fileName + ".xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Row row;
            Cell cell;
            int freeHour = getEmptyCellLocation(year, semster, day, duration, startingHour);
            for (int j = 0; j < duration; j++) {
                row = semesterSheets[sheetMap.get(new Pair(year, semster))].getRow(startingHour - 7 + j);
                cell = row.getCell(freeHour);
                cell.setCellValue(courseName+" "+teacherName);
                CellStyle style = workbook.createCellStyle();
                switch (freeHour % 6) {
                    case 0:
                        style = styles.colunm6BackgroundColor(style);
                        break;
                    case 1:
                        style = styles.colunm5BackgroundColor(style);
                        break;
                    case 2:
                        style = styles.colunm4BackgroundColor(style);
                        break;
                    case 3:
                        style = styles.colunm3BackgroundColor(style);
                        break;
                    case 4:
                        style = styles.colunm2BackgroundColor(style);
                        break;
                    case 5:
                        style = styles.colunm1BackgroundColor(style);
                        break;
                }
                if (j == 0 && duration == 1) {
                    style = styles.allBorderCellStyle(style);
                    cell.setCellStyle(style);
                } else if (j == 0 && duration > 1) {
                    style = styles.bottomOpenCellBorder(style);
                    cell.setCellStyle(style);
                } else if (j == (duration - 1)) {
                    style = styles.topOpenCellBorder(style);
                    cell.setCellStyle(style);
                } else {
                    style = styles.bottomTopOpenCellBorder(style);
                    cell.setCellStyle(style);
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

    private int getEmptyCellLocation(int year, int semster, int day, int duration, int startingHour){
        Row row;
        Cell cell;
        for (int i = 0; i < 6; i++) {
            int counter = 0;
            for (int j = 0; j < duration; j++) {
                row = semesterSheets[sheetMap.get(new Pair(year,semster))].getRow(startingHour-7+j);
                cell = row.getCell(dayMap.get(day)-i);
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

    private void initScheduleReport(){
        try {
            out = new FileOutputStream(fileName+".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        workbook = new HSSFWorkbook();
        semesterSheets = new Sheet[6];
        for(int i = 0 ; i < 6 ; i++){
            semesterSheets[i] = workbook.createSheet();
            workbook.setSheetName(i, "Year " + ((i/2)+1) + " semester " + ((i%2)+1));
        }
        CellStyle style = workbook.createCellStyle();
        CellStyle margeStyle = workbook.createCellStyle();
        CellStyle leftBorder = workbook.createCellStyle();
        CellStyle bottomBorder = workbook.createCellStyle();
        CellStyle leftBottomBorder = workbook.createCellStyle();
        style = styles.alignmentCellStyle(style);
        margeStyle = styles.allBorderCellStyle(margeStyle);
        leftBorder = styles.leftBorderCellStyle(leftBorder);
        bottomBorder = styles.bottomBorderCellStyle(bottomBorder);
        leftBottomBorder = styles.leftBorderCellStyle(leftBottomBorder);
        leftBottomBorder = styles.bottomBorderCellStyle(leftBottomBorder);
        Row row;
        Cell cell;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 15; j++) {
                row = semesterSheets[i].getRow(j);
                if(row == null) {
                    row = semesterSheets[i].createRow(j);
                }
                for (int k = 0; k < 37; k++) {
                    cell = row.createCell(k);
                    cell.setCellType(CellType.STRING);
                    cell.setCellStyle(style);
                    if (k == 36){
                        cell.setCellStyle(margeStyle);
                        if(j == 0) {
                            cell.setCellValue("שעה / יום");
                        } else {
                            cell.setCellValue((j+7)+":00");
                        }
                    } else if (k % 6 == 0) {
                        if(j == 14){
                            cell.setCellStyle(leftBottomBorder);
                        } else {
                            cell.setCellStyle(leftBorder);
                        }
                    } else if (j == 14) {
                        cell.setCellStyle(bottomBorder);
                    }
                }
            }
            int x = 5;
            for (int j = 0; j < 36 ; j+=6) {
                semesterSheets[i].addMergedRegion(new CellRangeAddress(0, 0, j, j+5));
                row = semesterSheets[i].getRow(0);
                cell = row.getCell(j);
                cell.setCellValue(days[x--]);
                for (int k = j; k < j+6; k++) {
                    cell = row.getCell(k);
                    cell.setCellStyle(margeStyle);
                }
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
