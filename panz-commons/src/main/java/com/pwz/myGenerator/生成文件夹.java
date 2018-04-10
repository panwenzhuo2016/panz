//package com.pwz.myGenerator;
//
//import org.springframework.util.AlternativeJdkIdGenerator;
//import org.springframework.util.AntPathMatcher;
//
//import java.io.File;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class 生成文件夹 {
//    public static void main2(String[] args) {
//        Map<Object,Object> sdf = new HashMap<Object, Object>();
//        sdf.put("23","sdf");
//        sdf.put("213","s3df");
//        sdf.keySet();
//        System.out.println(Integer.parseInt("3"));
//
//        Collection<Object> values = sdf.values();
//        Iterator<Object> iterator = values.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//
//        System.out.println("asdf".codePointAt(0));
//        System.out.println((char)98);
//        char dsf = 's';
//        System.out.println((int)dsf);
//        for (int i = 0; i < 128; i++) {
//            System.out.print((char) i);
//        }
//        System.out.println();
//        AlternativeJdkIdGenerator a = new AlternativeJdkIdGenerator();
//        System.out.println(a.generateId());
//        AntPathMatcher as = new AntPathMatcher();
//        System.out.println(as.match("/*","/sdf"));
//    }
//
//    public static void main(String[] args) {
//        String[] y = new String[10];
//        String[] w = new String[5];
//        String d = "C:\\Users\\Bruin\\Desktop\\p\\2018\\";
//        for (int i = 0; i < 10; i++) {
//            y[i] = d + (i + 3) + "月";
//        }
//        for (int i = 0; i < 5; i++) {
//            w[i] = (i + 1) + "周";
//        }
//
//        for (int i = 0; i < y.length; i++) {
//            for (int j = 0; j < w.length; j++) {
//                File file1 = new File( y[i] + "\\" + w[j]);
//                if (file1.mkdirs()) {
//                    System.out.println(file1.getPath());
//                }
//            }
//        }
//    }
//}
