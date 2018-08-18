package com.pwz.myGenerator;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;

public class BYClass {
    public static void main(String[] args) throws Exception {

        File dir = new File("D:\\idea\\entity\\src\\main\\java\\com\\zhulong\\jiaoyi\\entity");
        File[] dirfiles = dir.listFiles();
        for (File file : dirfiles) {
            String className = file.getName().substring(0, file.getName().length() - 5);
            Class aClass = Thread.currentThread().getContextClassLoader().loadClass("com.zhulong.jiaoyi.entity" + '.' + className);

            Table table = (Table) aClass.getAnnotation(Table.class);

            if(table != null){
                String poPath = "d:/" + "" + "po2";
                File folder = new File(poPath);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                File beanFile = new File(poPath, table.name() + ".java");
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile), "utf-8"));

                bw.write("package com.zhulong.jiaoyi.webservice.entity.allData;\n" +
                        "\n" +
                        "import javax.xml.bind.annotation.XmlAccessType;\n" +
                        "import javax.xml.bind.annotation.XmlAccessorType;\n" +
                        "import javax.xml.bind.annotation.XmlElement;\n" +
                        "import javax.xml.bind.annotation.XmlRootElement;\n" +
                        "\n" +
                        "@XmlAccessorType(XmlAccessType.FIELD)\n" +
                        "@XmlRootElement(name=\""+table.name()+"\")\n" +
                        "public class "+table.name()+" {\n");


                Field[] fields = aClass.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    // 字段名
                    Column c = fields[j].getAnnotation(Column.class);
                    if (c != null) {
                        String fn = fields[j].getName();
                        String sqln = c.name();
                        bw.write("\n" +
                                "    @XmlElement(name=\"" + sqln + "\")\n" +
                                "    private String " + fn + "; ");
                    }
                }

                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    // 字段名
                    Column c = fields[j].getAnnotation(Column.class);
                    if (c != null) {
                        String fn = fields[j].getName();
                        bw.write(get(fn));
                        bw.write(set(fn));
                    }
                }
                bw.write("}\n");

                bw.flush();
                bw.close();
            }
        }
    }

    private static String get(String str) {
        return " \n    public String get" + getPathNameUP(str) + "() {\n" +
                "        return " + str + ";\n" +
                "    }";
    }

    private static String set(String str) {
        return "\n" +
                "    public void set" + getPathNameUP(str) + "(String " + str + ") {\n" +
                "        this." + str + " = " + str + ";\n" +
                "    }";
    }

    /**
     * @param s OperationTic
     * @return operationTic
     */
    private static String getPathName(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2) {
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
    private static String getPathNameUP(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toUpperCase() + s2;
    }
}
