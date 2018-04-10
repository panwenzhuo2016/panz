package com.pwz.myGenerator;

import java.io.BufferedWriter;

public class B {
    public static void main2(BufferedWriter bw2, String name, String className) throws Exception {
        String bean = className;
        String xmlbean = name;
        if(bean.startsWith("DB") ){
            bw2.write("   @Autowired\n" +
                    "    private "+bean+"Dao db"+bean.substring(2)+"Dao;");
        } else {
            bw2.write("   @Autowired\n" +
                    "    private "+bean+"Dao "+A.getPathName(bean)+"Dao;");
        }
        bw2.write("\n");
        one(bean,xmlbean,bw2);
        more(bean,xmlbean,bw2);
    }

    private static void one(String bean,String xmlbean,BufferedWriter bw2) throws Exception{
        String code = "      "+bean+" "+A.getPathName(bean)+" = "+A.getPathName(bean)+"Dao.queryByGgbdGuid(zbGongGaoBD.getGgBDGuid());\n" +
                "            if("+A.getPathName(bean)+" != null){\n" +
                "                "+xmlbean+" "+xmlbean.toLowerCase()+" = new "+xmlbean+"();\n" +
                "                BeanUtils.copyProperties("+A.getPathName(bean)+", "+xmlbean.toLowerCase()+");\n" +
                "                allData.set"+A.getPathName(xmlbean)+"("+xmlbean.toLowerCase()+");\n" +
                "            }";
        bw2.write(code);
        bw2.write("\n");
    }
    private static void more(String bean,String xmlbean,BufferedWriter bw2) throws Exception{
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
        bw2.write(code);
    }
}
