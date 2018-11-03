package com.pwz.myGenerator;

import org.junit.Test;

import java.util.Arrays;

public class A {
    class S implements Comparable<S>{
        private int a;

        public S(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "S{" +
                    "a=" + a +
                    '}';
        }

        @Override
        public int compareTo(S o) {
            if (o.a > this.a){
                return -1;
            }
            if (o.a == this.a){
                return 0;
            }
            if (o.a < this.a){
                return 1;
            }
            return 0;
        }
    }

    @Test
    public  void  www(){
        S[] a = {new S(3),new S(1),new S(4),new S(2)};
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public  void  dsf(){
        int[] a = {5,3,4,1};
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[a.length-i-1];
        }
        a = b;
        System.out.println(Arrays.toString(a));
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
