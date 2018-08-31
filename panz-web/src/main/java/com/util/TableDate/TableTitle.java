package com.util.TableDate;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 15:06
 **/
public class TableTitle {
    private String title;
    private String key;

    public TableTitle(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
