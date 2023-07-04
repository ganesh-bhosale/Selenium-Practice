package SeleniumDemo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelReader {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;

    String path;

    ExcelReader(String path){
        this.path = path;
    }

    // Method to count Rows in Excel sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        int index = workbook.getSheetIndex(sheetName);
        if(index == -1){
            return 0;
        }
        else{
            sheet = workbook.getSheetAt(index);
            int num = sheet.getLastRowNum()+1;
            return num;
        }
    }

    // Method return Cell count in a sheet
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    // Method to get the cell data from a excel sheet

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            data = formatter.formatCellValue(cell); // Returns the formatted value of cell as a String
        }
        catch (Exception e){
            data="";
        }
        workbook.close();
        fi.close();
        return data;

    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlFile = new File(path);
        if(!xlFile.exists()){
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1){
            workbook.createSheet(sheetName);
        }

        sheet = workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null){
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillRedColor(String sheetName, int rownun, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownun);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public String [][] getDataFromSheet(String workbookPath, String sheetName ) throws IOException {
        workbook = new XSSFWorkbook(System.getProperty("user.dir")+ "/"+workbookPath);
        sheet = workbook.getSheet(sheetName);

        int noOfRows = sheet.getLastRowNum() + 1;
        int noOfCols = sheet.getRow(0).getLastCellNum();

        String [][] dataTable = new String[noOfRows][noOfCols];

        for(int i = sheet.getFirstRowNum(); i<sheet.getLastRowNum()+1; i++){
            Row row = sheet.getRow(i);
            for(int j = row.getFirstCellNum(); j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                dataTable[i][j] = cell.getStringCellValue();
            }
        }
        workbook.close();
        return dataTable;
    }

}
