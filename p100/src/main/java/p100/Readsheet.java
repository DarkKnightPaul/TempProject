package p100;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readsheet {

  public static void testread(String filename) throws Exception {
    int total = 0;
    XSSFWorkbook sworkbook = new XSSFWorkbook();

    XSSFSheet sspreadsheet = sworkbook.createSheet("合并结果");
    XSSFRow srow;

    XSSFRow row;
    FileInputStream fis = new FileInputStream(new File(filename));
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet spreadsheet = workbook.getSheetAt(0);
    Iterator<Row> rowIterator = spreadsheet.iterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      row = (XSSFRow) rowIterator.next();
      srow = sspreadsheet.createRow(total++);
      Iterator<Cell> cellIterator = row.cellIterator();
      int count = 0;
      while (cellIterator.hasNext()) {
        if (count == 4) {
          break;
        }
        Cell cell = cellIterator.next();
        cell.setCellType(CellType.STRING);
        String sv = cell.getStringCellValue();
        if (sv == null || sv.trim().equals("")) {
          break;
        }
        Cell scell = srow.createCell(count++);
        scell.setCellValue(cell.getStringCellValue());
      }
    }
    spreadsheet = workbook.getSheetAt(1);
    rowIterator = spreadsheet.iterator();
    rowIterator.next();
    while (rowIterator.hasNext()) {
      row = (XSSFRow) rowIterator.next();
      srow = sspreadsheet.createRow(total++);
      Iterator<Cell> cellIterator = row.cellIterator();
      int count = 0;
      while (cellIterator.hasNext()) {
        if (count == 4) {
          break;
        }
        Cell cell = cellIterator.next();
        cell.setCellType(CellType.STRING);
        String sv = cell.getStringCellValue();
        if (sv == null || sv.trim().equals("")) {
          break;
        }
        Cell scell = srow.createCell(count++);
        scell.setCellValue(cell.getStringCellValue());
      }
    }
    fis.close();
    workbook.close();
    System.out.println(total);

    FileOutputStream out = new FileOutputStream(new File("Writesheet.xlsx"));
    sworkbook.write(out);
    out.close();
    sworkbook.close();

  }

  public static void main(String[] args) throws Exception {
    String filename = args.length == 1 ? args[0] : "files/file.xlsx";
    // testread(filename, 0);
    testread(filename);
    // save();

  }
}
