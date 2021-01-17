package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportXLSX_2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("sheet1");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
        row.createCell(1).setCellValue("1");
        row.createCell(2).setCellValue("2");
        row.createCell(3).setCellValue("3");

        row = sheet.createRow(1);
        row.createCell(1).setCellValue("1");
        // row.createCell(2).setCellValue("2");
        row.createCell(3).setCellValue("3");
        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("c:\\Users\\Admin\\Desktop\\workbook_20210117.xlsx")) {
            wb.write(fileOut);
        }
    }
}
