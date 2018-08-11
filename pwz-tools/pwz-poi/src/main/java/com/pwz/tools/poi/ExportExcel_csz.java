package com.pwz.tools.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;


/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 *
 * @author csz
 * @version v1.0
 * @param <T>
 * 应用泛型，代表任意一个符合javabean风格的类
 * 注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 */
public class ExportExcel_csz<T> {

    //最基本的调用情况
    public void Export() {
        //声明一个工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = hwb.createSheet("设备故障");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short) 30);
        //生成一个样式
        HSSFCellStyle style = hwb.createCellStyle();
        //创建第一行（也可以成为表头）
        HSSFRow row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);

        cell.setCellValue("用户编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("余额");
        cell.setCellStyle(style);

        //添加数据
        /*list.add(new UserWallet(45,5.20f));
        list.add(new UserWallet(78,8.88f));
        list.add(new UserWallet(106,2.88f));*/


        row = sheet.createRow(1);
        row.createCell(0).setCellValue("324");
        row.createCell(1).setCellValue("234");


        sheet = hwb.createSheet("设备故障333");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short) 30);
        //生成一个样式
         style = hwb.createCellStyle();
        //创建第一行（也可以成为表头）
         row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //给表头第一行一次创建单元格
         cell = row.createCell((short) 0);

        cell.setCellValue("用户编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("余额");
        cell.setCellStyle(style);

        //添加数据
        /*list.add(new UserWallet(45,5.20f));
        list.add(new UserWallet(78,8.88f));
        list.add(new UserWallet(106,2.88f));*/


        row = sheet.createRow(1);
        row.createCell(0).setCellValue("324");
        row.createCell(1).setCellValue("234");



    }
}

