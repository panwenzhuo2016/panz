package com.pwz.tools.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/11 12:19
 **/
public class Execl {

    private HSSFWorkbook hwb = new HSSFWorkbook();
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCellStyle style;
    private HSSFCell cell;
    private HSSFFont font;
    private boolean hasTitle;
    private boolean hasSubTitle;
    private boolean hasColumnWidth;
    private boolean hasSheet;
    private int columnNum;
    private static final int MULTIPLE = 20;


    public void createSheet(String sheetName) {
        //声明一个单子并命名
        sheet = hwb.createSheet(sheetName);
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short) 30);
        hasSheet = true;
        hasTitle = false;
        hasSubTitle = false;
    }

    public void setTitle(String titleName, int columnNum) {
        if (hasTitle) {
            return;
        }
        this.columnNum = columnNum;
        //创建第一行（也可以成为表头）
        row = sheet.createRow(0);
        row.setHeight((short) (30 * MULTIPLE));
        //给表头第一行一次创建单元格
        setCellValueByIndex(0, titleName);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNum));
        hasTitle = true;
    }

    public void setSubTitle(String subTitleName) {
        if (hasSubTitle) {
            return;
        }
        //创建第一行（也可以成为表头）
        row = sheet.createRow(1);
        row.setHeight((short) (25 * MULTIPLE));
        //给表头第一行一次创建单元格
        setCellValueByIndex(0, subTitleName);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columnNum));
        hasSubTitle = true;
    }

    private void setCellValueByIndex(int index, String cellvalue) {
        setCellValueByIndex(index,cellvalue,getDefaultStyle());
    }
    private void setCellValueByIndex(int index, String cellvalue,HSSFCellStyle style) {
        cell = row.createCell(index);
        cell.setCellValue(cellvalue);
        cell.setCellStyle(style);
    }

    public void write(String fileName) {
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            hwb.write(out);
            out.close();
            System.out.println("导出成功!");
        } catch (FileNotFoundException e) {
            System.out.println("导出失败！" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("导出失败！" + e.getMessage());
            e.printStackTrace();
        }
    }

    public HSSFCellStyle getDefaultStyle() {
        return getStyle(getDefaultFont());
    }
    public HSSFCellStyle getStyle(HSSFFont font) {
        //生成一个样式
        style = hwb.createCellStyle();
        //样式字体居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        return style;
    }



    public HSSFFont getFont(int fontSize){
        font = hwb.createFont();
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }
    public HSSFFont getDefaultFont(){
        return getFont(15);
    }


    public void setColumnName(String... values) {
        int length = values.length;
        int index = 0;
        if(hasTitle){
            index ++;
        }
        if(hasSubTitle){
            index ++;
        }
        int i = 0;
        row = sheet.createRow(index);
        for (String value : values) {
            setCellValueByIndex(i,value);
            i++;
        }
        hasColumnWidth = true;
    }
    public void setColumn(int index,String... values) {
        int i = 0;
        row = sheet.createRow(index);
        for (String value : values) {
            setCellValueByIndex(i,value);
            i++;
        }
        hasColumnWidth = true;
    }
}
