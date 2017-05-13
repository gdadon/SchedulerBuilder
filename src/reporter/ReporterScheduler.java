package reporter;

import java.io.*;
import java.util.HashMap;
import objects.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class ReporterScheduler {

    private String[] days = {"יום ראשון","יום שני","יום שלישי","יום רביעי","יום חמישי","יום שישי"};
    private FileOutputStream out = null;
    private String fileName;
    private Workbook workbook;
    private Sheet[] semesterSheets;
    XLSStyles styles;
    private HashMap<Pair,Integer> sheetMap;
    private HashMap<Integer,Integer> dayMap;

    public ReporterScheduler(String fileName) {
        System.out.println("hello");
        this.fileName = fileName;
        styles = new XLSStyles();
        this.initScheduleReport();
        sheetMap = new HashMap<Pair, Integer>();
        dayMap = new HashMap<Integer,Integer>();
        initHashMaps();
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

    public void createReport(/*Scheduler sc*/int year, int semster, int day, String courseName, int duration, int startingHour){
        try {
            out = new FileOutputStream("workbook7.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Row row;
        Cell cell;
        int freeHour = getEmptyCellLocation(year,semster,day,duration,startingHour);
        for (int j = 0; j < duration; j++) {
            row = semesterSheets[sheetMap.get(new Pair(year,semster))].getRow(startingHour-8+j);
            cell = row.getCell(freeHour);
            cell.setCellValue(courseName);
            if(j == 0  && duration == 1){
                //box style
            } else if(j == 0 && duration > 1){
                //box open down
            } else if(j == (duration - 1)){
                //box open up
            } else {
                //box open up and down
            }
        }
        try {
            workbook.write(out);
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private int getEmptyCellLocation(int year, int semster, int day, int duration, int startingHour){
        Row row;
        Cell cell;
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < duration; j++) {
                row = semesterSheets[sheetMap.get(new Pair(year,semster))].getRow(startingHour-8+j);
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
            out = new FileOutputStream("workbook7.xls");
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
        margeStyle = styles.alignmentCellStyle(margeStyle);
        margeStyle = styles.allBorderCellStyle(margeStyle);
        leftBorder = styles.leftBorderCellStyle(leftBorder);
        leftBorder = styles.alignmentCellStyle(leftBorder);
        bottomBorder = styles.bottomBorderCellStyle(bottomBorder);
        bottomBorder = styles.alignmentCellStyle(bottomBorder);
        leftBottomBorder = styles.alignmentCellStyle(leftBottomBorder);
        leftBottomBorder = styles.leftBorderCellStyle(leftBottomBorder);
        leftBottomBorder = styles.bottomBorderCellStyle(leftBottomBorder);
        Row row;
        Cell cell;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 14; j++) {
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
                            cell.setCellValue((j+8)+":00");
                        }
                    } else if (k % 6 == 0) {
                        if(j == 13){
                            cell.setCellStyle(leftBottomBorder);
                        } else {
                            cell.setCellStyle(leftBorder);
                        }
                    } else if (j == 13) {
                        cell.setCellStyle(bottomBorder);
                    }
                    else {
                        cell.setCellValue(j+","+k);
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

    public static void main(String[] args) {
        ReporterScheduler rs = new ReporterScheduler("test");
        rs.createReport(1,1,2,"Tomer",2,9);
    }
}
