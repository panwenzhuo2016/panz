package com.pwz.util;

import com.pwz.gupiao.pojo.GuPiao;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/17 12:46
 **/
public class GP {
    public static void main(String[] args) {

        String result="";
        try {
             result = HttpUtil.getResult("http://qt.gtimg.cn/q=sz000858");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] g = result.split("~");


        GuPiao guPiao = new GuPiao();
        guPiao.setMingZi(g[1]);
        guPiao.setDaiMa(String.valueOf(g[2]));
        guPiao.setDangQianJiaGe(Double.valueOf(g[3]));
        guPiao.setZuoShou(Double.valueOf(g[4]));
        guPiao.setJinKai(Double.valueOf(g[5]));
        guPiao.setChengJiaoLiangShou(Double.valueOf(g[6]));
    }
}
