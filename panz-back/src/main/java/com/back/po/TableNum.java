package com.back.po;

import java.io.Serializable;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-4-9 13:10
 **/
public class TableNum extends BaseEntity  implements Serializable {
    private String tableName;
    private String zs;
    private String zl;
    private String yx;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }
}
