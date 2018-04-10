package com.pwz.myclass;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map concurrentHashMap = new java.util.concurrent.ConcurrentHashMap(64);
        concurrentHashMap.put("s","w");
        concurrentHashMap.put("e","e");
        concurrentHashMap.put("t","r");
        System.out.println(concurrentHashMap);

        StringBuilder sql = new StringBuilder();
        sql.append("select    xmbh as xiangMuGuid, xmmc as xiangMuName, xmbh as xiangMuBianHao, " +
                "(DATEDIFF(S,'1970-01-01 00:00:00', createtime) - 8 * 3600)*convert(bigint, 1000) as faBuTime , " +
                " zgbm as beiAnZhuGuan ," +
                " xmztz*10000 as touZiZongJinE, " +
                " isBig as isZhongDaXM \n" +
                "from JY_WW_XMQR\n" +
                "WHERE isnull(RepeatNo, 0) = 0 AND XMMC NOT LIKE '%测试%' AND XMBH NOT IN('11111120120001')" +
                "  ");
        System.out.println(sql);

        Map dsf = new LinkedHashMap();
        dsf.put("w","wer");
        dsf.put("a","wer");
        dsf.put("s","wer");
        dsf.put("d","we44r");
        dsf.put("g",34);
        System.out.println(dsf);

        Long d ;
        int s =5 ;
        if(s - 4 > 0){
            d = System.currentTimeMillis();
//            Long sd = d;
//            System.out.println(sd);
            System.out.println(d);
        }

        String qiYeXingZhi = "234";
        switch (qiYeXingZhi) {
            case "1":
                System.out.println("10");
                break;
            case "2":
                System.out.println("20");
                break;
            case "3":
                System.out.println("30");
                break;
            case "4":
                System.out.println("40");
                break;
            case "5":
                System.out.println("50");
                break;
            case "6":
                System.out.println("60");
                break;
            default:
                System.out.println("90");
                break;
        }
        System.out.println("");
        Long sdf = 1517565504065L;
        System.out.println(new Date(sdf));
    }
}

