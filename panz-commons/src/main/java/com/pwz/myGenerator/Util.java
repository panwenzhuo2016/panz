package com.pwz.myGenerator;

public class Util {


    public static String getPathNameLow(String s) {
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toLowerCase() + s2;
    }

    public static String getPathNameUP(String s) {
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1, s.length());
        return s1.toUpperCase() + s2;
    }
}
