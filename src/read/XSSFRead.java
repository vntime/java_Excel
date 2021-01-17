package read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFRead {
    public static void main(String[] args) throws IOException {
        String excelFilePath = "C:\\Users\\admin\\Desktop\\CaseNgoaiLe14012021.xlsx";

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                // continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();// @@@@@@@@@@@@dung kieu nay, gap cell null thi bo qua
                                                                 // => thieu cell

            // Read cells and set value for book object
            for (int i = 0; i < nextRow.getLastCellNum(); i++) {
                // Read cell
                Cell cell = nextRow.getCell(i);
                Object cellValue = getCellValue(cell);
                System.out.print(cellValue.toString() + "\t");
            }
            System.out.println();
        }

        workbook.close();
        inputStream.close();

    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
            cellValue = "";
            break;
        case BLANK:
            cellValue = "";
            break;
        case ERROR:
            cellValue = "";
            break;
        default:
            cellValue = "";
            break;
        }

        return cellValue;
    }
}
