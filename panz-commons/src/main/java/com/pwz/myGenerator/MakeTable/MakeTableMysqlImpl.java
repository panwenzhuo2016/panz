package com.pwz.myGenerator.MakeTable;

import com.pwz.StringUtil;
import com.pwz.util.PinYiUtil;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/17 22:15
 **/
public class MakeTableMysqlImpl implements MakeTable {
    @Override
    public void print(String[] fileContent) {
        String table = "CREATE TABLE `gu_piao` (\n" +
                "  `id` char(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" ;
        for (String s : fileContent) {
            String pinyinArray = s.substring(s.indexOf(":") + 1).trim();
            String pinyinArray2 = PinYiUtil.getSqlStringPinYin(pinyinArray);
//            System.out.println("private String " + pinyinArray.substring(0,pinyinArray.length() -1).toLowerCase() + ";");
            String pr = pinyinArray2.substring(0,pinyinArray2.length() -1).toLowerCase();
            table += " `"+pr+"` int(11) DEFAULT NULL   COMMENT '"+pinyinArray+"',\n";
        }
        table += Constants.BASE;
        table +=   "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='股票';";
        System.out.println(table);
    }
}
