package com.pwz.myGenerator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.SQLException;

/**
 * Created by pwz on 2017/9/14.
 */
public class BuildHtml {
    private final String pojoName = "OrderTic";

    public static void main(String[] args) throws Exception {
        BuildHtml buildHtml = new BuildHtml();
        buildHtml.buildHtml(); //web html生成
        buildHtml.buildHtmlApp();//app html生成
        buildHtml.buildHtmlApp2();//app json生成
        buildHtml.buildHtmlApp3();//app 实体生成
        Runtime.getRuntime().exec("cmd /c start explorer D:\\" + buildHtml.getPathName(buildHtml.pojoName));
    }

    public String[] getData(int m) {
        String[] re = null;
        try {
            File excelFile = new File("e:\\"+pojoName+".xls"); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
                re = new String[rowCount];
                for (int r = 0; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
//                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                    int cellCount = 6; //获取总列数
                    for (int c = 0; c < cellCount; c++) {
                        Cell cell = row.getCell(c);
                        if (c == m) {
                            re[r] = cell == null ? "" : cell.getStringCellValue();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }



    private static void setCell(int i, String a, String b, String c, HSSFSheet sheet, HSSFCellStyle style) {
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow((int) i);
        cell = row.createCell((short) 0);
        cell.setCellValue(a);
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue(b);
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue(c);
        cell.setCellStyle(style);

    }


    public void buildHtml() throws ClassNotFoundException, SQLException, IOException {

        String[] fieldName = getData(0);
        String[] fieldId = getData(1);
        String[] fieldDict = getData(2);
        String[] fieldType = getData(3);


        String htmlPath = "d:/" + getPathName(pojoName) + "/html";
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(htmlPath, "webHtml.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));

        StringBuffer sb = new StringBuffer(); //选择变量
        StringBuffer sb2 = new StringBuffer(); //选择

        StringBuffer dateVar = new StringBuffer(); //时间变量

        StringBuffer date = new StringBuffer(); //时间
        sb.append("var ");
        dateVar.append("var ");
        for (int i = 0; i < fieldName.length; i++) {
            if (StringUtils.isNotEmpty(fieldDict[i])) {
                bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                        "                    <td>\n" +
                        "                        <dm:select tableName=\"" + fieldDict[i] + "\" id=\"" + fieldId[i] + "\" name=\"" + fieldId[i] + "\"\n" +
                        "                                   value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\" style=\"width:162px;\">\n" +
                        "                        </dm:select>\n" +
                        "                    </td>");
                bw.newLine();
                bw.newLine();
                sb.append(fieldId[i] + ",");
                sb2.append("" + fieldId[i] + " = new dhtmlXComboFromSelect(\"" + fieldId[i] + "\");");
                sb2.append("\n");
            } else {
                if ("a".equals(fieldType[i])) {
                    bw.newLine();
                    bw.write("       <tr>\n" +
                            "                    <td>" + fieldName[i] + "：</td>\n" +
                            "                    <td  colspan=\"7\">\n" +
                            "                        <input  id=\"" + fieldId[i] + "\"  name=\"" + fieldId[i] + "\" type=\"text\" style=\"width: 97%;\"\n" +
                            "                                value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\" class=\"form-control\">\n" +
                            "                    </td>\n" +
                            "        </tr>");

                } else {
                    bw.newLine();
                    bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                            "                    <td>\n" +
                            "                        <input  id=\"" + fieldId[i] + "\"  name=\"" + fieldId[i] + "\" type=\"text\"\n" +
                            "                                value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\" class=\"form-control\">\n" +
                            "                    </td>");
                    bw.newLine();
                    bw.newLine();
                }
                if ("b".equals(fieldType[i])) {
                    dateVar.append(fieldId[i] + ",");

                }
                if ("b".equals(fieldType[i])) {
                    date.append(" " + fieldId[i] + " = new dhtmlXCalendarObject(\"" + fieldId[i] + "\");");
                    date.append("\n");
                }
            }
        }
        bw.newLine();
        bw.newLine();
        bw.write(sb.toString().substring(0, sb.length() - 1) + ";");
        bw.newLine();
        bw.newLine();
        bw.write(dateVar.toString().substring(0, dateVar.length() - 1) + ";");
        bw.newLine();
        bw.newLine();
        bw.write(sb2.toString());
        bw.newLine();
        bw.newLine();
        bw.write(date.toString());
        bw.flush();
        bw.close();
    }

    public void buildHtmlApp() throws ClassNotFoundException, SQLException, IOException {

        String[] fieldName = getData(0);
        String[] fieldId = getData(1);
        String[] fieldDict = getData(2);
        String[] fieldType = getData(3);


        String htmlPath = "d:/" + getPathName(pojoName) + "/html";
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(htmlPath, "AppHtml.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));

        for (int i = 0; i < fieldName.length; i++) {
            if (StringUtils.isNotEmpty(fieldDict[i])) {
                bw.write(" <TextOptionCom title='" + fieldName[i] + "' ref='" + fieldId[i] + "Option' data={" + fieldId[i] + "Code} keyWord={this.state." + fieldId[i] + "}/>");
                bw.newLine();
                bw.newLine();
            } else {
                if ("a".equals(fieldType[i])) {
                    bw.write("<TextAreaCom title='" + fieldName[i] + "' ref='" + fieldId + "Area' value={this.state." + fieldId[i] + "}/>");
                    bw.newLine();
                    bw.newLine();
                } else if ("b".equals(fieldType[i])) {
                    bw.write("<TextDateCom title='" + fieldName[i] + "' ref='" + fieldId[i] + "Date' date={this.state." + fieldId[i] + "}/>");
                    bw.newLine();
                    bw.newLine();
                } else {
                    bw.write("<TextInputCom title='" + fieldName[i] + "' ref='" + fieldId[i] + "Text' value={this.state." + fieldId[i] + "}/>");
                    bw.newLine();
                    bw.newLine();
                }
            }

        }
        bw.flush();
        bw.close();
    }

    public void buildHtmlApp2() throws ClassNotFoundException, SQLException, IOException {

        String[] fieldName = getData(0);
        String[] fieldId = getData(1);
        String[] fieldDict = getData(2);
        String[] fieldType = getData(3);

        String htmlPath = "d:/" + getPathName(pojoName) + "/html";
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(htmlPath, "appJson.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));

        for (int i = 0; i < fieldName.length; i++) {
            bw.write("" + fieldId[i] + ":" + getPathName(pojoName) + "." + fieldId[i] + ",\n");
        }
        bw.flush();
        bw.close();
    }

    public void buildHtmlApp3() throws ClassNotFoundException, SQLException, IOException {

        String[] fieldName = getData(0);
        String[] fieldId = getData(1);
        String[] fieldDict = getData(2);
        String[] fieldType = getData(3);

        String htmlPath = "d:/" + getPathName(pojoName) + "/html";
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(htmlPath, "appPO.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));

        for (int i = 0; i < fieldName.length; i++) {

            if ("a".equals(fieldType[i])) {
                bw.write("            /**" + fieldName[i] + "**/\n" +
                        "            " + fieldId[i] + ":'',\n" +
                        "            // " + fieldName[i] + "Text的方法\n" +
                        "            onChange" + getPathNameUP(fieldId[i]) + ": Function,\n");
                bw.newLine();
            } else {
                bw.write("            /**" + fieldName[i] + "**/\n" +
                        "            " + fieldId[i] + ":'',");
                bw.newLine();
            }

        }
        bw.flush();
        bw.close();
    }

    private String getPathName(String s) {
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toLowerCase() + s2;
    }

    private String getPathNameUP(String s) {
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toUpperCase() + s2;
    }
}
