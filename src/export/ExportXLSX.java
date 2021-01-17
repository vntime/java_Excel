package export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportXLSX {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("sheet1");
        CreationHelper createHelper = wb.getCreationHelper();

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);
        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);

        // XSSF Example
        XSSFRichTextString rt = new XSSFRichTextString("The quick brown fox");
        XSSFFont font1 = wb.createFont();
        font1.setBold(true);
        font1.setColor(new XSSFColor(new java.awt.Color(255, 0, 0)));
        rt.applyFont(0, 10, font1);
        XSSFFont font2 = wb.createFont();
        font2.setItalic(true);
        font2.setUnderline(XSSFFont.U_DOUBLE);
        font2.setColor(new XSSFColor(new java.awt.Color(0, 255, 0)));
        rt.applyFont(10, 19, font2);
        XSSFFont font3 = wb.createFont();
        font3.setColor(new XSSFColor(new java.awt.Color(0, 0, 255)));
        rt.append(" Jumped over the lazy dog", font3);

        // row.createCell(2).setCellValue(createHelper.createRichTextString("This is a
        // string"));
        row.createCell(2).setCellValue(rt);

        row.createCell(3).setCellValue(true);
        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("c:\\Users\\Admin\\Desktop\\test\\workbook.xlsx")) {
            wb.write(fileOut);
        }
    }
}
