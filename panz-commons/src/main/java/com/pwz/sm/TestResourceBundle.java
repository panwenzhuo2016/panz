package com.pwz.sm;

import java.util.ResourceBundle;

public class TestResourceBundle {  
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
        System.out.println(rb.getString("dsdsfsdf"));
    }  
}  