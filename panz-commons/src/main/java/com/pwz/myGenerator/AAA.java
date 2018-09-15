package com.pwz.myGenerator;

import com.pwz.util.DateUtil;
import com.pwz.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author panwenzhuo
 * @date 2018/8/7 15:54
 */
public class AAA {

    @Test
    public void dddf() {
        List<String> df = new ArrayList<>(8);
        df.add("34");
        df.add("234");
        df.add("234");
        df.add("245");
        df.add("3465");
        df.add("5674567");
        df.add("af");
        df.add("er");
        df.add("gf");
        df.add("rt");
        df.add("sdfdsfsdf");
        df.add("sdfdsfsdf2");
        System.out.println(df);

    }

    @Test
    public void main() {
        String s = "1\t深圳市龙华区建筑工务局\t3\t100350.14\n"
                + "2\t深圳市建筑工务署工程管理中心\t4\t30858.74\n"
                + "3\t深圳市土地投资开发中心\t1\t27885.94\n"
                + "4\t深圳市前海开发投资控股有限公司\t8\t2742.61\n"
                + "5\t深圳市水务工程建设管理中心\t2\t153.7\n"
                + "6\t深圳市交通公用设施建设中心\t16\t595104.4\n"
                + "7\t深圳市地铁集团有限公司\t5\t114557.59\n"
                + "8\t深圳市住宅工程管理站\t1\t6330\n"
                + "9\t深圳市交通公用设施管理局\t4\t9943.65\n";
        for (String s1 : s.split("\n")) {
            System.out.println(s1.substring(2, s1.indexOf("\t", 2)));
        }
    }

    public static void main(String[] args) {
        String[] tbr_name = FileUtil.getFileContent("G:\\zhulong\\zhuzhengshi\\new 3.txt");
        String[] create_time = FileUtil.getFileContent("G:\\zhulong\\zhuzhengshi\\new 4.txt");

        for (int i = 0; i < tbr_name.length; i++) {
            System.out.println("update  ZB_HuiYi_AnPai set is_jieshu = 1, HuiYi_EndTime = "+ DateUtil.parse(create_time[i],"yyyy-MM-dd HH:mm").getTime()+" where huiyi_name like '"+tbr_name[i]+"' and (HuiYi_LeiXing_name_str like '%开%' or huiyi_leixing = 1) and is_deleted = 0 ");
        }
    }
    public static void main2(String[] args) {
        String[] tbr_name = FileUtil.getFileContent("G:\\zhulong\\zhuzhengshi\\new 3.txt");
        String[] create_time = FileUtil.getFileContent("G:\\zhulong\\zhuzhengshi\\new 4.txt");
        String[] bdguid = FileUtil.getFileContent("G:\\zhulong\\zhuzhengshi\\new 5.txt");

        List<String> name = new ArrayList<>();
        List<String> bdguids = new ArrayList<>();
        for (int i = 0; i < tbr_name.length; i++) {
            if (name.contains(tbr_name[i]) ) {
                if(bdguids.contains(bdguid[i])){
                    if (StringUtils.isNotBlank(create_time[i])) {
                        String ss = "update KB_JIEGUO set is_deleted = 1  where bd_guid = '" + bdguid[i] + "' and  tbr_name = '" + tbr_name[i] + "' and create_time = " + create_time[i] + "";
                        System.out.println(ss);
                    } else {
                        String ss = "update KB_JIEGUO set is_deleted = 1  where bd_guid = '" + bdguid[i] + "' and  tbr_name = '" + tbr_name[i] + "' and create_time is null ";
                        System.out.println(ss);
                    }
                }
                if(!bdguids.contains(bdguid[i])){
                    bdguids.add(bdguid[i]);
                }
            }
            if (!name.contains(tbr_name[i]) ) {
                bdguids.clear();
                name.add(tbr_name[i]);
                bdguids.add(bdguid[i]);
            }

        }


    }



}
