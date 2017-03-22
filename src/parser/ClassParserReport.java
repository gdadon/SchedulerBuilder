package parser;

import objects.ClassRoom;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClassParserReport implements ParserInterface {

    HashMap<Integer, ClassRoom> classRoomReport = null;

    @Override
    public void startParse(String fileName) {
        try {
            classRoomReport = new HashMap<>();
            int day = 1;
            int id = 1;
            //Add load dialog
            FileInputStream file = new FileInputStream(new File(fileName));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0); //Sheet in index 0
            Row row = null; //Row
            Cell cell = null; //Column
            //Starting parse from C2 -> System.out.println(cell.getAddress());
            //Parsing from C2 -> O13
            for (int i = 1; i < 13; i++) {
                row = sheet.getRow(i);
                day = (int) Math.ceil(i / 2.0);
                for (int j = 2; j < 15; j++) {
                    cell = row.getCell(j);
                    String notAllowed = cell.getRichStringCellValue().getString();
                    if (i % 2 == 1 && !notAllowed.equals("X")) {
                        classRoomReport.put(id++, new ClassRoom.ClassRoomBuilder().setDay(day).setSize('S').setHour(j+6).build());
                    } else if (i % 2 == 0 && !notAllowed.equals("X")) {
                        classRoomReport.put(id++, new ClassRoom.ClassRoomBuilder().setDay(day).setSize('B').setHour(j+6).build());
                    }
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap getReport() {
        return classRoomReport;
    }
}
