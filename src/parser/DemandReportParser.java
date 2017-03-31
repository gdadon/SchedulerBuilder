package parser;

import objects.Demand;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DemandReportParser implements ParserInterface {

    HashMap<String, ArrayList<Demand>> demandParseReport = null;

    @Override
    public void startParse(String fileName) {
        try {
            demandParseReport = new HashMap<>();
            //Add load dialog
            FileInputStream file = new FileInputStream(new File(fileName));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0); //Sheet in index 0
            Row row = null; //Row
            Cell cell = null; //Column
            int lastRowNum = sheet.getLastRowNum();
            //Parsing from A2 -> lastRowNum
            for (int i = 1; i < lastRowNum; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(0);
                cell.setCellType(CellType.STRING);
                String lecturerId = cell.getRichStringCellValue().getString();
                if(lecturerId == ""){
                    break;
                }
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
                if (demandParseReport.get(lecturerId) != null) {//the lecturer already exist
                    demandParseReport.get(lecturerId).add(new Demand.DemandBuilder().setStart(startHour).setEnd(endHour).setDay(day).setReason(reason).build());
                } else {
                    demandParseReport.put(lecturerId, new ArrayList<>());
                    demandParseReport.get(lecturerId).add(new Demand.DemandBuilder().setStart(startHour).setEnd(endHour).setDay(day).setReason(reason).build());
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap getReport() {
        return demandParseReport;
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
