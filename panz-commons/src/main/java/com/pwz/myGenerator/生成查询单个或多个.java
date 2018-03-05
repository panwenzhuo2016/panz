package com.pwz.myGenerator;

public class 生成查询单个或多个 {
    public static void main(String[] args) {
        String bean = "ZtbCondition";
        String xmlbean = "DB_ZTB_QingKuang_BaoGao";
        if(xmlbean.startsWith("DB")){
            System.out.println("   @Autowired\n" +
                    "    private "+bean+"Dao db"+bean.substring(2)+"Dao;");
        } else {
            System.out.println("   @Autowired\n" +
                    "    private "+bean+"Dao "+A.getPathName(bean)+"Dao;");
        }
        System.out.println();
        one(bean,xmlbean);
        more(bean,xmlbean);
    }

    private static void one(String bean,String xmlbean) {
        String code = "      "+bean+" "+A.getPathName(bean)+" = "+A.getPathName(bean)+"Dao.queryByGgbdGuid(zbGongGaoBD.getGgBDGuid());\n" +
                "            if("+A.getPathName(bean)+" != null){\n" +
                "                "+xmlbean+" "+xmlbean.toLowerCase()+" = new "+xmlbean+"();\n" +
                "                BeanUtils.copyProperties("+A.getPathName(bean)+", "+xmlbean.toLowerCase()+");\n" +
                "                allData.set"+A.getPathName(xmlbean)+"("+xmlbean.toLowerCase()+");\n" +
                "            }";
        System.out.println(code);
        System.out.println();
    }
    private static void more(String bean,String xmlbean) {
        String code = "      List<"+bean+"> "+A.getPathName(bean)+"s = "+A.getPathName(bean)+"Dao.queryByGgAndBdGuid(zbGongGaoBD.getGgGuid(), zbGongGaoBD.getBdGuid());\n" +
                "            if("+A.getPathName(bean)+"s != null && "+A.getPathName(bean)+"s.size() > 0){\n" +
                "                List<"+xmlbean+"> "+xmlbean.toLowerCase()+"s = new ArrayList<>();\n" +
                "                for ("+bean+" "+A.getPathName(bean)+" : "+A.getPathName(bean)+"s) {\n" +
                "                    "+xmlbean+" "+A.getPathName(xmlbean)+" = new "+xmlbean+"();\n" +
                "                    BeanUtils.copyProperties("+A.getPathName(bean)+","+A.getPathName(xmlbean)+");\n" +
                "                    "+xmlbean.toLowerCase()+"s.add("+A.getPathName(xmlbean)+");\n" +
                "                }\n" +
                "                allData.set"+A.getPathName(xmlbean)+"("+xmlbean.toLowerCase()+"s);\n" +
                "            }";
        System.out.println(code);
    }
}
