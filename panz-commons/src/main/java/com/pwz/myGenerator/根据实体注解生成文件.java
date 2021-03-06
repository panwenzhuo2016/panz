package com.pwz.myGenerator;

import com.pwz.myGenerator.B;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;

public class 根据实体注解生成文件{
    public static void main(String[] args) throws Exception {

        File dir = new File("D:\\idea\\entity\\src\\main\\java\\com\\zhulong\\jiaoyi\\entity");
        File[] dirfiles = dir.listFiles();

        File beanFile2 = new File("d:/" + "" + "po4", "code" + ".txt");
        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile2), "utf-8"));

        for (File file : dirfiles) {
            String className = file.getName().substring(0, file.getName().length() - 5);
            Class aClass = Thread.currentThread().getContextClassLoader().loadClass("com.zhulong.jiaoyi.entity" + '.' + className);


            Table table = (Table) aClass.getAnnotation(Table.class);

            if(table != null){
                bw2.write("\n\n");
                B.main2(bw2,table.name(),className);

                Field[] fields = aClass.getDeclaredFields();

                boolean a = false;
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    // 字段名
                    JoinColumn c = fields[j].getAnnotation(JoinColumn.class);
                    if (c != null) {
                        a = true;
                    }
                }
                if(a){

                    System.out.println("    @XStreamAlias(\""+table.name()+"\")\n" +
                            "    private "+table.name()+" "+getPathName(table.name())+";");

                    String poPath = "d:/" + "" + "po3";
                    File folder = new File(poPath);
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    File beanFile = new File(poPath, table.name() + ".java");
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile), "utf-8"));

                    bw.write("package com.zhulong.jiaoyi.webservice.entity.allData;\n" +
                            "\n" +
                            "import com.thoughtworks.xstream.annotations.XStreamAlias;\n" +
                            "import java.sql.Clob;\n" +
                            "import java.util.Date;" +
                            "\n" +
                            "@XStreamAlias(\""+table.name()+"\")\n" +
                            "public class "+table.name()+" {\n");



                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        // 字段名
                        JoinColumn c = fields[j].getAnnotation(JoinColumn.class);
                        if (c != null) {
                            String fn = fields[j].getName();
                            String sqln = c.name();
                            bw.write("\n" +
                                    "    @XStreamAlias(\"" + sqln + "\")\n" +
                                    "    private "+fields[j].getType().getSimpleName()+" " + fn + "; ");
                        }
                    }

                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        // 字段名
                        JoinColumn c = fields[j].getAnnotation(JoinColumn.class);
                        if (c != null) {
                            String fn = fields[j].getName();
                            bw.write(get(fn,fields[j].getType().getSimpleName()));
                            bw.write(set(fn,fields[j].getType().getSimpleName()));
                        }
                    }
                    bw.write("}\n");

                    bw.flush();
                    bw.close();
                }

            }
        }
        bw2.flush();
        bw2.close();
    }

    private static void co(Table table,Field[] fields) throws Exception {


    }

    private static String get(String str,String type) {
        return " \n    public "+type+" get" + getPathNameUP(str) + "() {\n" +
                "        return " + str + ";\n" +
                "    }";
    }

    private static String set(String str,String type) {
        return "\n" +
                "    public void set" + getPathNameUP(str) + "("+type+" " + str + ") {\n" +
                "        this." + str + " = " + str + ";\n" +
                "    }";
    }

    /**
     * @param s OperationTic
     * @return operationTic
     */
    public static String getPathName(String s) {
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
    public static String getPathNameUP(String s) {
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
