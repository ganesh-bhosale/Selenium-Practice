package SeleniumDemo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import static java.sql.Types.NUMERIC;

public class ExcelReadFile {
    public static void main(String [] args) throws IOException {

        // Find the file and open it
        // Read the Rows and Columns

        FileInputStream fileInputStream = new FileInputStream("TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                switch (cell.getCellType()){
                    case STRING:
                        System.out.println(cell.getStringCellValue());
                        break;

                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;

                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;

                    case BLANK:
                        System.out.println("Blank Value");
                }
            }
        }

    }
}
