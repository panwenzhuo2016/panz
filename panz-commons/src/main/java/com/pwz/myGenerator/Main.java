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
    @Test
    public void data() throws Exception{
        EntityUtil entityUtil = new EntityUtil();
        entityUtil.generate("data");
    }
    //生成文件
    @Test
    public void build() throws Exception{
        EntityUtil entityUtil = new EntityUtil();
        entityUtil.generate("build");
    }
    //生成字典sql
    @Test
    public void sql() throws Exception{
        EntityUtil entityUtil = new EntityUtil();
        entityUtil.generate("sql");
    }

    @Test
    public void html() throws Exception{
        BuildHtml buildHtml = new BuildHtml();
        buildHtml.buildHtml(); //web html生成
        buildHtml.buildHtmlApp();//app html生成
        buildHtml.buildHtmlApp2();//app json生成
        buildHtml.buildHtmlApp3();//app 实体生成
    }
}
