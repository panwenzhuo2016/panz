package com.pwz.gupiao;

import com.pwz.gupiao.dao.GuPiaoMapper;
import com.pwz.gupiao.pojo.GuPiao;
import com.pwz.util.HttpUtil;
import com.pwz.util.NumUtil;
import com.pwz.util.UuidUtil;
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

    //    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime() {
    }

    public static void main(String[] args) {
        ScheduledTasks scheduledTasks = new ScheduledTasks();
        scheduledTasks.reportCurrentByCron();
//        for (int i = 7; i < 53; i++) {
//            System.out.println("            guPiao.setZuiGao(Double.valueOf(g["+i+"]));");
//        }
    }

    //每1分钟执行一次
//    @Scheduled(cron = "0/5 * * * * * ")
//    @Transactional(rollbackFor = Exception.class)
    public void reportCurrentByCron() {
        sz("sz");
        sz("sh");
    }

    private void sz(String sz) {
        for (int i = 601605; i < 601607; i++) {
//            Log log = new Log("gp");

            String result = "";
            try {
                result = HttpUtil.getResult("http://qt.gtimg.cn/q="+sz+"" + NumUtil.numFormat(6, i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result.startsWith("v_pv_none_match")) {
                continue;

            }
//            for (String s : result.split("~")) {
//                System.out.println(s);
//            }
            GuPiao guPiao = new GuPiao();
            guPiao.setId(UuidUtil.getUuid());
            String[] g = result.split("~");
            guPiao.setMingZi(g[1]);
            guPiao.setDaiMa(String.valueOf(g[2]));
            guPiao.setDangQianJiaGe(valueOf(g[3]));
            guPiao.setZuoShou(valueOf(g[4]));
            guPiao.setJinKai(valueOf(g[5]));
//            guPiao.setChengJiaoLiangShou(valueOf(g[6]));

//            guPiao.setZuiGao(valueOf(g[7]));
//            guPiao.setZuiGao(valueOf(g[8]));
//            guPiao.setZuiGao(valueOf(g[9]));
//            guPiao.setZuiGao(valueOf(g[10]));
//            guPiao.setZuiGao(valueOf(g[11]));
//            guPiao.setZuiGao(valueOf(g[12]));
//            guPiao.setZuiGao(valueOf(g[13]));
//            guPiao.setZuiGao(valueOf(g[14]));
//            guPiao.setZuiGao(valueOf(g[15]));
//            guPiao.setZuiGao(valueOf(g[16]));
//            guPiao.setZuiGao(valueOf(g[17]));
//            guPiao.setZuiGao(valueOf(g[18]));
//            guPiao.setZuiGao(valueOf(g[19]));
//            guPiao.setZuiGao(valueOf(g[20]));
//            guPiao.setZuiGao(valueOf(g[21]));
//            guPiao.setZuiGao(valueOf(g[22]));
//            guPiao.setZuiGao(valueOf(g[23]));
//            guPiao.setZuiGao(valueOf(g[24]));
//            guPiao.setZuiGao(valueOf(g[25]));
//            guPiao.setZuiGao(valueOf(g[26]));
//            guPiao.setZuiGao(valueOf(g[27]));
//            guPiao.setZuiGao(valueOf(g[28]));
////            guPiao.setZuiGao(valueOf(g[29]));
//            guPiao.setZuiGao(valueOf(g[30]));
//            guPiao.setZuiGao(valueOf(g[31]));
//            guPiao.setZuiGao(valueOf(g[32]));
//            guPiao.setZuiGao(valueOf(g[33]));
//            guPiao.setZuiGao(valueOf(g[34]));
////            guPiao.setZuiGao(valueOf(g[35]));
//            guPiao.setZuiGao(valueOf(g[36]));
//            guPiao.setZuiGao(valueOf(g[37]));
//            guPiao.setZuiGao(valueOf(g[38]));
//            guPiao.setZuiGao(valueOf(g[39]));
//            guPiao.setZuiGao(valueOf(g[40]));

            guPiao.setZuiGao(valueOf(g[41]));
            guPiao.setZuiDi(valueOf(g[42]));
            guPiao.setZhenFu(valueOf(g[43]));
            guPiao.setLiuTongShiZhi(valueOf(g[44]));
            guPiao.setZongShiZhi(valueOf(g[45]));
            guPiao.setShiJingLu(valueOf(g[46]));
            guPiao.setZhangTingJia(valueOf(g[47]));
            guPiao.setDieTingJia(valueOf(g[48]));
//            guPiao.setZuiGao(valueOf(g[49]));
//            guPiao.setZuiGao(valueOf(g[50]));
//            guPiao.setZuiGao(valueOf(g[51]));
//            guPiao.setZuiGao(valueOf(g[52]));

            guPiao.setCreateTime(new Date());
            guPiao.setModifyTime(new Date());
//            DataSource dataSource = SpringUtil.getDataSource();
            try {
//                final Connection connection = dataSource.getConnection();
//                connection.setAutoCommit(true);
                guPiaoMapper.insert(guPiao);
            } catch (Exception e) {
                e.printStackTrace();
            }



//            guPiao = new GuPiao();
//            guPiao.setJinKai(Double.valueOf("dfsaasdf"));
//            guPiaoMapper.insert(guPiao);
//            log.info(result);
//            log.write2Path();
//            for (String s : result.split("~")) {
////            System.out.println(s);
//            }
        }
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    private Double valueOf(String f){
        try {
            return  Double.valueOf(f);
        } catch (Exception e){
            return Double.valueOf(0L);
        }
    }

}