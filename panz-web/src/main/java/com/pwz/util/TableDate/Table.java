package com.pwz.util.TableDate;




import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/19 15:00
 **/
public class Table {
    private List<TableTitle> columns1;
    private List data1;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TableTitle> getColumns1() {
        return columns1;
    }

    public void setColumns1(List<TableTitle> columns1) {
        this.columns1 = columns1;
    }

    public List getData1() {
        return data1;
    }

    public void setData1(List data1) {
        this.data1 = data1;
    }
}
