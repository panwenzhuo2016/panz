package com.pwz;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

/**
 * Created by pwz on 2017/8/7.
 */
public class Execl {

    public static void main(String[] args) {
        Execl e = new Execl();
        e.readExcel();
    }

    public String readExcel() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            File excelFile = new File("e:\\1.xlsx"); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
            String sql = "CREATE TABLE `T_DD_GZP_COMMON` (\n" +
                    "`obj_id`  char(32) NOT NULL COMMENT '主键' ,";
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数

                for (int r = 0; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                    for (int c = 0; c < cellCount; c++) {
                        Cell cell = row.getCell(c);
                        int cellType = cell.getCellType();
                        String cellValue = null;
                        switch(cellType) {
                            case Cell.CELL_TYPE_STRING: //文本
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC: //数字、日期
                                if(DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = fmt.format(cell.getDateCellValue()); //日期型
                                }
                                else {
                                    cellValue = String.valueOf(cell.getNumericCellValue()); //数字
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN: //布尔型
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_BLANK: //空白
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_ERROR: //错误
                                cellValue = "错误";
                                break;
                            case Cell.CELL_TYPE_FORMULA: //公式
                                cellValue = "错误";
                                break;
                            default:
                                cellValue = "";
                        }
                            if(c == 1){
                                sql =sql + "`"+ cellValue.toLowerCase() + "`  char(32) NULL COMMENT '" ;
                            }
                            if (c == 2){
                                sql = sql + cellValue +  "' ,";
                            }
                    }
                }
                sql = sql + "PRIMARY KEY (`obj_id`)\n" +
                        ")\n" +
                        ";";
                System.out.println(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
