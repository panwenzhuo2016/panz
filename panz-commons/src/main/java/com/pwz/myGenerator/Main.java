package com.pwz.myGenerator;

import org.junit.Test;

/**
 * Created by pwz on 2017/9/18.
 */
public class Main {

    //第一步，生成execl数据，做少量修改
    // c列 字典
    // d列 类型 a大文本b日期 c 人
    // e列 展示字段
    // f列 查询条件
//    @Test
    public void data() throws Exception{
        EntityUtil entityUtil = new EntityUtil();
        entityUtil.generate("data");
    }
    //生成文件
//    @Test
    public void build() throws Exception{
//        EntityUtil entityUtil = new EntityUtil();
//        entityUtil.generate("build");
        EntityUtilOracel e = new EntityUtilOracel();
        e.generate("build");
    }
    //生成字典sql
//    @Test
    public void sql() throws Exception{
        EntityUtil entityUtil = new EntityUtil();
        entityUtil.generate("sql");
    }

//    @Test
    public void html() throws Exception{
        BuildHtml buildHtml = new BuildHtml();
        buildHtml.buildHtml(); //web html生成
        buildHtml.buildHtmlApp();//app html生成
        buildHtml.buildHtmlApp2();//app json生成
        buildHtml.buildHtmlApp3();//app 实体生成
    }


//    @Test
    public void htmdl() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select a.db_jieguo_tbr_guid as jieGuoGuid, b.bd_guid as biaoDuanGuid, d.gc_guid as gongChengGuid, e.xm_guid as xiangMuGuid,");
        sql.append(" h.guid as zhongBiaoUserGuid, h.qiye_bh as qiYeBianHao, a.tbr_name as zhongBiaoUserName, b.create_time as dingBiaoTime,");
        sql.append(" nvl(a.TB_BAOJIA, 0)/100 as zhongBiaoJinE, a.modify_time as YXTTime from DB_JIEGUO_TBR a");
        sql.append(" LEFT JOIN DB_JIEGUO b on a.db_jieguo_guid = b.db_jieguo_guid");
        sql.append(" LEFT JOIN ZB_GC_BD c on b.bd_guid = c.BD_GUID");
        sql.append(" LEFT JOIN ZB_GC d on c.gc_guid = d.gc_guid");
        sql.append(" LEFT JOIN ZB_XIANGMU e on c.xm_guid = e.xm_guid");
        sql.append(" LEFT JOIN VIEW_QIYE h on h.qiye_bh = a.tbr_bh");
        sql.append(" where not exists(select 1 from TONGBU_YUNFUWU_JILU x where a.db_jieguo_tbr_guid = x.yxt_guid and x.leixing = :leiXing)");
        sql.append(" and a.is_jinru_dingbiao_dw = 1");
        sql.append(" and a.zhongbiao_zhuangtai = 3 ");
        sql.append(" and a.IS_DELETED = 0 and b.IS_DELETED = 0 and c.bd_name not like '%测试测试测试%'");
        sql.append(" and d.is_plzb = 0");
        System.out.println(sql);
    }
}
