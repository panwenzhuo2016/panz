package com.pwz.myGenerator;

public class UUID {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(java.util.UUID.randomUUID().toString());
        }
    }
    public static String get(){
        return java.util.UUID.randomUUID().toString().replace("-","");
    }
}
