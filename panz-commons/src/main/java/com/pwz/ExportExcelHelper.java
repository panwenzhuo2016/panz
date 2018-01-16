///**
// * Copyright (c) 2014-2015 Fujian ETong Century Software Co.,
// * Ltd. All Rights Reserved.
// * This software is the confidential and proprietary information of
// * Fujian ETong Century Software Co., Ltd.
// * ("Confidential Information"). You shall not disclose such
// * Confidential Information and shall use it only in accordance with
// * the terms of the license agreement you entered into with ETong.
// * ETong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
// * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
// * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
// * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. ETong SHALL NOT BE LIABLE FOR
// * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
// * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
// */
//package com.pwz;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.xmlbeans.impl.validator.ValidatorUtil;
//
///**********************************************************
// * Copyright ? 2014，福建亿同世纪软件有限公司 All Rights Reserved. 文件名称：
// * ExportExcelHelper.java 摘 要： [不需要模板，支持个性化表头] 初始版本：1.0.0 原 作 者：guozx 完成日期：
// * 2014年8月25日 上午10:25:09 当前版本： 1.0.0 作 者： guozx 完成日期： 2014年8月25日 上午10:25:09
// *****************************************************************/
//public class ExportExcelHelper {
//
//    /**
//     *
//     */
//    private static final int       DEFAULT_COL_WIDTH           = 3000;
//
//    /**
//     *
//     */
//    private static final short     DEFAULT_TITLE_FONT_SIZE     = 16;
//
//    /**
//     *
//     */
//    private static final short     DEFAULT_SUB_TITLE_FONT_SIZE = 12;
//
//    /**
//     *
//     */
//    private static final short     DEFAULT_DATA_FONT_SIZE      = 10;
//
//    /**
//     *
//     */
//    private static final short     DEFAULT_HEAD_FONT_SIZE      = 10;
//
//    /**
//     * 工作薄对象.
//     */
//    private HSSFWorkbook           wb;
//
//    /**
//     * 工作表对象.
//     */
//    private Sheet                  sheet;
//
//    /**
//     * 样式列表.
//     */
//    private Map<String, CellStyle> styles                      = new HashMap<String, CellStyle>();
//
//    /**
//     * 当前行号.
//     */
//    private int                    rownum;
//
//    /**
//     *
//     */
//    private ExportExcelConfig      eeb;
//
//    /**
//     * 构造函数.
//     *
//     * @param exportExcelBean
//     *            exportExcelBean
//     */
//    public ExportExcelHelper(ExportExcelConfig exportExcelBean) {
//        initialize(exportExcelBean);
//    }
//
//    /**
//     * .
//     */
//    public ExportExcelHelper() {
//        this.wb = new HSSFWorkbook();
//        this.sheet = wb.createSheet("Export");
//    }
//
//    /**
//     * 初始化函数.
//     *
//     * @param exportExcelBean
//     *            exportExcelBean
//     */
//    private void initialize(ExportExcelConfig exportExcelBean) {
//
//        this.wb = new HSSFWorkbook();
//        this.sheet = wb.createSheet("Export");
//        this.styles = createStyles(exportExcelBean);
//        this.eeb = exportExcelBean;
//        if (eeb == null) {
//            throw new RuntimeException("exportExcelBean not null!");
//        }
//        if (ValidatorUtil.isEmpty(eeb.getHeaders())
//            && ValidatorUtil.isEmpty(eeb.getMultipHeaders())) {
//            throw new RuntimeException("headerList not null!");
//        }
//
//        int headerSize = 0;
//        if (ValidatorUtil.isNotEmpty(eeb.getHeaders())) {
//            headerSize = eeb.getHeaders().size();
//        } else {
//            headerSize = eeb.getMultipHeaderColSize();
//        }
//
//        // Create title
//        if (ValidatorUtil.isNotEmpty(eeb.getTitle())) {
//            Row titleRow = sheet.createRow(rownum++);
//            titleRow.setHeightInPoints(eeb.getTitleRowHeight());
//            Cell titleCell = titleRow.createCell(0);
//            titleCell.setCellStyle(styles.get("title"));
//            titleCell.setCellType(Cell.CELL_TYPE_STRING);
//            titleCell.setCellValue(eeb.getTitle());
//            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
//                titleRow.getRowNum(), titleRow.getRowNum(), headerSize - 1));
//        }
//        // Create subtitle
//        if (ValidatorUtil.isNotEmpty(eeb.getSubtitle())) {
//            Row subtitleRow = sheet.createRow(rownum++);
//            subtitleRow.setHeightInPoints(eeb.getSubtitleRowHeight());
//            Cell subtitleCell = subtitleRow.createCell(0);
//            subtitleCell.setCellStyle(styles.get("subtitle"));
//            subtitleCell.setCellValue(eeb.getSubtitle());
//            sheet.addMergedRegion(new CellRangeAddress(subtitleRow.getRowNum(),
//                subtitleRow.getRowNum(), subtitleRow.getRowNum() - 1,
//                headerSize - 1));
//        }
//        if (ValidatorUtil.isNotEmpty(eeb.getHeaders())) {
//            Row headerRow = sheet.createRow(rownum++);
//            headerRow.setHeightInPoints(eeb.getHeaderRowHeight());
//            for (int i = 0; i < eeb.getHeaders().size(); i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellStyle(styles.get("header"));
//                cell.setCellValue(eeb.getHeaders().get(i));
//                sheet.autoSizeColumn(i);
//            }
//            for (int i = 0; i < eeb.getHeaders().size(); i++) {
//                int colWidth = sheet.getColumnWidth(i) * 2;
//                sheet.setColumnWidth(i,
//                    colWidth < DEFAULT_COL_WIDTH ? DEFAULT_COL_WIDTH
//                        : colWidth);
//            }
//        } else {
//            for (List<String> headers : eeb.getMultipHeaders()) {
//                Row headerRow = sheet.createRow(rownum++);
//                // headerRow.setRowStyle(CellStyle.ALIGN_LEFT);
//                headerRow.setHeightInPoints(eeb.getHeaderRowHeight());
//                for (int i = 0; i < headers.size(); i++) {
//                    Cell cell = headerRow.createCell(i);
//                    cell.setCellStyle(styles.get("header"));
//                    cell.setCellValue(headers.get(i));
//                    sheet.autoSizeColumn(i);
//                }
//                for (int i = 0; i < headers.size(); i++) {
//                    int colWidth = sheet.getColumnWidth(i) * 2;
//                    sheet.setColumnWidth(i,
//                        colWidth < DEFAULT_COL_WIDTH ? DEFAULT_COL_WIDTH
//                            : colWidth);
//                }
//            }
//        }
//        if (ValidatorUtil.isNotEmpty(eeb.getDatas())) {
//            for (List<String> data : eeb.getDatas()) {
//                int colunm = 0;
//                Row row = this.addRow();
//                row.setHeightInPoints(eeb.getDataRowHeight());
//                for (String val : data) {
//                    this.addCell(row, colunm++, val);
//                }
//            }
//        }
//    }
//
//    /**
//     * 创建表格样式.
//     *
//     * @param exportExcelBean
//     *            exportExcelBean
//     * @return 样式列表
//     */
//    private Map<String, CellStyle> createStyles(
//        ExportExcelConfig exportExcelBean) {
//
//        CellStyle style = wb.createCellStyle();
//        style.setAlignment((short) 2);
//        style.setVerticalAlignment((short) 1);
//        style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        Font titleFont = wb.createFont();
//        titleFont.setFontName("Arial");
//        titleFont.setFontHeightInPoints((short) DEFAULT_TITLE_FONT_SIZE);
//        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        style.setFont(titleFont);
//
//        styles.put("title", style);
//
//        CellStyle subtitleStyle = wb.createCellStyle();
//        subtitleStyle.cloneStyleFrom(styles.get("title"));
//        subtitleStyle.setAlignment(exportExcelBean.getSubtitleAlign());
//        Font subtitleFont = wb.createFont();
//        subtitleFont.setFontName("Arial");
//        subtitleFont.setFontHeightInPoints((short) DEFAULT_SUB_TITLE_FONT_SIZE);
//        subtitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        subtitleStyle.setFont(subtitleFont);
//        styles.put("subtitle", subtitleStyle);
//
//        style = wb.createCellStyle();
//        style.cloneStyleFrom(styles.get("title"));
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
//        style.setWrapText(true);
//        // style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
//        style.setAlignment(CellStyle.ALIGN_LEFT);
//        Font dataFont = wb.createFont();
//        dataFont.setFontName("Arial");
//        dataFont.setFontHeightInPoints((short) DEFAULT_DATA_FONT_SIZE);
//        dataFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
//        style.setFont(dataFont);
//        styles.put("data", style);
//
//        style = wb.createCellStyle();
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
//        style.cloneStyleFrom(styles.get("data"));
//        Font headerFont = wb.createFont();
//        headerFont.setFontName("Arial");
//        headerFont.setFontHeightInPoints((short) DEFAULT_HEAD_FONT_SIZE);
//        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        style.setFont(headerFont);
//        styles.put("header", style);
//
//        return styles;
//    }
//
//    /**
//     * 添加一行。
//     *
//     * @return 行对象
//     */
//    public Row addRow() {
//        return sheet.createRow(rownum++);
//    }
//
//    /**
//     * 添加一个单元格.
//     *
//     * @param row
//     *            添加的行
//     * @param column
//     *            添加列号
//     * @param val
//     *            添加值
//     * @return 单元格对象
//     */
//    public Cell addCell(Row row, int column, Object val) {
//        Cell cell = row.createCell(column);
//        cell.setCellType(Cell.CELL_TYPE_STRING);
//        CellStyle style = styles.get("data");
//
//        try {
//            if (val == null) {
//                cell.setCellValue("");
//            } else if (val instanceof String) {
//                cell.setCellValue((String) val);
//            } else if (val instanceof Integer) {
//                cell.setCellValue((Integer) val);
//            } else if (val instanceof Long) {
//                cell.setCellValue((Long) val);
//            } else if (val instanceof Double) {
//                cell.setCellValue((Double) val);
//            } else if (val instanceof Float) {
//                cell.setCellValue((Float) val);
//            } else if (val instanceof Date) {
//                // DataFormat format = wb.createDataFormat();
//                // style.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));
//                val = DateUtil.format((Date) val, "yyyy-MM-dd HH:mm:ss");
//                cell.setCellValue((String) val);
//            }
//        } catch (Exception ex) {
//            cell.setCellValue(val.toString());
//        }
//        if (style != null) {
//            cell.setCellStyle(style);
//        }
//
//        return cell;
//    }
//
//    /**
//     * 合并单元格。
//     *
//     * @param firstRow
//     *            开始行
//     * @param lastRow
//     *            结束行
//     * @param firstCol
//     *            开始列
//     * @param lastCol
//     *            结束列
//     */
//    public void addMergedRegion(int firstRow, int lastRow, int firstCol,
//        int lastCol) {
//        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol,
//            lastCol));
//    }
//
//    /**
//     * 设置宽度.
//     *
//     * @param columnIndex
//     *            列索引
//     * @param width
//     *            宽度
//     */
//    public void setColumnWidth(int columnIndex, int width) {
//        sheet.setColumnWidth(columnIndex, width);
//    }
//
//    /**
//     * @return styles属性
//     */
//    public Map<String, CellStyle> getStyles() {
//        return styles;
//    }
//
//    /**
//     * @return wb属性
//     */
//    public HSSFWorkbook getWb() {
//        return wb;
//    }
//
//    /**
//     * 获取基础样式带边框.
//     *
//     * @return CellStyle
//     */
//    public CellStyle getDataBorderStyle() {
//        CellStyle style = wb.createCellStyle();
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
//        // style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
//        style.setAlignment(CellStyle.ALIGN_LEFT);
//        Font dataFont = wb.createFont();
//        dataFont.setFontName("Arial");
//        dataFont.setFontHeightInPoints((short) DEFAULT_DATA_FONT_SIZE);
//        dataFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
//        style.setFont(dataFont);
//        return style;
//    }
//
//    /**
//     * 获取基础样式.
//     *
//     * @return CellStyle
//     */
//    public CellStyle getDataStyle() {
//        CellStyle style = wb.createCellStyle();
//        // style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
//        style.setAlignment(CellStyle.ALIGN_LEFT);
//        Font dataFont = wb.createFont();
//        dataFont.setFontName("Arial");
//        dataFont.setFontHeightInPoints((short) DEFAULT_DATA_FONT_SIZE);
//        dataFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
//        style.setFont(dataFont);
//        return style;
//    }
//
//    /**
//     * 根据字符串获取自适应行高.
//     *
//     * @param str
//     *            字符串
//     * @param fontCountInline
//     *            每行显示的字数
//     * @return 行高
//     */
//    public float getExcelCellAutoHeight(String str, int fontCountInline,
//        float rowHeight) {
//        int defaultCount = str.length();
//        if (defaultCount > fontCountInline) {
//            return ((int) (defaultCount / fontCountInline) + 1) * rowHeight; // 计算
//        } else {
//            return rowHeight;
//        }
//    }
//
//    /**
//     * 输出数据流.
//     *
//     * @param os
//     *            输出数据流
//     * @throws IOException
//     *             IOException
//     */
//    public void write(OutputStream os) throws IOException {
//        wb.write(os);
//        os.close();
//    }
//
//    /**
//     * 输出到客户端.
//     *
//     * @param response
//     *            response
//     * @param request
//     *            request
//     * @param fileName
//     *            fileName
//     * @throws IOException
//     *             IOException
//     * @update hesw 修改文件名支持，判断浏览器内核来处理不同的文件名编码
//     */
//    public void write(HttpServletResponse response, HttpServletRequest request,
//        String fileName) throws IOException {
//        response.reset();
//        String userAgent = request.getHeader("User-Agent");
//        if (userAgent.toLowerCase().indexOf("firefox") > 0) {
//            byte[] bytes = fileName.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
//            fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
//        } else if (userAgent.toLowerCase().indexOf("msie") > 0) {
//            fileName = URLEncoder.encode(fileName, "UTF-8"); // 各浏览器基本都支持ISO编码
//        } else if (userAgent.toLowerCase().indexOf("chrome") > 0 && userAgent.toLowerCase().indexOf("edge") < 0) {
//            byte[] bytes = fileName.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
//            fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
//        } else {
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//        }
//        response.setHeader("Content-Disposition", "attachment; filename=\""
//            + fileName + "\"");
//        response.setContentType("application/octet-stream");
//        response.setCharacterEncoding("utf-8");
//        write(response.getOutputStream());
//    }
//
//    /**
//     * 输出到文件.
//     *
//     * @param name
//     *            输出文件名
//     * @throws FileNotFoundException
//     *             FileNotFoundException
//     * @throws IOException
//     *             IOException
//     */
//    public void writeFile(String name)
//        throws FileNotFoundException, IOException {
//        FileOutputStream os = new FileOutputStream(name);
//        this.write(os);
//    }
//
//    /**
//     * .
//     */
//    private static final short DEFAULT_NUM_8 = 8;
//    /**
//     * .
//     */
//    private static final short DEFAULT_NUM_3 = 3;
//    /**
//     * .
//     */
//    private static final short DEFAULT_NUM_6 = 6;
//
//    /**
//     * .
//     *
//     * @param args
//     *            args
//     * @throws FileNotFoundException
//     *             FileNotFoundException
//     * @throws IOException
//     *             IOException
//     */
//    public static void main(String[] args)
//        throws FileNotFoundException, IOException {
//        ExportExcelConfig excelBean = new ExportExcelConfig();
//        excelBean.setSubtitleAlign(CellStyle.ALIGN_CENTER);
//        excelBean.setTitle("福州肺科医院考勤汇总表");
//        excelBean.setSubtitle("2015/4/1");
//
//        List<List<String>> multipHeaders = new ArrayList<List<String>>();
//        List<String> header1 = new ArrayList<String>();
//        header1.add("编号");
//        header1.add("部门");
//        header1.add("在编人员");
//        header1.add("非在编");
//        header1.add("");
//        header1.add("");
//        header1.add("");
//        header1.add("总计");
//        multipHeaders.add(header1);
//        List<String> header2 = new ArrayList<String>();
//        header2.add("");
//        header2.add("");
//        header2.add("");
//        header2.add("专技");
//        header2.add("工勤");
//        header2.add("返聘");
//        header2.add("试用");
//        header2.add("");
//        multipHeaders.add(header2);
//        excelBean.setMultipHeaders(multipHeaders);
//        excelBean.setMultipHeaderColSize(DEFAULT_NUM_8);
//
//        // excelBean.setHeaders(ListUtil.array2List(new String[] {"表头1", "表头2"
//        // }));
//        ExportExcelHelper simpleExportExcel = new ExportExcelHelper(excelBean);
//        simpleExportExcel.addMergedRegion(2, 2, DEFAULT_NUM_3, DEFAULT_NUM_6);
//        // 当默认样式不满足需求时，可自定义样式
//        // simpleExportExcel.getStyles().get("title")
//        // .setAlignment(CellStyle.ALIGN_LEFT);
//
//        simpleExportExcel.writeFile("e:/test.xls");
//    }
//
//}
