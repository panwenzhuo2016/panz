package com.gupiao;

import com.gupiao.dao.GuPiaoMapper;
import com.gupiao.pojo.GuPiao;
import com.pwz.myGenerator.Log;
import com.pwz.util.HttpUtil;
import com.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    GuPiaoMapper guPiaoMapper;

    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime() {
    }

    //每1分钟执行一次
    @Scheduled(cron = "*/2 * *  * * * ")
    public void reportCurrentByCron() {
        Log log = new Log("gp");
        String result="";
        try {
            result = HttpUtil.getResult("http://qt.gtimg.cn/q=sz000860");
        } catch (Exception e) {
            e.printStackTrace();
        }
        GuPiao guPiao = new GuPiao();
        guPiao.setId(UuidUtil.getUuid());
        String[] g = result.split("~");
        guPiao.setMingZi(g[1]);
        guPiao.setDaiMa(String.valueOf(g[2]));
        guPiao.setDangQianJiaGe(Double.valueOf(g[3]));
        guPiao.setZuoShou(Double.valueOf(g[4]));
        guPiao.setJinKai(Double.valueOf(g[5]));
        guPiao.setChengJiaoLiangShou(Double.valueOf(g[6]));
        guPiaoMapper.insert(guPiao);
        log.info(result);
        log.write2Path();
        for (String s : result.split("~")) {
//            System.out.println(s);
        }
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

}