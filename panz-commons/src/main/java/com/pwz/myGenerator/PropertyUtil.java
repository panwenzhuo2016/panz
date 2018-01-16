package com.pwz.myGenerator;


public class PropertyUtil {
    public static void main(String[] args) {
        System.out.println(new PropertyUtil().toDBString("dsfAndMds"));
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
