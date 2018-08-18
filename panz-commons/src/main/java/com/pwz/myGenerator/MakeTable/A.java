package com.pwz.myGenerator.MakeTable;

import com.pwz.StringUtil;
import com.pwz.util.FileUtil;
import com.pwz.util.PinYiUtil;

import java.io.File;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/17 22:15
 **/
public class A {
    public static void main(String[] args) {

        String[] fileContent = FileUtil.getTableFileContent(A.class);
        MakeTable makeTable = MakeTableFactory.getMakeTableMysqlImpl();
        makeTable.print(fileContent);
    }
}
