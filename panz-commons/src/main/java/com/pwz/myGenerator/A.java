package com.pwz.myGenerator;

public class A {

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
