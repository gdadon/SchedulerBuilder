package reporter;

import org.apache.poi.ss.usermodel.*;

public class XLSStyles {

    public CellStyle alignmentCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        return cell;
    }

    public CellStyle leftBorderCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setBorderLeft(BorderStyle.THICK);
        cell.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }

    public CellStyle bottomBorderCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setBorderBottom(BorderStyle.THICK);
        cell.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }

    public CellStyle rightBorderCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setBorderRight(BorderStyle.THICK);
        cell.setRightBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }

    public CellStyle topBorderCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setBorderTop(BorderStyle.THICK);
        cell.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return cell;
    }

    public CellStyle bottomOpenCellBorder(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell = this.leftBorderCellStyle(cell);
        cell = this.rightBorderCellStyle(cell);
        cell = this.topBorderCellStyle(cell);
        return  cell;
    }

    public CellStyle topOpenCellBorder(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell = this.leftBorderCellStyle(cell);
        cell = this.rightBorderCellStyle(cell);
        cell = this.bottomBorderCellStyle(cell);
        return  cell;
    }

    public CellStyle bottomTopOpenCellBorder(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell = this.leftBorderCellStyle(cell);
        cell = this.rightBorderCellStyle(cell);
        return  cell;
    }

    public CellStyle allBorderCellStyle(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
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

    public CellStyle colunm1BackgroundColor(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setFillForegroundColor(IndexedColors.YELLOW.index);
        cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cell;
    }
    public CellStyle colunm2BackgroundColor(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setFillForegroundColor(IndexedColors.RED.index);
        cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cell;
    }
    public CellStyle colunm3BackgroundColor(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cell;
    }
    public CellStyle colunm4BackgroundColor(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
        cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cell;
    }
    public CellStyle colunm5BackgroundColor(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setFillForegroundColor(IndexedColors.ROYAL_BLUE.index);
        cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cell;
    }
    public CellStyle colunm6BackgroundColor(CellStyle cell){
        cell.setAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cell;
    }
}
