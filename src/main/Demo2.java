package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class Demo2 {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);
        workbook.createSheet();
        SXSSFSheet sheet = (SXSSFSheet) workbook.getSheetAt(0);
        sheet.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk
        int rownum = 0;
        Row row = sheet.createRow(rownum);

        // EmpNo
        Cell cell = row.createCell(0);
        cell.setCellValue("EmpNo");
        // EmpName
        cell = row.createCell(1);
        cell.setCellValue("EmpNo");
        // Salary
        cell = row.createCell(2);
        cell.setCellValue("Salary");
        // Grade
        cell = row.createCell(3);
        cell.setCellValue("Grade");

        // Data
        for (int i = 0; i < 100000; i++) {
            System.out.println("--I = " + i);
            rownum++;
            row = sheet.createRow(rownum);
            System.out.println("Insert row " + (rownum + 1));

            // EmpNo (A)
            // I resolved the issue and I am embarrassed that I didn't spot the issue earlier.
            // If you look at the "writeRowToWorkbook" method, you'll see that it creates a
            // CellStyle for cells that hold a date. Clearly, adding a CellStyle to the
            // worksheet every time a row is written is not a great idea. Moving the
            // creation of the date CellStyle out into the workbook method resolved this
            // issue.
//            cell = row.createCell(0, CellType.STRING);
            cell = row.createCell(0);
            cell.setCellValue("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + (rownum + 1));
            // EmpName (B)
            cell = row.createCell(1);
            cell.setCellValue(
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" + (rownum + 1));
            // Salary (C)
            cell = row.createCell(2);
            cell.setCellValue(
                    "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC" + (rownum + 1));
            // Grade (D)
            cell = row.createCell(3);
            cell.setCellValue("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" + (rownum + 1));
            for (int j = 4; j <= 256; j++) {
                cell = row.createCell(j);
                cell.setCellValue("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ" + (rownum + 1));
            }
        }

        FileOutputStream out = new FileOutputStream("c:\\Users\\CNN\\Desktop\\employee2.xlsx");
        workbook.write(out);
        out.close();
        // dispose of temporary files backing this workbook on disk
        workbook.dispose();
        workbook.close();

        System.out.println("Created file: " + new File("c:\\Users\\CNN\\Desktop\\employee.xlsx").getAbsolutePath());

    }

    /**
     *
     * <PRE>
     * 。
     * </PRE>
     *
     * @return
     */
    private static double getMemory() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
    }

}
