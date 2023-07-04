package SeleniumDemo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelFileWrite {
    public static void main(String [] args){

        // Create a file and write a data

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Login Data");

        Map<String, Object[]> data =  new TreeMap<>();
        data.put("1", new Object[]{"LoginId", "Username", "Password"});
        data.put("2", new Object[]{"1", "automation123@vwo.com", "Wingify@123"});
        data.put("3", new Object[]{"2", "apache987@vwo.com", "Wingify@123"});

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for(String key : keyset){
            Row r = sheet.createRow(rownum++);
            Object[] objectArray = data.get(key);
            int cellNum = 0;
            for(Object o : objectArray){
                Cell cell = r.createCell(cellNum++);
                if( o instanceof String){
                    cell.setCellValue((String) o);
                }
                else{
                    cell.setCellValue((Integer) o);
                }
            }
        }
        try{
            FileOutputStream outputStream = new FileOutputStream("TestData.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to write in a File");
    }
 }
}
