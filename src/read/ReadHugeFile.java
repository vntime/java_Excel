package read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

public class ReadHugeFile {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        long start = System.currentTimeMillis();
        File f = new File("C:\\Users\\admin\\Desktop\\CaseNgoaiLe14012021.xlsx");
        if (!f.exists()) {
            System.err.println("File not exist!");
            return;
        }
        InputStream is = new FileInputStream(f);
        Workbook wb = StreamingReader.builder().sstCacheSize(100).open(is);
        Sheet sheet = wb.getSheetAt(0);
        long count = 0;
        for (Row row : sheet) {
            count++;
//            System.out.println(row.toString());
        }
        System.out.println("Read " + count + " rows in " + (System.currentTimeMillis() - start) + "ms");
    }
}
