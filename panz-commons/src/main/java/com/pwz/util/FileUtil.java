package com.pwz.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static String[] getFileContent(String path) {
        StringBuffer sb = new StringBuffer("");
        try {
            // read file content from file
            FileReader reader = new FileReader(path);
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

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory, boolean nameorpathname) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(nameorpathname ? file.getName() : file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory, nameorpathname));
            } else {
                list.add(nameorpathname ? file.getName() : file.getAbsolutePath());
            }
        }
        return list;
    }
}
