package parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import objects.DemandToDB;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class DemandReportParser implements ParserInterface {

    @Override
    //TODO change path to fileName
    public void startParse(String fileName) {
        try {
            HashMap<String, ArrayList<DemandToDB>> demandParseToDB = new HashMap<>();
            //Add load dialog
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Tomer\\Desktop\\3.xls"));
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
                String lecturerId = cell.getRichStringCellValue().getString();
                cell = row.getCell(1);
                int day = parseDay(cell.getRichStringCellValue().getString());
                cell = row.getCell(2);
                int startHour = parseHour(cell.getRichStringCellValue().getString());
                cell = row.getCell(3);
                int endHour = parseHour(cell.getRichStringCellValue().getString());
                cell = row.getCell(4);
                String reason;
                if(cell == null) {
                    reason = "";
                }else{
                    reason = cell.getRichStringCellValue().getString();
                }
                if (demandParseToDB.get(lecturerId) != null) {//the lecturer already exist
                    demandParseToDB.get(lecturerId).add(new DemandToDB(startHour, endHour, day, reason));
                } else {
                    demandParseToDB.put(lecturerId, new ArrayList<>());
                    demandParseToDB.get(lecturerId).add(new DemandToDB(startHour, endHour, day, reason));
                }
            }
            file.close();
            for (HashMap.Entry<String, ArrayList<DemandToDB>> entry : demandParseToDB.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int parseDay(String day) {
        switch (day) {
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
            default:
                return 0;
        }
    }

    private int parseHour(String hour) {
        String[] ans = hour.split(":");
        return Integer.parseInt(ans[0]);
    }
}
