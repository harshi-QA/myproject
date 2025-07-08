package Utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {

            try {
                FileInputStream fis = new FileInputStream(new File(filePath));
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheet(sheetName);
                Row r = sheet.getRow(rowNum);

                if (r == null) return "";
                Cell c = r.getCell(colNum);
                if (c == null) return "";
                workbook.close();
                return c.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
}
