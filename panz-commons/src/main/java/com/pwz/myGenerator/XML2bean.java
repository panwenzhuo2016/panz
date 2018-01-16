package com.pwz.myGenerator;

public class XML2bean {
    public static void main(String[] args) {
        String s = "      <XMBH>项目编号</XMBH>\n" +
                "        <ZBXMBH>招标项目编号</ZBXMBH>\n" +
                "        <GCBH>标段编号</GCBH>\n" +
                "        <GCMC>工程名称</ GCMC >\n" +
                "        <JSDW>发包人</ JSDW >\n" +
                "        <ProjectClassName>工程类别</ProjectClassName >\n" +
                "        <InvModeName>招标方式</InvModeName >\n" +
                "        <EvalMethodName>评标方法</EvalMethodName >\n" +
                "        <ZBDLJG>招标代理机构 </ ZBDLJG >\n" +
                "        <CorpNO>中标单位编号 </ CorpNO >\n" +
                "        <CorpName>中标人 </ CorpName >\n" +
                "        <BidPrice>中标价(万元)</ BidPrice >\n" +
                "        <BidUnitPrice>中标价(自带单位)</BidUnitPrice >";
        String[] ss = s.split(">\\n");

        for (String str : ss){
            String id = str.substring(str.indexOf("<") + 1,str.indexOf(">"));
//			System.out.println(id);
            String name = str.substring(str.indexOf(">") +1,str.lastIndexOf("<"));
//			System.out.println(name);
            System.out.println("\t/**\n" +
                    "\t * "+name+"\n" +
                    "\t */\n" +
                    "\t@XmlElement(name=\""+id+"\")\n" +
                    "\tprivate String "+id+";");
            System.out.println();
        }
    }
}
