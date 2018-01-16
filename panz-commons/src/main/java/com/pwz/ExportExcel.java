//package com.pwz;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.util.Region;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.xmlbeans.impl.validator.ValidatorUtil;
//
///**********************************************************
//
// *****************************************************************/
//public final class ExportExcel {
//
//    /**
//     * 数据头标示.
//     */
//    public static final String      HEAD_LINE        = "#{headers}";
//
//    /**
//     * 数据行标识.
//     */
//    public static final String      DATA_LINE        = "#{lists}";
//
//    /**
//     * 默认样式标识.
//     */
//    public static final String      DEFAULT_STYLE    = "#{defaultStyles}";
//
//    /**
//     * 行样式标识.
//     */
//    public static final String      STYLE            = "#{styles}";
//    /**
//     * 插入序号样式标识.
//     */
//    public static final String      SER_NUM          = "#{numbers}";
//
//    /**
//     *
//     */
//    private static ExportExcel      et               = new ExportExcel();
//
//    /**
//     * Workbook.
//     */
//    private Workbook                wb;
//
//    /**
//     * Sheet.
//     */
//    private Sheet                   sheet;
//
//    /**
//     * 数据的初始化列数.
//     */
//    private int                     initColIndex;
//
//    /**
//     * 表头的初始化列数.
//     */
//    private int                     initHeadColIndex;
//
//    /**
//     * 数据的初始化行数.
//     */
//    private int                     initRowIndex;
//
//    /**
//     * 表头的初始化行数.
//     */
//    private int                     initHeadRowIndex;
//
//    /**
//     * 当前列数.
//     */
//    private int                     curColIndex;
//
//    /**
//     * 当前表头列数.
//     */
//    private int                     curHeadColIndex;
//
//    /**
//     * 当前行数.
//     */
//    private int                     curRowIndex;
//
//    /**
//     * 当前表头行数.
//     */
//    private int                     curHeadRowIndex;
//
//    /**
//     * 当前行对象.
//     */
//    private Row                     curRow;
//
//    /**
//     * 当前表头对象.
//     */
//    private Row                     curHeadRow;
//
//    /**
//     * 最后一行的数据.
//     */
//    private int                     lastRowIndex;
//
//    /**
//     * 最后一行表头的数据索引.
//     */
//    private int                     lastHeadRowIndex = 0;
//
//    /**
//     * 默认样式.
//     */
//    private CellStyle               defaultStyle;
//
//    /**
//     * 默认表头样式.
//     */
//    private CellStyle               defaultHeaderStyle;
//
//    /**
//     * 默认行高.
//     */
//    private float                   rowHeight;
//
//    /**
//     * 存储某一方所对于的样式.
//     */
//    private Map<Integer, CellStyle> styles;
//
//    /**
//     * 序号的列.
//     */
//    private int                     serColIndex;
//
//    /**
//     * 私有构造 .
//     */
//    private ExportExcel() {
//
//    }
//
//    public Workbook getWb() {
//        return wb;
//    }
//
//    public static ExportExcel getInstance() {
//        return et;
//    }
//
//    /**
//     * 从classpath路径下读取相应的模板文件。
//     *
//     * @param path
//     *            模板路径
//     */
//    public void readTemplateByClasspath(String path) {
//        try {
//            wb = WorkbookFactory.create(ExportExcel.class
//                .getResourceAsStream(path));
//            initTemplate();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板格式有错，！请检查");
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板不存在！请检查");
//        }
//    }
//
//    /**
//     * 将文件写到相应的路径下.
//     *
//     * @param filepath
//     *            导出excel文件的路径
//     */
//    public void writeToFile(String filepath) {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(filepath);
//            wb.write(fos);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException("写入的文件不存在");
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("写入数据失败:" + e.getMessage());
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 将文件写到某个输出流中。
//     *
//     * @param os
//     *            OutputStream
//     */
//    public void wirteToStream(OutputStream os) {
//        try {
//            wb.write(os);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("写入流失败:" + e.getMessage());
//        }
//    }
//
//    /**
//     * 从某个路径来读取模板。
//     *
//     * @param path
//     *            读取excel模板的路径
//     * @return
//     */
//    public void readTemplateByPath(String path) {
//        try {
//            wb = WorkbookFactory.create(new File(path));
//            initTemplate();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板格式有错，！请检查");
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板不存在！请检查");
//        }
//    }
//
//    /**
//     * 从某个路径来读取模板。
//     *
//     * @param file
//     *            读取excel模板的路径
//     * @return
//     */
//    public void readTemplateByPath(File file) {
//        try {
//            wb = WorkbookFactory.create(file);
//            initTemplate();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板格式有错，！请检查");
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板不存在！请检查");
//        }
//    }
//
//    /**
//     * 从某个路径来下载模板(不初始化数据，只下载模板)。
//     *
//     * @param file
//     *            读取excel模板的路径
//     * @return
//     */
//    public void downloadTemplateByPath(File file) {
//        try {
//            wb = WorkbookFactory.create(file);
//            sheet = wb.getSheetAt(0);
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板格式有错，！请检查");
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("读取模板不存在！请检查");
//        }
//    }
//
//    /**
//     * 创建相应的元素，基于String类型.
//     *
//     * @param value
//     *            单元格的值.
//     */
//    public void createCell(String value) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于String类型.
//     *
//     * @param value
//     *            单元格的值
//     */
//    public void createHeadCell(String value) {
//        Cell c = curHeadRow.createCell(curHeadColIndex);
//        setHeaderCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curHeadColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Integer类型.
//     *
//     * @param value
//     *            单元格的值
//     */
//    public void createCell(Integer value) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Date类型.
//     *
//     * @param value
//     *            单元格的值
//     */
//    public void createCell(Date value) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Double类型.
//     *
//     * @param value
//     *            单元格的值
//     */
//    public void createCell(Double value) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Boolean类型.
//     *
//     * @param value
//     *            单元格的值
//     */
//    public void createCell(Boolean value) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Calendar类型 .
//     *
//     * @param value
//     *            单元格的值
//     */
//    public void createCell(Calendar value) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        if (value == null) {
//            c.setCellValue("");
//        } else {
//            c.setCellValue(value);
//        }
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于String类型， 同时指定样式。
//     *
//     * @param value
//     *            单元格的值
//     * @param cellStyle
//     *            单元格样式
//     */
//    public void createCell(String value, CellStyle cellStyle) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        c.setCellValue(value);
//        c.setCellStyle(cellStyle);
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Calendar类型， 同时指定样式。
//     *
//     * @param value
//     *            单元格的值
//     * @param cellStyle
//     *            单元格样式
//     */
//    public void createCell(Calendar value, CellStyle cellStyle) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        c.setCellValue(value);
//        c.setCellStyle(cellStyle);
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于boolean类型， 同时指定样式。
//     *
//     * @param value
//     *            单元格的值
//     * @param cellStyle
//     *            单元格样式
//     */
//    public void createCell(Boolean value, CellStyle cellStyle) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        c.setCellValue(value);
//        c.setCellStyle(cellStyle);
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于double类型， 同时指定样式。
//     *
//     * @param value
//     *            单元格的值
//     * @param cellStyle
//     *            单元格样式
//     */
//    public void createCell(Double value, CellStyle cellStyle) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        c.setCellValue(value);
//        c.setCellStyle(cellStyle);
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Date类型， 同时指定样式。
//     *
//     * @param value
//     *            单元格的值
//     * @param cellStyle
//     *            单元格样式
//     */
//    public void createCell(Date value, CellStyle cellStyle) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        c.setCellValue(value);
//        c.setCellStyle(cellStyle);
//        curColIndex++;
//    }
//
//    /**
//     * 创建相应的元素，基于Integer类型， 同时指定样式。
//     *
//     * @param value
//     *            单元格的值
//     * @param cellStyle
//     *            单元格样式
//     */
//    public void createCell(Integer value, CellStyle cellStyle) {
//        Cell c = curRow.createCell(curColIndex);
//        setCellStyle(c);
//        c.setCellValue(value);
//        c.setCellStyle(cellStyle);
//        curColIndex++;
//    }
//
//    /**
//     * 设置某个元素的样式.
//     *
//     * @param c
//     *            单元格
//     */
//    private void setCellStyle(Cell c) {
//        if (styles.containsKey(curColIndex)) {
//            c.setCellStyle(styles.get(curColIndex));
//        } else {
//            c.setCellStyle(defaultStyle);
//        }
//    }
//
//    /**
//     * 设置某个元素的样式.
//     *
//     * @param c
//     *            单元格
//     */
//    private void setHeaderCellStyle(Cell c) {
//        if (styles.containsKey(curHeadColIndex)) {
//            c.setCellStyle(styles.get(curHeadColIndex));
//        } else {
//            c.setCellStyle(defaultHeaderStyle);
//        }
//    }
//
//    /**
//     * 创建新行，在使用时只要添加完一行，需要调用该方法创建。
//     */
//    public void createNewRow() {
//        if (lastRowIndex > curRowIndex && curRowIndex != initRowIndex) {
//            sheet.shiftRows(curRowIndex, lastRowIndex, 1, true, true);
//            lastRowIndex++;
//        }
//        curRow = sheet.createRow(curRowIndex);
//        curRow.setHeightInPoints(rowHeight);
//        curRowIndex++;
//        curColIndex = initColIndex;
//    }
//
//    /**
//     * 创建新行，在使用时只要添加完一行，需要调用该方法创建.
//     */
//    public void createNewHeadRow() {
//        if (lastHeadRowIndex > curHeadRowIndex
//            && curHeadRowIndex != initHeadRowIndex) {
//            lastHeadRowIndex++;
//        }
//        curHeadRow = sheet.getRow(curHeadRowIndex);
//        curHeadRow.setHeightInPoints(rowHeight);
//        curHeadRowIndex++;
//        curHeadColIndex = initHeadColIndex;
//    }
//
//    /**
//     * 创建新行，在使用时只要添加完一行，需要调用该方法创建。
//     *
//     * @param rowHeightCustom
//     *            行高
//     */
//    public void createNewRow(float rowHeightCustom) {
//        if (lastRowIndex > curRowIndex && curRowIndex != initRowIndex) {
//            sheet.shiftRows(curRowIndex, lastRowIndex, 1, true, true);
//            lastRowIndex++;
//        }
//        curRow = sheet.createRow(curRowIndex);
//        curRow.setHeightInPoints(rowHeightCustom);
//        curRowIndex++;
//        curColIndex = initColIndex;
//    }
//
//    /**
//     *
//     * 创建新行，支持指定行索引.
//     *
//     * @param rowHeightCustom 行高
//     * @param curDataLineIndex 行索引
//     */
//    public void createNewRow(Float rowHeightCustom, Integer curDataLineIndex) {
//        if (lastRowIndex > curDataLineIndex && curDataLineIndex != initRowIndex) {
//            sheet.shiftRows(curDataLineIndex, lastRowIndex, 1, true, true);
//            lastRowIndex++;
//        }
//        curRow = sheet.createRow(curDataLineIndex);
//        if (rowHeightCustom != null) {
//            curRow.setHeightInPoints(rowHeightCustom);
//        }
//        curRowIndex = curDataLineIndex + 1;
//        curColIndex = initColIndex;
//    }
//
//    /**
//     * 插入序号，会自动找相应的序号标示的位置完成插入，自动累加.
//     */
//    public void insertSer() {
//        int index = 1;
//        Row row = null;
//        Cell c = null;
//        for (int i = initRowIndex; i < curRowIndex; i++) {
//            row = sheet.getRow(i);
//            c = row.createCell(serColIndex);
//            setCellStyle(c);
//            c.setCellValue(index++);
//        }
//    }
//
//    /**
//     * 插入序号，会自动找相应的序号标示的位置完成插入，指定序列值.
//     *
//     * @param index
//     *            序列值
//     */
//    public void insertSer(String index) {
//        Row row = null;
//        Cell c = null;
//        row = sheet.getRow(curRowIndex - 1);
//        c = row.createCell(serColIndex);
//        setCellStyle(c);
//        c.setCellValue(index);
//    }
//
//    /**
//     * 根据map替换相应的常量，通过Map中的值来替换${param}。
//     *
//     * @param datas
//     *            map集合
//     */
//    public void replaceFinalData(Map<String, String> datas) {
//        if (datas == null) {
//            return;
//        }
//        for (Row row : sheet) {
//            for (Cell c : row) {
//                if (c.getCellType() != Cell.CELL_TYPE_STRING) {
//                    continue;
//                }
//                for (Map.Entry<String, String> setEntry : datas.entrySet()) {
//                    String str = c.getStringCellValue().trim();
//                    Pattern pattern = Pattern.compile("\\$\\{("
//                        + setEntry.getKey() + "[.^\\}]*)\\}");
//                    Matcher matcher = pattern.matcher(str);
//                    if (matcher.find()) {
//                        c.setCellValue(matcher.replaceAll(datas.get(matcher
//                            .group(1))));
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 初始化数据 默认初始化第一个sheet。
//     */
//    private void initTemplate() {
//        sheet = wb.getSheetAt(0);
//        initHeadConfigData();
//        initConfigData();
//        lastRowIndex = sheet.getLastRowNum();
//        curRow = sheet.createRow(curRowIndex);
//        curHeadRow = sheet.getRow(curHeadRowIndex);
//    }
//
//    /**
//     * 初始化数据 默认初始化第一个sheet。
//     *
//     * @param newSheet
//     *            newSheet
//     */
//    public void initTemplate(Sheet newSheet) {
//        this.sheet = newSheet;
//        initHeadConfigData();
//        initConfigData();
//        lastRowIndex = sheet.getLastRowNum();
//        curRow = sheet.createRow(curRowIndex);
//        curHeadRow = sheet.getRow(curHeadRowIndex);
//    }
//
//    /**
//     * 初始化数据信息。
//     */
//    private void initConfigData() {
//        boolean findData = false;
//        boolean findSer = false;
//        for (Row row : sheet) {
//            if (findData) {
//                break;
//            }
//            for (Cell c : row) {
//                if (c.getCellType() != Cell.CELL_TYPE_STRING) {
//                    continue;
//                }
//                String str = c.getStringCellValue().trim();
//                if (str.equals(SER_NUM)) {
//                    serColIndex = c.getColumnIndex();
//                    findSer = true;
//                }
//                if (str.equals(DATA_LINE)) {
//                    initColIndex = c.getColumnIndex();
//                    initRowIndex = row.getRowNum();
//                    curColIndex = initColIndex;
//                    curRowIndex = initRowIndex;
//                    findData = true;
//                    defaultStyle = c.getCellStyle();
//                    rowHeight = row.getHeightInPoints();
//                    initStyles();
//                    break;
//                }
//            }
//        }
//        if (!findSer) {
//            initSer();
//        }
//    }
//
//    /**
//     * 初始化数据信息。
//     */
//    private void initHeadConfigData() {
//        boolean findData = false;
//        if (lastHeadRowIndex > 0) {
//            for (Row row : sheet) {
//                if (findData) {
//                    break;
//                }
//                for (Cell c : row) {
//                    if (c.getCellType() != Cell.CELL_TYPE_STRING) {
//                        continue;
//                    }
//                    String str = c.getStringCellValue().trim();
//                    if (str.equals(HEAD_LINE)) {
//                        initHeadColIndex = c.getColumnIndex();
//                        initHeadRowIndex = row.getRowNum();
//                        curHeadColIndex = initHeadColIndex;
//                        curHeadRowIndex = initHeadRowIndex;
//                        findData = true;
//                        defaultHeaderStyle = c.getCellStyle();
//                        rowHeight = row.getHeightInPoints();
//                        initStyles();
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    public void setLastHeadRowIndex(int rowIndex) {
//        lastHeadRowIndex = rowIndex;
//    }
//
//    /**
//     * 初始化序号位置。
//     */
//    private void initSer() {
//        for (Row row : sheet) {
//            for (Cell c : row) {
//                if (c.getCellType() != Cell.CELL_TYPE_STRING) {
//                    continue;
//                }
//                String str = c.getStringCellValue().trim();
//                if (str.equals(SER_NUM)) {
//                    serColIndex = c.getColumnIndex();
//                }
//            }
//        }
//    }
//
//    /**
//     * 初始化样式信息.
//     */
//    private void initStyles() {
//        styles = new HashMap<Integer, CellStyle>();
//        for (Row row : sheet) {
//            for (Cell c : row) {
//                if (c.getCellType() != Cell.CELL_TYPE_STRING) {
//                    continue;
//                }
//                String str = c.getStringCellValue().trim();
//                if (str.equals(DEFAULT_STYLE)) {
//                    defaultStyle = c.getCellStyle();
//                    defaultHeaderStyle = c.getCellStyle();
//                }
//                if (str.equals(STYLE)) {
//                    styles.put(c.getColumnIndex(), c.getCellStyle());
//                }
//            }
//        }
//    }
//
//    /**
//     * 转换字符串编码 ，解决文件名乱码。
//     *
//     * @param filename
//     *            文件名
//     * @param request
//     *            HttpServletRequest
//     * @return 转换后的文件名
//     */
//    public static String encodeFilename(String filename,
//        HttpServletRequest request) {
//        String agent = request.getHeader("USER-AGENT");
//        if (!ValidatorUtil.isEmpty(filename)) {
//            filename = StringUtil.replaceAll(filename, "-", "");
//            try {
//                if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
//                    return URLEncoder.encode(filename, "utf-8");
//                }
//                return new String(filename.getBytes("utf-8"), "ISO8859_1");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return filename;
//    }
//
//    /**
//     * 合并单元格（以一定规律合并）。
//     *
//     * @param mergedRowCount
//     *            合并行数
//     * @param mergedCol
//     *            合并的列号
//     */
//    public void addMergedRegion(int mergedRowCount, int mergedCol) {
//        for (int i = initRowIndex; i < lastRowIndex; i++) {
//            if (i % mergedRowCount == 0) {
//                sheet.addMergedRegion(new CellRangeAddress(i - 1, i, mergedCol,
//                    mergedCol));
//            }
//        }
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
//     * 根据字符串获取自适应行高.
//     *
//     * @param str
//     *            字符串
//     * @param fontCountInline
//     *            每行显示的字数
//     * @return 行高
//     */
//    public float getExcelCellAutoHeight(String str, int fontCountInline) {
//        int defaultCount = str.length();
//        if (defaultCount > fontCountInline) {
//            return ((int) (defaultCount / fontCountInline) + 1) * rowHeight; // 计算
//        } else {
//            return rowHeight;
//        }
//    }
//    /**
//     * 设置当前行
//     * .
//     *
//     * @param index
//     */
//    public void setCurRowIndex(int index){
//        curRowIndex = index;
//    }
//
//    /**
//     * 获取当前行
//     * .
//     *
//     * @param index
//     */
//    public int getCurRowIndex(){
//        return curRowIndex;
//    }
//
//
//
//    /**
//	 * 根据行内容重新计算行高
//	 *  <p> 有合并单元格的要乘系数
//	 * @param row
//	 */
//	public static void calcAndSetRowHeigt(HSSFRow sourceRow) {
//		//原行高
//		short height = sourceRow.getHeight();
//		//计算后的行高
//		double maxHeight = height;
//		for (int cellIndex = sourceRow.getFirstCellNum(); cellIndex <= sourceRow.getPhysicalNumberOfCells(); cellIndex++) {
//			HSSFCell sourceCell = sourceRow.getCell(cellIndex);
//			//单元格的内容
//			String cellContent = getCellContentAsString(sourceCell);
//			if(null == cellContent || "".equals(cellContent)){
//				continue;
//			}
//			//单元格的宽度
//			int columnWidth = getCellWidth(sourceCell);
//			System.out.println("单元格的宽度 : " + columnWidth + "    单元格的高度 : " + maxHeight + ",    单元格的内容 : " + cellContent);
//			HSSFCellStyle cellStyle = sourceCell.getCellStyle();
//			HSSFFont font = cellStyle.getFont(sourceRow.getSheet().getWorkbook());
//			//字体的高度
//			short fontHeight = font.getFontHeight();
//
//			//cell内容字符串总宽度
//			double cellContentWidth = cellContent.getBytes().length * 2 * 256;
//
//		   //字符串需要的行数 不做四舍五入之类的操作
//		   double stringNeedsRows =(double)cellContentWidth / columnWidth;
//		   //小于一行补足一行
//		   if(stringNeedsRows < 1.0){
//		   	stringNeedsRows = 1.0;
//		   }
//
//		   //需要的高度 			(Math.floor(stringNeedsRows) - 1) * 40 为两行之间空白高度
//		   double stringNeedsHeight = (double)fontHeight * stringNeedsRows;
//		   if(stringNeedsHeight > maxHeight){
//		   	maxHeight = stringNeedsHeight;
//		   }
//		   System.out.println("字体高度 : " + fontHeight + ",    字符串宽度 : " + cellContentWidth + ",    字符串需要的行数 : " + stringNeedsRows + ",   需要的高度 : " + stringNeedsHeight);
//		   System.out.println();
//		}
//		//超过原行高三倍 则为3倍 实际应用中可
//		if(maxHeight/height > 5){
//			maxHeight = 5 * height;
//		}
//		//最后取天花板防止高度不够
//		maxHeight = Math.ceil(maxHeight);
//		sourceRow.setHeight((short)maxHeight);
//	}
//
//	/**
//	 * 解析一个单元格得到数据
//	 * @param columnNameList
//	 * @param row
//	 * @param ext2
//	 * @param ext1
//	 * @return
//	 */
//	private static String getCellContentAsString(HSSFCell cell) {
//		if(null == cell){
//			return "";
//		}
//		String result = "";
//		switch (cell.getCellType()) {
//		case Cell.CELL_TYPE_NUMERIC:
//			String s = String.valueOf(cell.getNumericCellValue());
//			if (s != null) {
//				if (s.endsWith(".0")) {
//					s = s.substring(0, s.length() - 2);
//				}
//			}
//			result = s;
//			break;
//		case Cell.CELL_TYPE_STRING:
//			if(cell.getStringCellValue() == null){
//				cell.setCellValue("");
//			}
//			result = String.valueOf(cell.getStringCellValue()).trim();
//			break;
//		case Cell.CELL_TYPE_BLANK:
//			break;
//		case Cell.CELL_TYPE_BOOLEAN:
//			result = String.valueOf(cell.getBooleanCellValue());
//			break;
//		case Cell.CELL_TYPE_ERROR:
//			break;
//		default:
//			break;
//		}
//		return result;
//	}
//
//	/**
//	* 获取单元格及合并单元格的宽度
//	* @param sheet
//	* @param row
//	* @param column
//	* @return
//	*/
//    private static int getCellWidth(HSSFCell cell) {
//    	int result = 0;
//    	HSSFSheet sheet = cell.getSheet();
//    	int rowIndex = cell.getRowIndex();
//    	int columnIndex = cell.getColumnIndex();
//
//    	boolean isPartOfRegion = false;
//    	int firstColumn = 0;
//    	int lastColumn = 0;
//    	int firstRow = 0;
//    	int lastRow = 0;
//		int sheetMergeCount = sheet.getNumMergedRegions();
//		for (int i = 0; i < sheetMergeCount; i++) {
//			Region ca = sheet.getMergedRegionAt(i);
//			firstColumn = ca.getColumnFrom();
//			lastColumn = ca.getColumnTo();
//			firstRow = ca.getRowFrom();
//			lastRow = ca.getRowTo();
//			if (rowIndex >= firstRow && rowIndex <= lastRow) {
//				if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
//					isPartOfRegion = true;
//					break;
//				}
//			}
//		}
//		if(isPartOfRegion){
//			for (int i = firstColumn; i <= lastColumn; i++) {
//				result += sheet.getColumnWidth(i);
//			}
//		}else{
//			result = sheet.getColumnWidth(columnIndex);
//		}
//		return result;
//	}
//}
