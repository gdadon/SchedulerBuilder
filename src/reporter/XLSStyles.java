package reporter;

import org.apache.poi.ss.usermodel.*;

public class XLSStyles {
    public CellStyle allBorderCellStyle(CellStyle cell){
        cell.setBorderBottom(BorderStyle.THICK);
        cell.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cell.setBorderLeft(BorderStyle.THICK);
        cell.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cell.setBorderRight(BorderStyle.THICK);
        cell.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cell.setBorderTop(BorderStyle.THICK);
        cell.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }

    public CellStyle alignmentCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        return cell;
    }

    public CellStyle leftBorderCellStyle(CellStyle cell){
        cell.setBorderLeft(BorderStyle.THICK);
        cell.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }

    public CellStyle bottomBorderCellStyle(CellStyle cell){
        cell.setBorderBottom(BorderStyle.THICK);
        cell.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }
}
