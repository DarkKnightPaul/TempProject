package top.huhuiyu.p101.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {

  public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static List<TestInfo> makedata(int amount) {
    List<TestInfo> list = new ArrayList<TestInfo>();
    Random random = new Random();
    for (int i = 0; i < amount; i++) {
      TestInfo info = new TestInfo();
      info.setTid(random.nextInt());
      info.setDecTest(new BigDecimal(random.nextDouble() * 10000));
      info.setStrTest("字符" + random.nextInt());
      info.setDateTest(new Date());
      list.add(info);
    }
    return list;
  }

  public static void save(List<TestInfo> list, String filename) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet spreadsheet = workbook.createSheet("输出数据");
    XSSFRow row;
    int rownum = 0;
    // 写标题=============================================================
    row = spreadsheet.createRow(rownum);
    Cell cell = row.createCell(0);
    cell.setCellType(CellType.STRING);
    cell.setCellValue("编号");
    cell = row.createCell(1);
    cell.setCellType(CellType.STRING);
    cell.setCellValue("金额");
    cell = row.createCell(2);
    cell.setCellType(CellType.STRING);
    cell.setCellValue("描述");
    cell = row.createCell(3);
    cell.setCellType(CellType.STRING);
    cell.setCellValue("日期");

    for (TestInfo info : list) {
      row = spreadsheet.createRow(rownum++);
      cell = row.createCell(0);
      cell.setCellType(CellType.NUMERIC);
      cell.setCellValue(info.getTid());
      cell = row.createCell(1);
      cell.setCellType(CellType.STRING);
      cell.setCellValue(info.getStrTest());
      cell = row.createCell(2);
      cell.setCellType(CellType.STRING);
      cell.setCellValue(info.getDecTest().toString());
      cell = row.createCell(3);
      cell.setCellType(CellType.STRING);
      cell.setCellValue(FORMAT.format(info.getDateTest()));
    }

    // 输出到文件
    FileOutputStream out = new FileOutputStream(new File(filename));
    workbook.write(out);
    out.close();
    workbook.close();
  }

  public static List<TestInfo> read(String filename) throws Exception {
    List<TestInfo> list = new ArrayList<TestInfo>();
    FileInputStream fis = new FileInputStream(new File(filename));
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet spreadsheet = workbook.getSheetAt(0);
    Iterator<Row> rowIterator = spreadsheet.iterator();
    rowIterator.next(); // 跳过第一行标题
    XSSFRow row;
    while (rowIterator.hasNext()) {
      row = (XSSFRow) rowIterator.next();
      // 读取行数据
      TestInfo info = new TestInfo();
      Cell cell = row.getCell(0);
      info.setTid((int) cell.getNumericCellValue());
      cell = row.getCell(1);
      info.setStrTest(cell.getStringCellValue());
      cell = row.getCell(2);
      info.setDecTest(new BigDecimal(cell.getStringCellValue()));
      cell = row.getCell(3);
      info.setDateTest(FORMAT.parse(cell.getStringCellValue()));
      list.add(info);
    }
    workbook.close();
    return list;
  }

  public static void main(String[] args) throws Exception {
    String filename = "data.xlsx";
    // save(makedata(10), filename);
    // System.out.println("保存成功");
    List<TestInfo> list = read(filename);
    for (TestInfo testInfo : list) {
      System.out.println(testInfo);
    }

  }

}
