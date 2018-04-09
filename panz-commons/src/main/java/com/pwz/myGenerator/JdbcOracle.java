package com.pwz.myGenerator;

import java.sql.*;

public class JdbcOracle {
    public static void main(String[] args) {
        String driver = "oracle.jdbc.OracleDriver";    //驱动标识符
        String url = "jdbc:oracle:thin:@112.112.12.76:1521:zloracle"; //链接字符串
        String user = "tr_trade_zs";         //数据库的用户名
        String password = "tr_trade_zs";     //数据库的密码
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user, password);
            String sql = "select * from DB_JIEGUO";
            pstm =con.prepareStatement(sql);
            rs = pstm.executeQuery();
            int i = 0;
            while(rs.next()) {
                i++;
                String ename =rs.getString("BD_GUID");
                System.out.println(ename);
            }
            System.out.println(i);

            flag = true;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭执行通道
            if(pstm !=null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭连接通道
            try {
                if(con != null &&(!con.isClosed())) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                       e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(flag) {
            System.out.println("执行成功！");
        } else {
            System.out.println("执行失败！");
        }
    }
}