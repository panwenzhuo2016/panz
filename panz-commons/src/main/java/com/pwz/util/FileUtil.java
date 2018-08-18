package com.pwz.util;

import com.pwz.myGenerator.MakeTable.A;

import java.io.*;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/17 22:40
 **/
public class FileUtil {
    public static final String MAVEN_PATH = "src" + File.separator + "main" + File.separator + "java";
    public static final String TABLE = "table";

    public static String getThisFilePath(Class c) {
        return getBasePath(c) + File.separator + MAVEN_PATH + File.separator + packageName2Path(c.getPackage().getName() + File.separator);
    }

    public static String getTableFile(Class c) {
        File[] allFiles = getAllFiles(c);
        for (File file : allFiles) {
            if (TABLE.equals(getFileType(file))) {
                return getThisFilePath(c) + file.getName();
            }
        }
        return "";
    }

    public static String[] getTableFileContent(Class c) {
        return getFileContent(getTableFile(c));
    }

    public static File[] getAllFiles(Class c) {
        File file = new File(getThisFilePath(c));
        File[] tempList = file.listFiles();
        return tempList;
    }

    public static String getFileType(File file) {
        String type = file.getName().substring(file.getName().indexOf(".") + 1);
        return type;
    }

    public static String getBasePath(Class c) {
        String classpath = c.getClass().getResource("/").getPath();
        if (classpath.contains("/")) {
            classpath = classpath.replace("/", File.separator);
        }
        if (classpath.contains("target")) {
            return classpath.substring(0, classpath.indexOf("target"));
        }
        return classpath;
    }

    public static String packageName2Path(String packageName) {
        return packageName.replace(".", File.separator);

    }

    public static String[] getFileContent(String p) {
        StringBuffer sb = new StringBuffer("");
        try {
            // read file content from file
            FileReader reader = new FileReader(p);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = sb.toString().split("\r\n");
        return split;
    }
}
