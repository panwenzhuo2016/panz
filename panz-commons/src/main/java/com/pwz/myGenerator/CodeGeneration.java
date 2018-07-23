package com.pwz.myGenerator;


import com.pwz.util.DateUtil;
import com.pwz.util.ObjectUtil;

import java.io.*;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-7-23 10:47
 **/
public class CodeGeneration {
    static Log log = new Log("CodeGeneration");
    private static final String base = "D:\\zhuizhengshi\\code-core\\定时任务\\jy-jobtask\\src\\main\\java\\";

    public static void main(String[] args) {
        String s = "<alidvZhiChengPingTaiData>\n" +
                "       <expertExtractNoDaily>每日抽取专家数</expertExtractNoDaily>\n" +
                "        <bidFilesNoDaily>每日接收标书数量</bidFilesNoDaily>\n" +
                "</alidvZhiChengPingTaiData >";
        String[] ss = s.split("\n");
//        createwsBean(ss);
//        addtoService(ss);
//        addtoServiceImpl(ss);
//        addtoDao(ss);
//        addtoDaoImpl(ss);
    }

    private static void addtoServiceImpl(String[] ss) {
        String path = base + package2Path("com.zhulong.jobtask.service.impl") + "\\JobTaskJiaoYiWangTJServiceImpl.java";
        String s = readFileByLines(path);
        int i = 0;
        String className = ss[0].substring(ss[0].indexOf("<") + 1, ss[0].indexOf(">"));
        for (String s1 : s.split("\n")) {
            if (i == s.split("\n").length - 1) {
                sb.append("    @Override\n" +
                        "    public List<Map<String, Object>> queryTongBu" + ObjectUtil.up(className) + "(int rows) {\n" +
                        "        return jobTaskJiaoYiWangTJDao.queryTongBu" + ObjectUtil.up(className) + "(rows);\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public void dealTongBu" + ObjectUtil.up(className) + "(Map<String, Object> map, TongBuWebService service) {\n" +
                        "        if(map == null || map.size() <= 0){\n" +
                        "            return;\n" +
                        "        }\n" +
                        "        //获取公共参数\n" +
                        "        String yxtGuid = dealString(map.get(\"huiYiAnPaiGuid\"));\n" +
                        "        Long yxtTime = map.get(\"Modify_Time\")==null?0L:Long.parseLong(dealString(map.get(\"Modify_Time\")));\n" +
                        "        String yfwGuid = dealString(map.get(\"huiYiAnPaiGuid\"));\n" +
                        "        //组装实体\n" +
                        "        HuiYiAnPaiData bean = ObjectUtil.map2Object(HuiYiAnPaiData.class,map,true);\n" +
                        "        dealMoreInfo(bean);\n" +
                        "        //发送请求\n" +
                        "        String xml = \"\";\n" +
                        "        try {\n" +
                        "            xml = service.tongBuHuiYiAnPai(objToXml(bean));\n" +
                        "        } catch (Exception e) {\n" +
                        "            log.info(bean.toString() + e.getMessage());\n" +
                        "            e.printStackTrace();\n" +
                        "        } finally {\n" +
                        "\n" +
                        "        }\n" +
                        "        //解析返回值\n" +
                        "        if(dealResult(xml)){\n" +
                        "            //保存处理记录\n" +
                        "            jobTaskJiaoYiWangTJDao.insertTongBuJiLu(JiaoYiTongJiConstants.hui_Yi_An_Pai, yxtGuid, yxtTime, yfwGuid);\n" +
                        "        }\n" +
                        "        log.write2Path();\n" +
                        "    }\n\n");
            }
            i++;
            sb.append(s1 + "\n");
        }
        write2Path(path);
        System.out.println("生成serviceImpl 成功");
    }

    private static void addtoService(String[] ss) {
        String path = base + package2Path("com.zhulong.jobtask.service") + "\\JobTaskJiaoYiWangTJService.java";
        String s = readFileByLines(path);
        String bz = "1.10推送大屏支撑平台数据";
        int i = 0;
        String className = ss[0].substring(ss[0].indexOf("<") + 1, ss[0].indexOf(">"));
        for (String s1 : s.split("\n")) {
            if (i == s.split("\n").length - 1) {
                sb.append("\t/**\n" +
                        "\t *  " + bz + "\n" +
                        "\t * @author panwenzhuo\n" +
                        "\t * @date " + DateUtil.format(DateUtil.getToday(), DateUtil.FORMATTER_OF_DATE_AND_TIME) + "\n" +
                        "\t * @param rows\n" +
                        "\t * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>\n" +
                        "\t */\n" +
                        "\tList<Map<String,Object>> queryTongBu" + ObjectUtil.up(className) + "(int rows);\n" +
                        "\n" +
                        "\t/**\n" +
                        "\t *  " + bz + "\n" +
                        "\t * @author panwenzhuo\n" +
                        "\t * @date " + DateUtil.format(DateUtil.getToday(), DateUtil.FORMATTER_OF_DATE_AND_TIME) + "\n" +
                        "\t * @param map\n" +
                        "\t\b * @param service\n" +
                        "\t * @return void\n" +
                        "\t */\n" +
                        "\tvoid dealTongBu" + ObjectUtil.up(className) + "(Map<String, Object> map, TongBuWebService service);");
            }
            i++;
            sb.append(s1 + "\n");
        }
        write2Path(path);
        System.out.println("生成service 成功");
    }

    private static void addtoDaoImpl(String[] ss) {
        String path = base + package2Path("com.zhulong.jobtask.dao.imp") + "\\JobTaskJiaoYiWangTJDaoImpl.java";
        String s = readFileByLines(path);
        String bz = "1.10推送大屏支撑平台数据";
        int i = 0;
        String className = ss[0].substring(ss[0].indexOf("<") + 1, ss[0].indexOf(">"));
        for (String s1 : s.split("\n")) {
            if (i == s.split("\n").length - 1) {
                sb.append("\n    @Override\n" +
                        "    public List<Map<String, Object>> queryTongBu" + ObjectUtil.up(className) + "(int rows) {\n" +
                        "        StringBuffer sql = new StringBuffer();\n" +
                        "        sql.append(\"SELECT  \" +\n");
                int j = 0;
                for (String str : ss) {
                    if (j != 0 && j != ss.length - 1) {
                        String id = str.substring(str.indexOf("<") + 1, str.indexOf(">"));
                        String name = str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
                        id = id.trim();
                        sb.append("                \" a. as " + id + " \" +\n");
                    }
                    j++;
                }
                sb.append("                \" FROM ZB_HuiYi_AnPai A\" +\n" +
                        "                \"  WHERE NOT EXISTS ( SELECT 1 FROM TONGBU_YUNFUWU_JILU x WHERE A.HuiYi_AnPai_Guid = x.yxt_guid AND x.leiXing = :leiXing ) \" +\n" +
                        "                \" \");\n" +
                        "        Session session = sessionFactoryZs.getCurrentSession();\n" +
                        "        SQLQuery query = session.createSQLQuery(sql.toString())\n");
                j = 0;
                for (String str : ss) {
                    if (j != 0 && j != ss.length - 1) {
                        String id = str.substring(str.indexOf("<") + 1, str.indexOf(">"));
                        String name = str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
                        id = id.trim();
                        sb.append("                .addScalar(\"" + id + "\", StringType.INSTANCE)\n");
                    }
                    j++;
                }
                sb.append("                ;\n" +
                        "        query.setParameter(\"leiXing\", JiaoYiTongJiConstants.hui_Yi_An_Pai);\n" +
                        "        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);\n" +
                        "        query.setMaxResults(rows);\n" +
                        "        return query.list();\n" +
                        "    }\n\n");
            }
            i++;
            sb.append(s1 + "\n");
        }
        write2Path(path);
        System.out.println("生成 DaoImpl 成功");
    }

    private static void addtoDao(String[] ss) {
        String path = base + package2Path("com.zhulong.jobtask.dao") + "\\JobTaskJiaoYiWangTJDao.java";
        String s = readFileByLines(path);
        String bz = "1.10推送大屏支撑平台数据";
        int i = 0;
        String className = ss[0].substring(ss[0].indexOf("<") + 1, ss[0].indexOf(">"));
        for (String s1 : s.split("\n")) {
            if (i == s.split("\n").length - 1) {
                sb.append("\t/**\n" +
                        "\t *" + bz + "\n" +
                        "\t * @param rows\n" +
                        "\t * @return\n" +
                        "\t */\n");
                sb.append("    List<Map<String,Object>> queryTongBu" + ObjectUtil.up(className) + "(int rows);\n");
            }
            i++;
            sb.append(s1 + "\n");
        }
        write2Path(path);
        System.out.println("生成 Dao 成功");
    }


    private static void createwsBean(String[] ss) {
        final String voPath = "com.zhulong.jobtask.vo";
        log.info("1、ws实体------------------------");
        log.info("");
        String className = ss[0].substring(ss[0].indexOf("<") + 1, ss[0].indexOf(">"));
        sb.append("package " + voPath + ";\n" +
                "\n" +
                "import javax.xml.bind.annotation.XmlAccessType;\n" +
                "import javax.xml.bind.annotation.XmlAccessorType;\n" +
                "import javax.xml.bind.annotation.XmlElement;\n" +
                "import javax.xml.bind.annotation.XmlRootElement;\n\n\n" +

                "@XmlAccessorType(XmlAccessType.FIELD)\n" +
                "@XmlRootElement(name=\"" + className + "\")\n" +
                "public class " + ObjectUtil.up(className) + " {\n" +
                "\n"
        );
        int i = 0;
        for (String str : ss) {
            if (i != 0 && i != ss.length - 1) {
                String id = str.substring(str.indexOf("<") + 1, str.indexOf(">"));
                String name = str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
                id = id.trim();
                sb.append("\t/**\n" +
                        "\t * " + name + "\n" +
                        "\t */\n");
                sb.append("\t@XmlElement(name=\"" + id + "\")\n" +
                        "\tprivate String " + id + ";\n\n");
            }
            i++;
        }
        i = 0;
        for (String str : ss) {
            if (i != 0 && i != ss.length - 1) {
                String id = str.substring(str.indexOf("<") + 1, str.indexOf(">"));
                String name = str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
                id = id.trim();
                sb.append("\n\tpublic String get" + ObjectUtil.up(id) + "() {\n" +
                        "\t\treturn " + id + ";\n" +
                        "\t}\n" +
                        "\n" +
                        "\tpublic void set" + ObjectUtil.up(id) + "(String " + id + ") {\n" +
                        "\t\tthis." + id + " = " + id + ";\n" +
                        "\t}\n");
            }
            i++;
        }

        sb.append("}\n");
        log.info(sb.toString());
        log.info("");
        log.write2Path();
        write2Path(base + package2Path(voPath) + "\\" + ObjectUtil.up(className) + ".java");
        System.out.println("ws实体 生成成功");

    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    private static StringBuffer sb = new StringBuffer();

    public static boolean write2Path(String path) {
        try {
            clearInfoForFile(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), true));
            writer.write(sb.toString());
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            sb.setLength(0);
        }
    }

    public static String package2Path(String packageName) {
        return packageName.replace(".", "\\");
    }

    /**
     * 清空文件内容
     *
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
