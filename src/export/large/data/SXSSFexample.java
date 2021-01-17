/**
 * 
 */
package export.large.data;

import java.io.FileOutputStream;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import entity.Employee;

public class SXSSFexample {

    public static void main(String[] args) throws Throwable {
        int maxrow = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        Employee e1 = new Employee();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);

        workbook.createSheet();
        SXSSFSheet sheet = (SXSSFSheet) workbook.getSheetAt(0);
        sheet.setRandomAccessWindowSize(5000);// keep 100 rows in memory, exceeding rows will be flushed to disk
        for (int rownum = 0; rownum < 1048575; rownum++) {
            System.out.println("Row num = " + rownum);
            Row row = sheet.createRow(rownum);
            for (int cellnum = 0; cellnum < 1; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\mytemplate.xlsx");
        workbook.write(out);
        out.close();
        workbook.close();
    }

}