package com.pwz.myGenerator.MakeTable;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/8/18 0:29
 **/
public class MakeTableFactory {
    public static MakeTable getMakeTableMysqlImpl(){
        return new MakeTableMysqlImpl();
    }
    public static MakeTable getMakeTableOracleImpl(){
        return new MakeTableOracleImpl();
    }
}
