package com.pwz.myGenerator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * 自动生成MyBatis的实体类、实体映射XML文件、Mapper
 *
 * @author WYS
 * @version v1.0
 * @date 2014-11-8
 */
@SuppressWarnings("hiding")
public class EntityUtil {

    private final String packageName = "com.linker.resmaster.module.pdsc.ordertic.";
    private final String urlpath = "module/pdsc/ordertic";
    private final String pojoName = "OrderTic";
    private String tableName = "t_dd_dl";
    private final String pojoStr = "指令票";
    private final String author = "潘文卓";

    private final String driverName = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String password = "123456";
    private final String moduleName = "dsp"; //
    private final String url = "jdbc:mysql://192.168.1.105:3306/" + moduleName + "?characterEncoding=utf8";
    /**
     * *********************************使用前必读*******************
     * *
     * * 改以上字段就行了 ， 去d盘下pojoName的文件下找代码
     * *
     * **********************************************************
     */

    private final String type_char = "char";

    private final String type_date = "date";

    private final String type_timestamp = "timestamp";

    private final String type_int = "int";

    private final String type_bigint = "bigint";

    private final String type_text = "text";

    private final String type_bit = "bit";

    private final String type_decimal = "decimal";

    private final String type_blob = "blob";


    private String beanName = "DeviceZf";

    private String mapperName = "zfDao.xml";

    private Connection conn = null;


    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
    }


    /**
     * 获取所有的表
     *
     * @return
     * @throws SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString(1);
            //          if ( tableName.toLowerCase().startsWith("yy_") ) {
            tables.add(tableName);
            //          }
        }
        return tables;
    }


    private void processTable(String table) {
        StringBuffer sb = new StringBuffer(table.length());
        String tableNew = table.toLowerCase();
        String[] tables = tableNew.split("_");
        String temp = null;
        for (int i = 1; i < tables.length; i++) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        beanName = sb.toString();
        mapperName = beanName + "Mapper";
    }


    private String processType(String type) {
        if (type.indexOf(type_char) > -1) {
            return "String";
        } else if (type.indexOf(type_bigint) > -1) {
            return "Long";
        } else if (type.indexOf(type_int) > -1) {
            return "Integer";
        } else if (type.indexOf(type_date) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_text) > -1) {
            return "String";
        } else if (type.indexOf(type_timestamp) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_bit) > -1) {
            return "Boolean";
        } else if (type.indexOf(type_decimal) > -1) {
            return "java.math.BigDecimal";
        } else if (type.indexOf(type_blob) > -1) {
            return "byte[]";
        }
        return null;
    }


    private String processField(String field) {
        StringBuffer sb = new StringBuffer(field.length());
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        return sb.toString();
    }


    /**
     * 将实体类名首字母改为小写
     *
     * @param beanName
     * @return
     */
    private String processResultMapId(String beanName) {
        String s = "";
        if (beanName.length() > 0) {
            s = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
        }
        return s;
    }


    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" **/");
        return bw;
    }


    /**
     * 构建方法上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t * " + text);
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }


    /**
     * 生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {
        String poPath = "d:/" + getPathName(pojoName) + "/po";
        File folder = new File(poPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File beanFile = new File(poPath, pojoName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile), "utf-8"));
        bw.write("package " + packageName + "po;");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        //bw.write("import lombok.Data;");
        //      bw.write("import javax.persistence.Entity;");
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.newLine();
        //      bw.write("@Entity");
        //bw.write("@Data");
        //bw.newLine();
        bw.write("public class " + pojoName + " extends BaseEntity " + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            bw.write("\t/**" + comments.get(i) + "**/");
            bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
        }
        bw.newLine();
        bw.write("\t/**分页**/\n" +
                "\tprivate Integer curPageNum;\n" +
                "\tprivate Integer perPageSize;\n" +
                "\n" +
                "\tpublic Integer getCurPageNum() {\n" +
                "\t\treturn curPageNum;\n" +
                "\t}\n" +
                "\n" +
                "\tpublic void setCurPageNum(Integer curPageNum) {\n" +
                "\t\tthis.curPageNum = curPageNum;\n" +
                "\t}\n" +
                "\n" +
                "\tpublic Integer getPerPageSize() {\n" +
                "\t\treturn perPageSize;\n" +
                "\t}\n" +
                "\n" +
                "\tpublic void setPerPageSize(Integer perPageSize) {\n" +
                "\t\tthis.perPageSize = perPageSize;\n" +
                "\t}\n" +
                "\n");
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            bw.newLine();
            //          bw.write("\tpublic void set" + tempField + "(" + tempType + " _" + _tempField + "){");
            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            bw.newLine();
            //          bw.write("\t\tthis." + _tempField + "=_" + _tempField + ";");
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }


    /**
     * 构建controller文件
     *
     * @throws IOException
     */
    private void buildController() throws IOException {

        String controllerPath = "d:/" + getPathName(pojoName) + "/controller";
        File folder = new File(controllerPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(controllerPath, pojoName + "Controller.java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + packageName + "controller;");
        bw.newLine();
        bw.newLine();
        bw.write("import com.google.common.collect.Maps;");
        bw.newLine();
        bw.write("import com.linker.resmaster.base.beans.GridModel;");
        bw.newLine();
        bw.write("import " + packageName + "po." + pojoName + ";");
        bw.newLine();
        bw.write("import " + packageName + "service.I" + pojoName + "Service;");
        bw.newLine();
        bw.write("import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.ResponseBody;\n" +
                "import org.springframework.web.servlet.ModelAndView;\n" +
                "\n" +
                "import java.util.Map;");
        bw.newLine();
        bw = buildClassComment(bw, pojoName + "控制类" + "\n *" + author + " " + new java.util.Date());
        bw.newLine();
        bw.newLine();
        bw.write("@Controller");
        bw.newLine();
        bw.write("@RequestMapping(\"" + urlpath + "/" + getPathName(pojoName) + ".spr\")");
        bw.newLine();
        bw.write("public class " + pojoName + "Controller {");
        bw.newLine();
        bw.newLine();
        bw.write("    @Autowired");
        bw.newLine();
        bw.write("    I" + pojoName + "Service " + getPathName(pojoName) + "Service;");
        bw.newLine();
        bw.newLine();
        bw.write("    /**\n" +
                "     * 主界面\n" +
                "     * @return 视图\n" +
                "     */");
        bw.newLine();
        bw.write("    @RequestMapping(params = \"index\")");
        bw.newLine();
        bw.write("    public String index() {");
        bw.newLine();
        bw.write("        return \"" + urlpath + "/index\";");
        bw.newLine();
        bw.write("    }");
        bw.newLine();
        bw.newLine();
        bw.write("    /**\n" +
                "     * 新增修改界面\n" +
                "     * @param id  主键\n" +
                "     * @return  视图\n" +
                "     */");
        bw.newLine();
        bw.write("    @RequestMapping(params = \"addOrUpdate\")\n" +
                "    public ModelAndView addOrUpdate(String id) {");
        bw.newLine();
        bw.write("        " + pojoName + " " + getPathName(pojoName) + " = " + getPathName(pojoName) + "Service.get(id);\n");
        bw.write("        if(" + getPathName(pojoName) + " == null){\n" +
                "            " + getPathName(pojoName) + " = new " + pojoName + "();\n" +
                "        }\n");
        bw.newLine();
        bw.write("        ModelAndView mv = new ModelAndView(\"" + urlpath + "/addOrUpdate\", \"" + getPathName(pojoName) + "\", " + getPathName(pojoName) + ");");
        bw.newLine();
        bw.write("        return mv;\n" +
                "    }\n");
        bw.newLine();
        bw.write("    /**\n" +
                "     * 保存\n" +
                "     * @param " + getPathName(pojoName) + "\n" +
                "     * @return  实体\n" +
                "     */");
        bw.newLine();
        bw.write("    @ResponseBody\n" +
                "    @RequestMapping(params = \"save\")\n" +
                "    public " + pojoName + " save(" + pojoName + " " + getPathName(pojoName) + ") {\n" +
                "        " + getPathName(pojoName) + "Service.save(" + getPathName(pojoName) + ");\n" +
                "        return " + getPathName(pojoName) + ";\n" +
                "    }");
        bw.newLine();
        bw.newLine();
        bw.write("    /**\n" +
                "     * 删除\n" +
                "     * @param id  主键\n" +
                "     * @return  主键\n" +
                "     */");
        bw.newLine();
        bw.write("    @ResponseBody\n" +
                "    @RequestMapping(params = \"delete\")\n" +
                "    public String delete(String id) {\n" +
                "        " + getPathName(pojoName) + "Service.delete(id);\n" +
                "        return id;\n" +
                "    }");
        bw.newLine();
        bw.newLine();
        bw.write("    /**\n" +
                "     *表格数据\n" +
                "     * @param " + getPathName(pojoName) + "  查询条件实体\n" +
                "     * @return  视图模型\n" +
                "     */");
        bw.newLine();


        BuildHtml buildHtml = new BuildHtml();
        String[] fieldId = buildHtml.getData(1);
        String[] fieldQuery = buildHtml.getData(5);


        bw.write("    @ResponseBody\n" +
                "    @RequestMapping(params = \"findDataGrid\")\n" +
                "    public GridModel findDataGrid(@RequestBody " + pojoName + " " + getPathName(pojoName) + ") {\n" +
                "        GridModel gridModel = null;\n" +
                "        Map<String, Object> params = Maps.newHashMap();\n" +
                "        if (" + getPathName(pojoName) + " != null) {\n" +
                "            params.put(\"curPageNum\", " + getPathName(pojoName) + ".getPerPageSize() * (" + getPathName(pojoName) + ".getCurPageNum() - 1));\n" +
                "            params.put(\"perPageSize\", " + getPathName(pojoName) + ".getPerPageSize());\n");
        for (int i = 0; i < fieldId.length; i++) {
            if ("a".equals(fieldQuery[i])) {
                bw.write("            params.put(\"" + fieldId[i] + "\"," + getPathName(pojoName) + ".get" + getPathNameUP(fieldId[i]) + "());\n");
            }
        }
        bw.write("        }\n" +
                "        gridModel = " + getPathName(pojoName) + "Service.findDataGrid(params);\n" +
                "        return gridModel;\n" +
                "    }");


        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    /**
     * 构建Dao文件
     *
     * @throws IOException
     */
    private void buildDao() throws IOException {

        String daoPath = "d:/" + getPathName(pojoName) + "/dao";
        File folder = new File(daoPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(daoPath, "I" + pojoName + "Dao.java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + packageName + "dao;\n" +
                "\n" +
                "import com.linker.resmaster.base.dao.BaseDao;\n" +
                "import " + packageName + "po." + pojoName + ";\n" +
                "\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * \n" +
                " * @author " + author + "\n" +
                " *" + new java.util.Date() + "\n" +
                " */\n" +
                "public interface I" + pojoName + "Dao extends BaseDao<" + pojoName + ",String>{\n" +
                "\n" +
                "    /**\n" +
                "     * 查询数据列表\n" +
                "     * @param params 查询条件\n" +
                "     * @return  数据列表\n" +
                "     */" +
                "    List<" + pojoName + "> findDataGrid(Map<String, Object> params);\n" +
                "\n" +
                "    /**\n" +
                "     * 查询数据列表 数量\n" +
                "     * @param params 查询条件\n" +
                "     * @return  数量\n" +
                "     */" +
                "\tint countDataGrid(Map<String, Object> params);\n" +
                "}\n");

        bw.flush();
        bw.close();
    }

    /**
     * 构建Service接口文件
     *
     * @throws IOException
     */
    private void buileService() throws IOException {

        String servicePath = "d:/" + getPathName(pojoName) + "/service";
        File folder = new File(servicePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(servicePath, "I" + pojoName + "Service.java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + packageName + "service;\n" +
                "\n" +
                "import com.linker.resmaster.base.beans.GridModel;\n" +
                "import " + packageName + "po." + pojoName + ";\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * Created by " + author + " on " + new java.util.Date() + "\n" +
                " */\n" +
                "public interface I" + pojoName + "Service {\n" +
                "\n" +
                "    /**\n" +
                "     * 新增\n" +
                "     * @param " + getPathName(pojoName) + " 实体\n" +
                "     */\n" +
                "    void add(" + pojoName + " " + getPathName(pojoName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 修改\n" +
                "     * @param " + getPathName(pojoName) + " 实体\n" +
                "     */\n" +
                "    void update(" + pojoName + " " + getPathName(pojoName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 删除\n" +
                "     * @param objId\n" +
                "     */\n" +
                "    void delete(String objId);\n" +
                "\n" +
                "    /**\n" +
                "     * 通过主键 获取实体\n" +
                "     * @param objId 主键\n" +
                "     * @return 实体\n" +
                "     */\n" +
                "    " + pojoName + " get(String objId);\n" +
                "\n" +
                "    /**\n" +
                "     *  保存 新增修改共用\n" +
                "     * @param " + getPathName(pojoName) + " 实体\n" +
                "     */\n" +
                "    void save(" + pojoName + " " + getPathName(pojoName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 表格数据封装\n" +
                "     * @param params 查询条件\n" +
                "     * @return GridModel数据模型\n" +
                "     */\n" +
                "    GridModel findDataGrid(Map<String, Object> params);\n" +
                "}\n");

        bw.flush();
        bw.close();
    }

    /**
     * 构建Service接口实现文件
     *
     * @throws IOException
     */
    private void buileServiceIpml() throws IOException {
        BuildHtml buildHtml = new BuildHtml();
        String[] fieldName = buildHtml.getData(0);
        String[] fieldId = buildHtml.getData(1);
        String[] fieldDict = buildHtml.getData(2);
        String[] fieldType = buildHtml.getData(3);
        String[] fieldHead = buildHtml.getData(4);

        String serviceImplPath = "d:/" + getPathName(pojoName) + "/service/impl";
        File folder = new File(serviceImplPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(serviceImplPath, pojoName + "Service.java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + packageName + "service.impl;\n" +
                "\n" +
                "import com.linker.resmaster.base.beans.GridModel;\n" +
                "import com.linker.resmaster.base.beans.GridNodeModel;\n" +
                "import " + packageName + "dao.I" + pojoName + "Dao;\n" +
                "import " + packageName + "po." + pojoName + ";\n" +
                "import " + packageName + "service.I" + pojoName + "Service;\n" +
                "import com.linker.resmaster.platform.common.exception.PlatformException;\n" +
                "import com.linker.resmaster.utils.DateUtils;\n" +
                "import com.linker.resmaster.utils.StringUtils;\n" +
                "import com.linker.resmaster.utils.UserUtils;\n" +
                "import com.linker.resmaster.utils.UuidUtil;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "/**\n" +
                " * Created by " + author + " on " + new java.util.Date() + ".\n" +
                " */\n" +
                "@Service\n" +
                "public class " + pojoName + "Service implements I" + pojoName + "Service {\n" +
                "\n" +
                "    @Autowired\n" +
                "    I" + pojoName + "Dao " + getPathName(pojoName) + "Dao;\n" +
                "\n" +
                "    @Override\n" +
                "    public void add(" + pojoName + " " + getPathName(pojoName) + ") {\n" +
                "        " + getPathName(pojoName) + ".setObjId(UuidUtil.getUuid());\n" +
                "        " + getPathName(pojoName) + ".setDeleteState('0');\n" +
                "        " + getPathName(pojoName) + ".setCreator(UserUtils.getUserVo().getId());\n" +
                "        " + getPathName(pojoName) + "Dao.add(" + getPathName(pojoName) + ");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void update(" + pojoName + " " + getPathName(pojoName) + ") {\n" +
                "        " + getPathName(pojoName) + ".setModifier(UserUtils.getUserVo().getId());\n" +
                "        " + getPathName(pojoName) + ".setModifyTime(DateUtils.CURR_DATE_STR2);\n" +
                "        " + getPathName(pojoName) + ".setCreateTime(null);\n" +
                "        " + getPathName(pojoName) + ".setDeleteState('0');\n" +
                "        " + getPathName(pojoName) + "Dao.update(" + getPathName(pojoName) + ");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void delete(String objId) {\n" +
                "        " + pojoName + " " + getPathName(pojoName) + " = " + getPathName(pojoName) + "Dao.getEntityById(objId);\n" +
                "        " + getPathName(pojoName) + ".setDeleteState('1');\n" +
                "        " + getPathName(pojoName) + "Dao.update(" + getPathName(pojoName) + ");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + pojoName + " get(String objId) {\n" +
                "       return " + getPathName(pojoName) + "Dao.getEntityById(objId);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void save(" + pojoName + " " + getPathName(pojoName) + ") {\n" +
                "        if(StringUtils.isEmpty(" + getPathName(pojoName) + ".getObjId())){\n" +
                "            add(" + getPathName(pojoName) + ");\n" +
                "        }else {\n" +
                "            update(" + getPathName(pojoName) + ");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public GridModel findDataGrid(Map<String, Object> params) {\n" +
                "        GridModel gridModel = new GridModel();\n" +
                "        GridNodeModel gridNodeModel = null;\n" +
                "        List rows = new ArrayList<GridNodeModel>();\n" +
                "        List data = null;\n" +
                "        List<" + pojoName + "> list = null;\n" +
                "        try {\n" +
                "            list = " + getPathName(pojoName) + "Dao.findDataGrid(params);\n" +
                "            if (list.size() >= 0) {\n" +
                "                for (" + pojoName + " " + getPathName(pojoName) + " : list) {\n" +
                "                    gridNodeModel = new GridNodeModel();\n" +
                "                    data = new ArrayList();\n" +
                "                    data.add(\"\");\n");
        for (int i = 0; i < fieldName.length; i++) {
            if ("a".equals(fieldHead[i])) {
                if (StringUtils.isNotEmpty(fieldDict[i])) {
                    bw.write("                    data.add(DictUtils.getDictLabel(\"" + fieldDict[i] + "\"," + getPathName(pojoName) + ".get" + getPathNameUP(fieldId[i]) + "(),\"\"));\n");

                } else {
                    bw.write("                    data.add(" + getPathName(pojoName) + ".get" + getPathNameUP(fieldId[i]) + "());\n");
                }
            }
        }
        bw.write("                    gridNodeModel.setData(data);\n" +
                "                    gridNodeModel.setId(" + getPathName(pojoName) + ".getObjId());\n" +
                "                    rows.add(gridNodeModel);\n" +
                "                }\n" +
                "                gridModel.setRows(rows);\n" +
                "                int totalNum = " + getPathName(pojoName) + "Dao.countDataGrid(params);\n" +
                "                gridModel.setTotalNum(totalNum);\n" +
                "                return gridModel;\n" +
                "            } else {\n" +
                "                return null;\n" +
                "            }\n" +
                "        } catch (Exception e) {\n" +
                "            e.printStackTrace();\n" +
                "            throw new PlatformException(\"查找" + pojoStr + "失败\");\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}\n");

        bw.flush();
        bw.close();
    }

    /**
     * 构建Service接口实现文件
     *
     * @throws IOException
     */
    private void buildIndexJsp() throws IOException {

        BuildHtml buildHtml = new BuildHtml();
        String[] fieldName = buildHtml.getData(0);
        String[] fieldId = buildHtml.getData(1);
        String[] fieldDict = buildHtml.getData(2);
        String[] fieldType = buildHtml.getData(3);
        String[] fieldHead = buildHtml.getData(4);
        String[] fieldQuery = buildHtml.getData(5);

        String indexPath = "d:/" + getPathName(pojoName) + "/jsp";
        File folder = new File(indexPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(indexPath, "index.jsp");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n" +
                "         pageEncoding=\"UTF-8\" %>\n" +
                "<%@ include file=\"/WEB-INF/jsp/include/taglib.jsp\" %>\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>主页</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/fonts/font_roboto/roboto.css\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/dhtmlx-new.css\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/custom/dhtmlx_pagebar_plugin.css\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/custom/common.css\"/>\n" +
                "\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/dhtmlx.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/ext/swfobject.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/jquery/jquery-3.2.1.min.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/custom/dhtmlx_pagebar_plugin.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/common/js/base.js\"></script>\n" +
                "    <style>\n" +
                "        html, body {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            margin: 0px;\n" +
                "            padding: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"tag\">\n" +
                "    <div id=\"searchDiv\" class=\"searchDiv\">\n" +
                "        <form id=\"searchForm\" enctype=\"multipart/form-data\">\n" +
                "            <table style=\"text-align: center; width:90%;\">\n");

        int m = 0;
        int n = 0;
        for (int i = 0; i < fieldId.length; i++) {
            if ("a".equals(fieldQuery[i])) {
                m++;
                if (m == 1 || (m - 1) % 3 == 0) {
                    n++;
                    bw.write("                <tr>\n");
                }
                if (StringUtils.isNotEmpty(fieldDict[i])) {
                    bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                            "                    <td>\n" +
                            "                        <dm:select tableName=\"" + fieldDict[i] + "\" id=\"" + fieldId[i] + "\" name=\"" + fieldId[i] + "\"\n" +
                            "                                   value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\" style=\"width:212px;\">\n" +
                            "                        </dm:select>\n" +
                            "                    </td>\n");
                } else {

                    bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                            "                    <td>\n" +
                            "                        <input  id=\"" + fieldId[i] + "\"  name=\"" + fieldId[i] + "\" type=\"text\"\n" +
                            "                                 class=\"form-control\">\n" +
                            "                    </td>\n");
                }
                if (m % 3 == 0) {
                    bw.write("                </tr>\n");
                }
            }
        }


        bw.write("                <tr>\n" +
                "                    <td colspan=\"2\" style=\"text-align: left;\">\n" +
                "                        <button type=\"button\" onclick=\"add()\" class=\"btn btn-default\">新增</button>\n" +
                "                        <button type=\"button\" onclick=\"edit()\" class=\"btn btn-default\">修改</button>\n" +
                "                        <button type=\"button\" onclick=\"deleteData()\" class=\"btn btn-default\"\n" +
                "                                style=\"background-color: #ff8400\">删除\n" +
                "                        </button>\n" +
                "                    </td>\n" +
                "                    <td colspan=\"4\">\n" +
                "                        <div style=\"float: left\">\n" +
                "                            <button type=\"button\" onclick=\"select()\" class=\"btn btn-default\">查询</button>\n" +
                "                            <button type=\"button\" onclick=\"reset()\" class=\"btn btn-default\">重置</button>\n" +
                "                        </div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "    <div id=\"dataDiv\">\n" +
                "        <div id=\"dataGrid\"></div>\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "<style>\n" +
                "    #tag {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        height: 100%;\n" +
                "    }\n" +
                "\n" +
                "    #dataDiv {\n" +
                "        position: relative;\n" +
                "        left: 15px;\n" +
                "        width: 98%;\n" +
                "        top: 18px;\n" +
                "    }\n" +
                "\n" +
                "    #searchDiv {\n" +
                "        position: relative;\n" +
                "        top: 9px;\n" +
                "        left: 15px;\n" +
                "        width: 100%;\n" +
                "        height: " + (20 + n * 40) + "px;\n" +
                "    }\n" +
                "\n" +
                "    #dataGrid {\n" +
                "        position: relative;\n" +
                "        border-radius: 4px;\n" +
                "        width: 100%;\n" +
                "        top: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .pf-paging {\n" +
                "        position: relative;\n" +
                "        top: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .form-control {\n" +
                "        width: 200px;\n" +
                "    }\n" +
                "\n" +
                "</style>\n" +
                "<script>\n" +
                "    var dhxWins, mygrid;\n");

        StringBuffer sb = new StringBuffer(); //选择变量
        StringBuffer sb2 = new StringBuffer(); //选择

        StringBuffer dateVar = new StringBuffer(); //时间变量

        StringBuffer date = new StringBuffer(); //时间

        sb.append("    var ");
        dateVar.append("    var ");
        for (int i = 0; i < fieldName.length; i++) {
            if ("a".equals(fieldQuery[i])) {
                if (StringUtils.isNotEmpty(fieldDict[i])) {
                    sb.append(fieldId[i] + ",");
                    sb2.append("        " + fieldId[i] + " = new dhtmlXComboFromSelect(\"" + fieldId[i] + "\");");
                    sb2.append("\n");
                } else {
                    if ("b".equals(fieldType[i])) {
                        dateVar.append(fieldId[i] + ",");
                    }
                    if ("b".equals(fieldType[i])) {
                        date.append("        " + fieldId[i] + " = new dhtmlXCalendarObject(\"" + fieldId[i] + "\");");
                        date.append("\n");
                    }
                }
            }
        }
        if (!"    var ".equals(sb.toString())) {
            bw.write(sb.toString().substring(0, sb.length() - 1) + ";\n");
        }
        if (!"    var ".equals(dateVar.toString())) {
            bw.write(dateVar.toString().substring(0, dateVar.length() - 1) + ";\n");
        }
        bw.write("    var selectIds = [];\n" +
                "    $(document).ready(function () {\n" +
                "        //初始化布局\n" +
                "        initLayout();\n" +
                "    });\n" +
                "\n" +
                "    function initLayout() {\n");
        StringBuffer head = new StringBuffer();
        for (int i = 0; i < fieldName.length; i++) {
            if ("a".equals(fieldHead[i])) {
                head.append(fieldName[i] + ",");
            }
        }
        bw.write("        var head = \"选择," + head.toString().substring(0, head.length() - 1) + "\";\n" +
                "        mygrid = new dhtmlXGridObject(\"dataGrid\");\n" +
                "        mygrid.setImagePath(\"${ctx}/lib/dhtmlx/codebase/imgs/\");\n" +
                "        mygrid.setInitWidths(\"80\");\n" +
                "        initGrid(head, mygrid, selectIds);\n" +
                "\n" +
                "        enablePaging(mygrid, \"dataGrid\", queryDevice);//queryDevice:对应查询的方法\n" +
                "        queryDevice(\"dataGrid\", 1, 8);\n");
        bw.newLine();
        bw.newLine();
        bw.write(sb2.toString());
        bw.newLine();
        bw.newLine();
        bw.write(date.toString());
        bw.write("    }\n" +
                "\n" +
                "    //新增" + pojoStr + "\n" +
                "    function add() {\n" +
                "        dhxWins = new dhtmlXWindows();\n" +
                "        dhxWins.attachViewportTo(\"tag\");\n" +
                "       var h = $(\"#tag\").height() - 8;\n" +
                "        var w = $(\"#tag\").width() - 80;\n" +
                "        dhxWins.createWindow(\"02\", 40, 4, w, h);\n" +
                "        dhxWins.window(\"02\").setText(\"新增" + pojoStr + "\");\n" +
                "        dhxWins.window(\"02\").attachURL(\"${ctx}/" + urlpath + "/" + getPathName(pojoName) + ".spr" + "?addOrUpdate\", null, true);\n" +
                "    }\n" +
                "\n" +
                "    //修改" + pojoStr + "\n" +
                "    function edit() {\n" +
                "        var d = [];\n" +
                "        mygrid.forEachRow(function (id) {\n" +
                "            var sort = mygrid.cells(id, 0).getValue();\n" +
                "            if (sort == \"1\") {\n" +
                "                d.push(id);\n" +
                "            }\n" +
                "        });\n" +
                "        if (d.length == 1) {\n" +
                "            var id = d[0];\n" +
                "            dhxWins = new dhtmlXWindows();\n" +
                "            dhxWins.attachViewportTo(\"tag\");\n" +
                "            var h = $(\"#tag\").height() - 8;\n" +
                "            var w = $(\"#tag\").width() - 80;\n" +
                "            dhxWins.createWindow(\"03\", 40, 4, w, h);\n" +
                "            dhxWins.window(\"03\").setText(\"修改" + pojoStr + "\");\n" +
                "            dhxWins.window(\"03\").attachURL(\"${ctx}/" + urlpath + "/" + getPathName(pojoName) + ".spr" + "?addOrUpdate&id=\" + id, null, true);\n" +
                "        } else {\n" +
                "            if (dhtmlx && dhtmlx.message) dhtmlx.message({text: \"请选择一条记录\", expire: 2000});\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    //删除" + pojoStr + "\n" +
                "    var deleteData = function () {\n" +
                "        var d = [];\n" +
                "        mygrid.forEachRow(function (id) {\n" +
                "            var sort = mygrid.cells(id, 0).getValue();\n" +
                "            if (sort == \"1\") {\n" +
                "                d.push(id);\n" +
                "            }\n" +
                "        });\n" +
                "        if (d.length == 0 || d.length > 1) {\n" +
                "            if (dhtmlx && dhtmlx.message) dhtmlx.message({text: \"请选择一条记录\", expire: 2000});\n" +
                "            return;\n" +
                "        }\n" +
                "        var id = d[0];\n" +
                "        dhtmlx.confirm({\n" +
                "            title: \"确定删除\",\n" +
                "            ok: \"确定\", cancel: \"取消\",\n" +
                "            text: \"确定删除？\",\n" +
                "            callback: function (result) {\n" +
                "                if (result == true) {\n" +
                "                    $.ajax({\n" +
                "                        type: \"post\",\n" +
                "                        url: '${ctx}/" + urlpath + "/" + getPathName(pojoName) + ".spr" + "?delete&id=' + id,\n" +
                "                        success: function (data) {\n" +
                "                            if (dhtmlx && dhtmlx.message) dhtmlx.message({text: \"删除成功\", expire: 2000});\n" +
                "                            select();\n" +
                "                        },\n" +
                "                        error: function (data) {\n" +
                "                            if (dhtmlx && dhtmlx.message) dhtmlx.message({text: \"删除失败\", expire: 2000});\n" +
                "                        }\n" +
                "                    });\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    //查询\n" +
                "    function select() {\n" +
                "        queryDevice(\"dataGrid\", 1, 8);\n" +
                "    }\n" +
                "\n" +
                "    function closeWindow(id) {\n" +
                "        dhxWins.window(id).close();\n" +
                "\n" +
                "    }\n" +
                "    function saveWindow(id) {\n" +
                "        select();\n" +
                "        dhxWins.window(id).close();\n" +
                "        if (dhtmlx && dhtmlx.message) dhtmlx.message({text: \"保存成功\", expire: 2000});\n" +
                "    }\n" +
                "\n" +
                "    //分页根据条件筛选列表信息\n" +
                "    var queryDevice = function (gridId, curPageNum, perPageSize) {\n" +
                "        var data = $(\"#searchForm\").serializeJson();\n" +
                "        data.curPageNum = curPageNum;\n" +
                "        data.perPageSize = perPageSize;\n" +
                "        $.ajax({\n" +
                "            type: \"post\",\n" +
                "            dataType: \"json\",\n" +
                "            contentType: 'application/json',\n" +
                "            url: '${ctx}/" + urlpath + "/" + getPathName(pojoName) + ".spr" + "?findDataGrid',\n" +
                "            data: JSON.stringify(data),\n" +
                "            success: function (data) {\n" +
                "                //参数myGrid:dhtmlxgrid对象 gridId:列表id curPageNum:当前页 perPageSize:每页条数\n" +
                "                loadGrid(mygrid, gridId, curPageNum, perPageSize, data);\n" +
                "            },\n" +
                "            error: function () {\n" +
                "            }\n" +
                "        });\n" +
                "    };\n" +
                "\n" +
                "    //重置\n" +
                "    var reset = function () {\n" +
                "        $('#searchForm')[0].reset();\n" +
                "    }\n" +
                "\n" +
                "</script>\n" +
                "</html>");

        bw.flush();
        bw.close();
    }

    /**
     * 构建Service接口实现文件
     *
     * @throws IOException
     */
    private void buildAddorUpdateJsp() throws IOException {

        BuildHtml buildHtml = new BuildHtml();
        String[] fieldName = buildHtml.getData(0);
        String[] fieldId = buildHtml.getData(1);
        String[] fieldDict = buildHtml.getData(2);
        String[] fieldType = buildHtml.getData(3);
        String[] fieldHead = buildHtml.getData(4);
        String[] fieldQuery = buildHtml.getData(5);

        String addorUpdatePath = "d:/" + getPathName(pojoName) + "/jsp";
        File folder = new File(addorUpdatePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File mapperFile = new File(addorUpdatePath, "addOrUpdate.jsp");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));
        bw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n" +
                "         pageEncoding=\"UTF-8\" %>\n" +
                "<%@ include file=\"/WEB-INF/jsp/include/taglib.jsp\" %>\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/fonts/font_roboto/roboto.css\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/dhtmlx-new.css\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/custom/common.css\"/>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"${ctx}/lib/dhtmlx/codebase/custom/dhtmlx_pagebar_plugin.css\"/>\n" +
                "\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/dhtmlx.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/ext/swfobject.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/jquery/jquery-3.2.1.min.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/custom/common-select.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/dhtmlx/codebase/custom/dhtmlx_pagebar_plugin.js\"></script>\n" +
                "    <script src=\"${ctx}/lib/common/js/base.js\"></script>\n" +
                "    <style>\n" +
                "        html, body {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            margin: 0px;\n" +
                "            padding: 0px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"searchDiv\" style=\"display:none;height:130px\">这个是来调节grid的高度的，不可见</div>\n" +
                "<div id=\"tag\">\n" +
                "    <!-- 查询条件DIV -->\n" +
                "    <div id=\"savediv\">\n" +
                "        <button type=\"button\" onclick=\"save()\" class=\"btn btn-default\" style=\"margin: 5px 5px 0 5px\">保存</button>\n" +
                "    </div>\n" +
                "    <div id=\"detailQuery\">\n" +
                "        <form id=\"formid\">\n" +
                "            <table style=\"text-align:right; width:100%;height: 240px;\">\n");

        int m = 0;
        for (int i = 0; i < fieldId.length; i++) {
            m++;
            if (m == 1 || (m - 1) % 4 == 0) {
                bw.write("                <tr>\n");
            }
            if (StringUtils.isNotEmpty(fieldDict[i])) {
                bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                        "                    <td>\n" +
                        "                        <dm:select tableName=\"" + fieldDict[i] + "\" id=\"" + fieldId[i] + "\" name=\"" + fieldId[i] + "\"\n" +
                        "                                   value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\" style=\"width:162px;\">\n" +
                        "                        </dm:select>\n" +
                        "                    </td>\n");
            } else {
                if ("c".equals(fieldType[i])) {
                    bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                            "                    <td>\n" +
                            "                        <input id=\"" + fieldId[i] + "\" name=\"" + fieldId[i] + "\" type=\"text\" value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\"\n" +
                            "                               style=\"display: none\">\n" +
                            "                        <input id=\"" + fieldId[i] + "Str\" type=\"text\" value=\"${fns:getUserNameList(" + getPathName(pojoName) + "." + fieldId[i] + ")}\"\n" +
                            "                               class=\"form-control\" onclick=\"searchPeople('" + fieldId[i] + "')\" style=\"width: 150px\">\n" +
                            "                    </td>\n");
                } else {
                    bw.write("                    <td>" + fieldName[i] + "：</td>\n" +
                            "                    <td>\n" +
                            "                        <input  id=\"" + fieldId[i] + "\"  name=\"" + fieldId[i] + "\" type=\"text\"\n" +
                            "                                 class=\"form-control\" value=\"${" + getPathName(pojoName) + "." + fieldId[i] + "}\" >\n" +
                            "                    </td>\n");
                }


            }
            if (m % 4 == 0) {
                bw.write("                </tr>\n");
            }
        }


        bw.write("            </table>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "</body>\n" +
                "<style>\n" +
                "    #tag {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        height: 100%;\n" +
                "        overflow: scroll;\n" +
                "    }\n" +
                "\n" +
                "    #savediv {\n" +
                "        top: 10px;\n" +
                "        left: 20px;\n" +
                "        position: relative;\n" +
                "        width: 98%;\n" +
                "    }\n" +
                "\n" +
                "    #searchDiv {\n" +
                "        position: relative;\n" +
                "        top: 10px;\n" +
                "        left: 20px;\n" +
                "        width: 98%;\n" +
                "        height: 430px;\n" +
                "    }\n" +
                "\n" +
                "    #dataDiv {\n" +
                "        position: relative;\n" +
                "        left: 20px;\n" +
                "        width: 87%;\n" +
                "        top: 18px;\n" +
                "    }\n" +
                "\n" +
                "    #dataGrid {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        height: 280px;\n" +
                "    }" +
                "\n" +
                "</style>\n" +
                "<script>\n" +
                "    var " + getPathName(pojoName) + " = new Object();\n");
        StringBuffer sb = new StringBuffer(); //选择变量
        StringBuffer sb2 = new StringBuffer(); //选择

        StringBuffer dateVar = new StringBuffer(); //时间变量

        StringBuffer date = new StringBuffer(); //时间

        sb.append("    var ");
        dateVar.append("    var ");
        for (int i = 0; i < fieldName.length; i++) {
            if (StringUtils.isNotEmpty(fieldDict[i])) {
                sb.append(fieldId[i] + ",");
                sb2.append("        " + fieldId[i] + " = new dhtmlXComboFromSelect(\"" + fieldId[i] + "\");");
                sb2.append("\n");
            } else {
                if ("b".equals(fieldType[i])) {
                    dateVar.append(fieldId[i] + ",");
                }
                if ("b".equals(fieldType[i])) {
                    date.append("        " + fieldId[i] + " = new dhtmlXCalendarObject(\"" + fieldId[i] + "\");");
                    date.append("\n");
                }
            }
        }
        if (!"    var ".equals(sb.toString())) {
            bw.write(sb.toString().substring(0, sb.length() - 1) + ";\n");
        }
        if (!"    var ".equals(dateVar.toString())) {
            bw.write(dateVar.toString().substring(0, dateVar.length() - 1) + ";\n");
        }
        bw.write("    $(document).ready(function () {\n" +
                "        doOnLoad();\n" +
                "    });\n" +
                "\n" +
                "    var doOnLoad = function () {\n" +
                "\n");
        bw.newLine();
        bw.newLine();
        bw.write(sb2.toString());
        bw.newLine();
        bw.newLine();
        bw.write(date.toString());
        bw.write("    }\n" +
                "\n" +
                "    function closeWindow(id) {\n" +
                "        dhxWins.window(id).close();\n" +
                "    }\n" +
                "\n" +
                "    function save() {\n" +
                "        if(!checkData()){\n" +
                "            return;\n" +
                "        }\n" +
                "        $.ajax({\n" +
                "            type: \"post\",\n" +
                "            dataType: \"json\",\n" +
                "            url: '${ctx}/" + urlpath + "/" + getPathName(pojoName) + ".spr" + "?save',\n" +
                "            data: $('#formid').serialize(),\n" +
                "            success: function (data) {\n" +
                "                " + getPathName(pojoName) + " = data;\n" +
                "                $(\"#objId\").val(" + getPathName(pojoName) + ".objId);\n" +
                "                dhtmlx.alert(\"保存成功！\");\n" +
                "                window.parent.select();\n" +
                "            },\n" +
                "            error: function (data) {\n" +
                "                dhtmlx.alert(\"保存失败！\");\n" +
                "            }\n" +
                "        });\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    var checkData = function () {\n" +
                "        if($(\"#name\").val() == \"\"){\n" +
                "            dhtmlx.message(\"名称不能为空！\");\n" +
                "            $(\"#name\").focus();\n" +
                "            return false;\n" +
                "        }\n" +
                "        return true;\n" +
                "    }\n" +
                "</script>\n" +
                "</html>");

        bw.flush();
        bw.close();
    }


    /**
     * 构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildMapperXml(List<String> columns, List<String> types, List<String> comments) throws IOException {
        String xmlPath = "d:/" + getPathName(pojoName) + "/xml";
        File folder = new File(xmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(xmlPath, pojoName + "Mapper.xml");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + "" + packageName + "dao.I" + pojoName + "Dao" + "\">");
        bw.newLine();
        bw.newLine();

        buildSQL(bw, columns, types);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }


    private void buildSQL(BufferedWriter bw, List<String> columns, List<String> types) throws IOException {
        int size = columns.size();
        // 通用结果列
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        bw.write("\t\t a.obj_id as objId,");
        bw.newLine();
        for (int i = 1; i < size; i++) {
            bw.write("\t\ta." + columns.get(i) + " as " + processField(columns.get(i)));
            if (i != size - 1) {
                bw.write(",");
                bw.newLine();
            }
        }

        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

        // 查询（列表查询）
        bw.write("\t<!-- 查询（列表查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"findList\" resultType=\""
                + getPathName(pojoName) + "\" parameterType=\"Map\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName + " a");
        bw.newLine();
        bw.write("\t\t WHERE 1 = 1");
        bw.newLine();
        String tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null and " + tempField + " != '' \">");
            bw.newLine();
            bw.write("\t\t\t\tand\ta." + columns.get(i) + " = #{" + tempField + "}");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完

        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查询（根据主键ID查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"getEntityById\" resultType=\""
                + getPathName(pojoName) + "\" parameterType=\"java.lang." + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName + " a");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完


        // 删除（根据主键ID删除）
        bw.write("\t<!--删除：根据主键ID删除-->");
        bw.newLine();
        bw.write("\t<delete id=\"delete\" parameterType=\"java.lang." + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完


        // 添加insert方法
        bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"add\" parameterType=\"" + getPathName(pojoName) + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write(" \t\t(");
        for (int i = 0; i < size; i++) {
            bw.write(columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t(");
        for (int i = 0; i < size; i++) {
            bw.write("#{" + processField(columns.get(i)) + "}");
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加insert完


        //---------------  insert方法（匹配有值的字段）
        bw.write("\t<!-- 添加 （匹配有值的字段）-->");
        bw.newLine();
        bw.write("\t<insert id=\"insertSelective\" parameterType=\"" + getPathName(pojoName) + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.write("\t " + columns.get(i) + ",");
            bw.write("\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write("\t\t </trim>");
        bw.newLine();

        bw.write("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + "!=null\">");
            bw.write("\t #{" + tempField + "},");
            bw.write("\t</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        //---------------  完毕


        // 修改update方法
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"update\" parameterType=\"" + getPathName(pojoName) + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write(" \t\t <set> ");
        bw.newLine();

        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.write("\t " + columns.get(i) + " = #{" + tempField + "},");
            bw.write("\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write(" \t\t </set>");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
        // update方法完毕

        // ----- 修改（匹配有值的字段）
        bw.write("\t<!-- 修 改（匹配有值的字段）-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + getPathName(pojoName) + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write("\t\t SET ");

        bw.newLine();
        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t " + columns.get(i) + " = #{" + tempField + "}");
            if (i != size - 1) {
                bw.write(",");
            }
            bw.newLine();
        }

        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");


        bw.newLine();
        bw.newLine();


        BuildHtml buildHtml = new BuildHtml();
        String[] fieldId = buildHtml.getData(1);
        String[] fieldType = buildHtml.getData(3);
        String[] fieldQuery = buildHtml.getData(5);

        bw.write("\t<select id=\"findDataGrid\" parameterType=\"Map\" resultType=\"" + getPathName(pojoName) + "\">\n" +
                "\t\tselect\n" +
                "\t\t<include refid=\"Base_Column_List\"/>\n" +
                "\t\tfrom " + tableName + " a\n" +
                "\t\t<include refid=\"condition\"/>\n" +
                "\t\torder by a.create_time DESC\n" +
                "\t\t<if test=\"curPageNum!=null and perPageSize!=null\">\n" +
                "\t\t\tlimit #{curPageNum},#{perPageSize}\n" +
                "\t\t</if>\n" +
                "\n" +
                "\t</select>\n" +
                "\t<select id=\"countDataGrid\" parameterType=\"Map\" resultType=\"int\">\n" +
                "\t\tselect\n" +
                "\t\tcount(a.obj_id)\n" +
                "\t\tfrom " + tableName + " a\n" +
                "\t\t<include refid=\"condition\"/>\n" +
                "\n" +
                "\t</select>\n" +
                "\n" +
                "\t<sql id=\"condition\">\n" +
                "\t\twhere 1=1 and a.delete_state = 0\n");
        for (int i = 0; i < fieldId.length; i++) {
            if ("a".equals(fieldQuery[i])) {
                if ("a".equals(fieldType[i])) {
                    bw.write("\t\t<if test=\"" + fieldId[i] + " != null and " + fieldId[i] + " != ''\">\n" +
                            "\t\t  and a." + toDBString(fieldId[i]) + " like concat(concat('%',#{" + fieldId[i] + "}),'%')\n" +
                            "\t\t</if>\n");
                } else {
                    bw.write("\t\t<if test=\"" + fieldId[i] + " != null and " + fieldId[i] + " != ''\">\n" +
                            "\t\t  and\ta." + toDBString(fieldId[i]) + " = #{" + fieldId[i] + "}\n" +
                            "\t\t</if>\n");
                }
            }
        }
        bw.write("\t</sql>");

    }


    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }


    public void generate(String type) throws ClassNotFoundException, SQLException, IOException {
        init();
        String prefix = "show full fields from ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        PreparedStatement pstate = null;
        List<String> tables = getTables();
        Map<String, String> tableComments = getTableComment();
        for (String table : tables) {
            if (table.startsWith("vw") || table.startsWith("vm")) {
                System.out.println(table + "不是业务表，跳过");
            } else if (tableName.equals(table)) {
                columns = new ArrayList<String>();
                types = new ArrayList<String>();
                comments = new ArrayList<String>();
                pstate = conn.prepareStatement(prefix + table);
                ResultSet results = pstate.executeQuery();
                while (results.next()) {
                    columns.add(results.getString("FIELD"));
                    types.add(results.getString("TYPE"));
                    comments.add(results.getString("COMMENT"));
                }
                tableName = table;
                processTable(table);
                //          this.outputBaseBean();
                String tableComment = tableComments.get(tableName);
                if ("build".equals(type)) {
                    buildEntityBean(columns, types, comments, tableComment);  //生成实体po
                    buildController();//生成Controller
                    buildDao();//生成Dao
                    buileService();//生成Service接口
                    buileServiceIpml();//生成Service实现
                    buildMapperXml(columns, types, comments);//生成mybatis Mapper文件
                    buildIndexJsp();//生成主页
                    buildAddorUpdateJsp();//生成增加修改
                }
                if ("sql".equals(type)) {
                    String[] name = {"DM_ZLP_DC", "动词", "断开", "合上", "解除", "待令"};
                    String[] name2 = {"DM_ZLP_SB", "设备", "开关", "线路", "开关及线路", "开关、线路及线路PT"};
                    String[] name3 = {"DM_ZLP_ZT", "状态", "由运行转热备用", "由热备用转运行", "由运行转冷备用", "由冷备用转检修"};
                    String[] name4 = {"DM_ZLP_STATUS", "调令状态", "停电", "送电", "转电"};
                    String[] name5 = {"DM_ZLP_TYPE2", "调令类型2", "检修", "启动送电", "转电", "口头指令"};
                    List<String[]> data = new LinkedList<String[]>();
                    data.add(name);
                    data.add(name2);
                    data.add(name3);
                    data.add(name4);
                    data.add(name5);
                    buildDictSQL(data);   //生成字典的sql
                }
                if ("data".equals(type)) {
                    getFieldsExecl(columns, comments); //生成初始execl数据
                }
            }
        }
        conn.close();
    }

    /**
     * 构建字典sql
     *
     * @throws IOException
     */
    private void buildDictSQL(List<String[]> data) throws IOException {

        String daoPath = "d:/" + getPathName(pojoName) + "/dictSql";
        File folder = new File(daoPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(daoPath, "I" + pojoName + "dictSql.sql");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), "utf-8"));

        for (int i = 0; i < data.size(); i++) {
            bw.write("INSERT INTO `sys_dictionary_table` VALUES ('" + data.get(i)[0] + "', 'select * from sys_dictionary_field where table_name = \\'" + data.get(i)[0] + "\\' order by sort', 'key', 'value', '" + data.get(i)[1] + "', '0');\n");
            bw.newLine();
            for (int j = 2; j < data.get(i).length; j++) {
                bw.write("INSERT INTO `sys_dictionary_field` VALUES ('" + (j - 1) + "', '" + data.get(i)[j] + "', '0', '" + data.get(i)[0] + "', " + 10 * (j - 1) + ");\n");
            }
            bw.newLine();
        }


        bw.flush();
        bw.close();
    }

    private void getFieldsExecl(List<String> columns, List<String> comments) {
        try {
//            new BuildHtml().buildHtml();
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("学生表一");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            HSSFRow row = null;
            HSSFCell cell = null;

//            for (int i = 0; i < fieldName.length; i++) {
//                setCell(i,fieldName[i],fieldId[i],fieldDict[i],sheet, style);
//            }
            for (int i = 0; i < columns.size(); i++) {
                setCell(i, comments.get(i), processField(columns.get(i)), sheet, style);
            }

            // 第六步，将文件存到指定位置
            try {
                FileOutputStream fout = new FileOutputStream("E:/" + pojoName + ".xls");
                wb.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setCell(int i, String a, String b, HSSFSheet sheet, HSSFCellStyle style) {
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow((int) i);
        cell = row.createCell((short) 0);
        cell.setCellValue(a);
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue(b);
        cell.setCellStyle(style);
    }

//    public static void main(String[] args) {
//        try {
//            new EntityUtil().generate();
//            // 自动打开生成文件的目录
//            Runtime.getRuntime().exec("cmd /c start explorer D:\\" + new EntityUtil().getPathName(new EntityUtil().pojoName));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @param s OperationTic
     * @return operationTic
     */
    private String getPathName(String s) {
        if(s == null){
            return "";
        }
        if(s.length() < 2){
            return s;
        }
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toLowerCase() + s2;
    }

    /**
     * @param s operationTic
     * @return OperationTic
     */
    private String getPathNameUP(String s) {
        if(s == null){
            return "";
        }
        if(s.length() < 2){
            return s;
        }
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toUpperCase() + s2;
    }

    /**
     * @param fieldId sedAndEnd
     * @return sed_and_end
     */
    private String toDBString(String fieldId) {
        char[] c = fieldId.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < c.length; i++) {
            if (Character.isUpperCase(c[i])) {
                sb.append("_" + String.valueOf(c[i]).toLowerCase());
            } else {
                sb.append(String.valueOf(c[i]));
            }
        }
        return sb.toString();
    }

}