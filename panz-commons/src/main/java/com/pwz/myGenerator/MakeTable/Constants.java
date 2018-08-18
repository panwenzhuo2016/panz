package com.pwz.myGenerator.MakeTable;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/18 10:02
 **/
public class Constants {
    public static final String   BASE = " `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            " `create_guid` char(32) DEFAULT NULL COMMENT '创建人',\n" +
            " `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
            " `modify_guid` char(32) DEFAULT NULL COMMENT '修改人',\n"+
            " `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除',\n";
}
