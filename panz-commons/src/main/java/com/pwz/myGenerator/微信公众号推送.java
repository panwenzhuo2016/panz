package com.pwz.myGenerator;

import java.lang.reflect.Field;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-5-18 10:20
 **/
public class 微信公众号推送 {

    public static void main(String[] args) throws ClassNotFoundException {
        String path = "com.zhulong.webservice.jyxhgongzhonghao.";
        String[] obs = new String[]{"WsProject", "WsProjectTender", "WsTenderBid", "WsTenderPublish", "WsWinPublish", "WsWinResultPublish",
                "WsSchedule", "WsFileList"};
        for (String ob : obs) {
            Field[] declaredFields =  Class.forName(path+ob).getDeclaredFields();
            System.out.println("    @Override\n" +
                    "    public void deal"+ob.substring(2)+"Data(Map<String, Object> map, SoapService service) {\n" +
                    "        if (map == null || map.size() <= 0) {\n" +
                    "            return;\n" +
                    "        }\n" +
                    "        String yeWuGuid = dealString(map.get(\"yeWuGuid\"));\n" +
                    "        Long modifyTime = map.get(\"modifyTime\")==null?0L:Long.parseLong(dealString(map.get(\"modifyTime\")));\n" +
                    "        "+ob+" "+getLString(ob)+" = new "+ob+"();");
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                if(declaredField.getType().getSimpleName().equals("BigDecimal")||declaredField.getType().getSimpleName().equals("Double")
                        ||declaredField.getType().getSimpleName().equals("Long") ||declaredField.getType().getSimpleName().equals("Integer")){
                    System.out.println(""+getLString(ob)+".set" + getUPString(name) + "(null);");
                    continue;
                }
                if(declaredField.getType().getSimpleName().equals("XMLGregorianCalendar")){
                    System.out.println(""+getLString(ob)+".set" + getUPString(name) + "(dateToXML(new Date()));");
                    continue;
                }
                System.out.println(""+getLString(ob)+".set" + getUPString(name) + "(dealString(map.get(\""+name+"\")));");
            }
            System.out.println("     WsResult wsResult = service.add"+ob.substring(2)+"("+getLString(ob)+");\n" +
                    "        if (wsResult.isSuccess()) {\n" +
                    "            jobTaskWeiXingGongZhongHaoDao.insertTongBuJiLu(JiaoYiTongJiConstants.Type_GongCheng, yeWuGuid, modifyTime);\n" +
                    "        }\n" +
                    "    }");
            System.out.println();
            System.out.println();
        }
        System.out.println("------------------------------------------------------");
        for (String ob : obs) {
            System.out.println("   /**\n" +
                    "    *  \n" +
                    "    * @author panwenzhuo\n" +
                    "    * @date 2018-5-18 9:51  \n" +
                    "    * @param map\n" +
                    "   \b * @param service  \n" +
                    "    * @return void  \n" +
                    "    */\n" +
                    "   void deal"+ob.substring(2)+"Data(Map<String, Object> map, SoapService service);");
        }
        System.out.println("------------------------------------------------------");
        for (String ob : obs) {
            System.out.println("   /**\n" +
                    "    *  查询  数据\n" +
                    "    * @author panwenzhuo\n" +
                    "    * @date 2018-5-18 9:55  \n" +
                    "    * @param rows  \n" +
                    "    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  \n" +
                    "    */\n" +
                    "   List<Map<String,Object>> query" + ob.substring(2) + "Data(int rows);");
            System.out.println();
        }

        System.out.println("------------------------------------------------------");
        for (String ob : obs) {
            System.out.println("    @Override\n" +
                    "    public List<Map<String, Object>> query" + ob.substring(2) + "Data(int rows) {\n" +
                    "        return jobTaskWeiXingGongZhongHaoDao.query" + ob.substring(2) + "Data(rows);\n" +
                    "    }");
            System.out.println();
        }
        System.out.println("------------------------------------------------------");

        for (String ob : obs) {
            Field[] declaredFields = Class.forName(path + ob).getDeclaredFields();
            System.out.println("   @Override\n" +
                    "    public List<Map<String, Object>> query"+ob.substring(2)+"Data(int rows) {\n" +
                    "        StringBuilder sql = new StringBuilder(); \n" +
                    "  sql.append(\" select  a. as id , \"+");
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                if(!"id".equals(name)){
                    System.out.println("       \"    as "+name+"  \" +");
                }
            }
            System.out.println("           \" from   a\");\n" +
                    "        sql.append(\" where not exists(select 1 from TONGBU_GZH_JILU x where a.XM_Guid = x.yewu_guid and a.Modify_Time = x.modify_time \" +\n" +
                    "                \" and x.leixing = :leiXing)\");\n" +
                    "        sql.append(\" and a.IS_DELETED = '0'  \");\n" +
                    "        Session session = sessionFactoryZs.openSession();\n" +
                    "        SQLQuery query = session.createSQLQuery(sql.toString())\n" +
                    "                .addScalar(\"id\",StringType.INSTANCE)");
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                if(!"id".equals(name) && !"modifyTime".equals(name) ){
                    System.out.println("  .addScalar(\""+name+"\",StringType.INSTANCE)");
                }
            }
            System.out.println("                .addScalar(\"modifyTime\",StringType.INSTANCE);\n" +
                    "        query.setParameter(\"leiXing\", JiaoYiTongJiConstants.Type_XiangMu);\n" +
                    "        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);\n" +
                    "        query.setMaxResults(rows);\n" +
                    "        return query.list();" +
                    "}");
            System.out.println();
            System.out.println();
        }
        System.out.println("------------------------------------------------------");

        for (String ob : obs) {
            System.out.println("    /**\n" +
                    "     *  处理推送  数据\n" +
                    "     * @author panwenzhuo\n" +
                    "     * @date 2018-5-18 10:08  \n" +
                    "     * @param service  \n" +
                    "     * @return void  \n" +
                    "     */");
            System.out.println("    private void set"+ob+"(SoapService service) {\n" +
                    "        String m = \"JobTaskWeiXingGongZhongHao set"+ob+" \";\n" +
                    "        try{\n" +
                    "            log.info(m + \" begin:\"+sp.format(new Date()));\n" +
                    "            List<Map<String, Object>> paramList = jobTaskWeiXingGongZhongHaoService.query"+ob.substring(2)+"Data(rows);\n" +
                    "            for(Map<String, Object> map : paramList){\n" +
                    "                try{\n" +
                    "                    jobTaskWeiXingGongZhongHaoService.deal"+ob.substring(2)+"Data(map, service);\n" +
                    "                }catch(Exception e){\n" +
                    "                    log.error(m + \"deal error:\"+sp.format(new Date()), e);\n" +
                    "                }\n" +
                    "            }\n" +
                    "            log.info(m + \" end:\"+sp.format(new Date()));\n" +
                    "        }catch(Exception e){\n" +
                    "            log.error(m + \"query error:\"+sp.format(new Date()), e);\n" +
                    "        }\n" +
                    "    }");
        }
    }

    private static String getUPString(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String getLString(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
