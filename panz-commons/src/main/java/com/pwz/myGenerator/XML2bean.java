package com.pwz.myGenerator;

import javax.xml.bind.annotation.XmlElement;

public class XML2bean {
    public static void main(String[] args) {
        String s = "  < huiYiAnPaiGuid >会议安排guid</ huiYiAnPaiGuid >\n" +
                "< huiYiName >会议名称</ huiYiName >\n" +
                "   < huiYiBDBH >会议标段编号</ huiYiBDBH >\n" +
                "< huiYiDiDianGuid >会议地点guid</ huiYiDiDianGuid >\n" +
                "< huiYiDiDianName >会议地点名称</ huiYiDiDianName >\n" +
                "< huiYiStartTime >会议开始时间</ huiYiStartTime >\n" +
                "< huiYiEndTime >会议结束时间</ huiYiEndTime >\n" +
                "< yuJiRenShu >预计人数</ yuJiRenShu >\n" +
                "< yuHuiZheName >与会者名称</ yuHuiZheName >\n" +
                "< isDeleted >是否删除</ isDeleted >\n" +
                "< isLinShiHuiYi >是否临时会议</ isLinShiHuiYi >\n" +
                "< isDuiWaiGongShi >是否对外公示</ isDuiWaiGongShi >\n" +
                "< yuHuiZheGuids>与会者guids</ yuHuiZheGuids >\n" +
                "< isChaoShi>是否超时</ isChaoShi >\n" +
                "< isJieShu>是否结束</ isJieShu >\n" +
                "< huiYiLeiXing>本次会议类型</ huiYiLeiXing >\n" +
                "< isWangShangHuiYi>是否网上会议</ isWangShangHuiYi >\n" +
                "< isLiuBiao>是否流标</ isLiuBiao >\n" +
                "< isFuWuAdd>是否增加服务</ isFuWuAdd >\n" +
                "<gclxs>工程类型</gclxs>\n" +
                "< bdsl>会议标段数</ bdsl >\n" +
                "< hysd>会议时段</ hysd >\n" +
                "< isYuanZhuanJiaFuYi>是否原专家复议</ isYuanZhuanJiaFuYi >";
        String[] ss = s.split(">\\n");

        for (String str : ss){
            String id = str.substring(str.indexOf("<") + 1,str.indexOf(">"));
//			System.out.println(id);
            String name = str.substring(str.indexOf(">") +1,str.lastIndexOf("<"));
//			System.out.println(name);
//            System.out.println("ztbReportFSGXMLInfo.set"+id.replaceAll(" ","")+"(dealString(gc.get(\""+id.replaceAll(" ","" )+"\")));");
            System.out.println(".addScalar(\""+del(id)+"\",StringType.INSTANCE)");
//            System.out.println("\t\" null as "+del(id)+", \" +");
//            System.out.println("ztbReportFSGXMLInfo.set"+id.replaceAll(" ","")+"(dealString(null));");
//            System.out.println("    \n         /**\n" +
//                    "         * "+name+"\n" +
//                    "         */\n" +
//                    "        @XmlElement(name=\""+del(id)+"\")\n" +
//                    "        private String "+del(id)+";");
//            System.out.println("\tbean.set"+Util.getPathNameUP(del(id))+"(dealString(map.get(\""+del(id)+"\")));");
        }
//        createwsBean(ss);

//          html();
//         yemian();

//        sql();

    }

    private static void createwsBean(String[] ss) {
        System.out.println("package com.zhulong.jobtask.vo;\n" +
                "\n" +
                "import javax.xml.bind.annotation.XmlAccessType;\n" +
                "import javax.xml.bind.annotation.XmlAccessorType;\n" +
                "import javax.xml.bind.annotation.XmlElement;\n" +
                "import javax.xml.bind.annotation.XmlRootElement;\n" +

                "@XmlAccessorType(XmlAccessType.FIELD)\n" +
                "@XmlRootElement(name=\"huiYiAnPaiData\")\n" +
                "public class HuiYiAnPaiData {\n" +
                "\n"
        );
        for (String str : ss) {
            String id = str.substring(str.indexOf("<") + 1,str.indexOf(">"));
            id = id.trim();
            System.out.println(   "\t@XmlElement(name=\""+id+"\")\n" +
                    "\tprivate String "+id+";\n" );
        }

                System.out.println(   "\t}\n" +
                "}\n");
    }

    private static void sql() {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.db_jieguo_tbr_guid as jieGuoGuid, b.bd_guid as biaoDuanGuid, d.gc_guid as gongChengGuid, e.xm_guid as xiangMuGuid,");
        sql.append(" h.guid as zhongBiaoUserGuid, h.qiye_bh as qiYeBianHao, a.tbr_name as zhongBiaoUserName, b.create_time as dingBiaoTime,");
        sql.append(" nvl(a.TB_BAOJIA, 0)/100 as zhongBiaoJinE, a.modify_time as YXTTime, " +
                " a.gongQi as gongQi, " +
                " gs.GongShi_StartTime as zhongBiaoGongShiStartTime," +
                " gs.GongShi_EndTime as zhongBiaoGongShiEndTime," +
                " tzs.Create_time as zhongBiaoTongZhiShuCreateTime," +
                " gs.printTime as zhongBiaoGongShiPrintTime" +
                " from DB_JIEGUO_TBR a");
        sql.append(" LEFT JOIN DB_JIEGUO b on a.db_jieguo_guid = b.db_jieguo_guid");
        sql.append(" LEFT JOIN ZB_GC_BD c on b.bd_guid = c.BD_GUID");
        sql.append(" LEFT JOIN DB_ZhongBiao_JieGuo gs on a.GG_BD_GUID = gs.GGbd_Guid AND gs.IS_DELETED = 0 ");
        sql.append(" LEFT JOIN DB_ZhongBiao_JieGuo_TongZhi tzs on a.GG_BD_GUID = tzs.GG_BD_Guid AND tzs.IS_DELETED = 0 ");
        sql.append(" LEFT JOIN ZB_GC d on c.gc_guid = d.gc_guid");
        sql.append(" LEFT JOIN ZB_XIANGMU e on c.xm_guid = e.xm_guid");
        sql.append(" LEFT JOIN VIEW_QIYE h on h.qiye_bh = a.tbr_bh");
        sql.append(" where not exists(select 1 from TONGBU_YUNFUWU_JILU x where a.db_jieguo_tbr_guid = x.yxt_guid and x.leixing = :leiXing)");
        sql.append(" and a.is_jinru_dingbiao_dw = '1'");
        sql.append(" and a.zhongbiao_zhuangtai = '3' ");
        sql.append(" and a.IS_DELETED = '0' and b.IS_DELETED = '0' and c.bd_name not like '%测试测试测试%'");
        sql.append(" and d.is_plzb = '0' ");

        System.out.println(sql.toString());
    }

    private static String yemian() {
        String sdf = "   para = doc.createParagraph();set12ContentAfter20(black(beforeP-1)+\"  1、开标情况报告\",para,run,doc);set12UnderLine(print.getData1(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(14),para,run,doc);\n" +
                "        set12ContentAfter(\"  6、监督小组成员签到表\",para,run,doc);set12UnderLine(print.getData2(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP-1)+\"  2、开标情况记录表\",para,run,doc);set12UnderLine(print.getData3(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(12),para,run,doc);\n" +
                "        set12ContentAfter(\"  7、监督小组名单\",para,run,doc);set12UnderLine(print.getData4(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP-1)+\"  3、资格后审报告\",para,run,doc);set12UnderLine(print.getData5(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(14),para,run,doc);\n" +
                "        set12ContentAfter(\"  8、个人投票表（原件）\",para,run,doc);set12UnderLine(print.getData6(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP-1)+\"  4、定标委员会正式名单\",para,run,doc);set12UnderLine(print.getData7(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(8),para,run,doc);\n" +
                "        set12ContentAfter(\"  9、投票表\",para,run,doc);set12UnderLine(print.getData8(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP-1)+\"  5、定标委员会成员签到表\",para,run,doc);set12UnderLine(print.getData9(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(6),para,run,doc);\n" +
                "        set12ContentAfter(\"  10、投票结果汇总表\",para,run,doc);set12UnderLine(print.getData10(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP-1)+\"  11、其他：\",para,run,doc);set12UnderLine(dealString(print.getData11(),29)+\"　\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20JC(\"　  \",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20JC(black(beforeP)+\"　工作人员:  \",para,run,doc);set12ContentAfter(print.getGzry(),para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20JC(black(beforeP)+\"　\" + getDate(print.getGzrydatan(),print.getGzrydatay(),print.getGzrydatar()),para,run,doc);\n" +
                "        para = doc.crea份数/页数teParagraph();\n" +
                "\t\tset12ContentAfter20JC(black(beforeP)+\"  定标资料清单：\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP)+\"  1、定标委员会正式名单\",para,run,doc);set12UnderLine(print.getData12(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(8),para,run,doc);\n" +
                "        set12ContentAfter(\"  6、个人投票表\",para,run,doc);set12UnderLine(print.getData13(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP)+\"  2、定标委员会成员签到表\",para,run,doc);set12UnderLine(print.getData14(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(6),para,run,doc);\n" +
                "        set12ContentAfter(\"  7、投票表\",para,run,doc);set12UnderLine(print.getData15(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP)+\"  3、监督小组名单\",para,run,doc);set12UnderLine(print.getData16(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(14),para,run,doc);\n" +
                "        set12ContentAfter(\"  8、定标结果表\",para,run,doc);set12UnderLine(print.getData17(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP)+\"  4、监督小组成员签到表\",para,run,doc);set12UnderLine(print.getData18(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(8),para,run,doc);\n" +
                "        set12ContentAfter(\"  9、定标报告\",para,run,doc);set12UnderLine(print.getData19(),para,run,doc);set12ContentAfter(\"份数/页数\",para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP)+\"  5、投票汇总表\",para,run,doc);set12UnderLine(print.getData20(),para,run,doc);set12ContentAfter(\"份数/页数\"+ black(16),para,run,doc);\n" +
                "        para = doc.createParagraph();set12ContentAfter20(black(beforeP)+\"  10、其他：\",para,run,doc);set12UnderLine(dealString(print.getData21(),29)+\"　\",para,run,doc);";
        for(int i = 1; i < 21; i++){

            if(sdf.contains("份数/页数")){
                sdf = sdf.replaceFirst("份数/页数","份\",para,run,doc);set12UnderLine(print.getPage"+i+"(),para,run,doc);set12ContentAfter(\"页");
            }
        }
        return sdf;
    }

    private static int html() {
        int n = 21;
        for(int i = 1; i < n; i++){
            System.out.println(" $(\"#page"+i+"\").val(data.zbHuiYiAnPaiPrint.page"+i+");");
        }
        System.out.println();
        for(int i = 1; i < n; i++){
            System.out.println("   url += '&page"+i+"=' + $(\"#page"+i+"\").val();");
        }
        System.out.println();
        for(int i = 1; i < n; i++){
            System.out.println("  data.page"+i+" = $(\"#page"+i+"\").val();");
        }
        System.out.println();
        for(int i = 1; i < n; i++){
            System.out.println();
            System.out.println("\t@Column(name = \"page"+i+"\")\n" +
                    "\t@LogField(name = \"页数"+i+"\")\n" +
                    "\tprivate String page"+i+";");
        }
        System.out.println();
        for(int i = 1; i < n; i++){
            System.out.println("alter table ZB_HuiYi_AnPai_Print add page"+i+"  nvarchar2(10);");
        }
        return n;
    }

    private static   String del(String id){
        return id.replaceAll(" ","");
    }

}
