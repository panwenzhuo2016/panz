package com.pwz.file;

public class DownLoadTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        String filepath = "http://localhost:8086/jy-zhaobiao/wwww";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,4);    
        load.downloadPart();    
    }
}