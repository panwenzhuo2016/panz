//package com.pwz.myGenerator;
//
//import all.JYDataTransferService;
//import all.JYDataTransferService_Service;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.sql.*;
//
//public class Jdbc {
//    public static void main(String[] args) throws Exception {
//        String driver = "oracle.jdbc.OracleDriver";    //驱动标识符
//        String url = "jdbc:oracle:thin:@112.112.12.76:1521:zloracle"; //链接字符串
//        String user = "tr_trade_zs";         //数据库的用户名
//        String password = "tr_trade_zs";     //数据库的密码
//        Connection con = null;
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        boolean flag = false;
//
//        try {
//            JYDataTransferService_Service jyDataTransferService_service = new JYDataTransferService_Service();
//            JYDataTransferService jyDataTransferService = jyDataTransferService_service.getJYDataTransferServiceImplPort();
//
//            String poPath = "d:/" + "" + "po5";
//            File folder = new File(poPath);
//            if (!folder.exists()) {
//                folder.mkdir();
//            }
//
//
//
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,user, password);
//            String sql = "select * from DB_JIEGUO";
//            pstm =con.prepareStatement(sql);
//            rs = pstm.executeQuery();
//            int i = 0;
//            while(rs.next()) {
//                i++;
//                String BD_GUID =rs.getString("BD_GUID");
//                System.out.println(BD_GUID);
//
//                File beanFile = new File(poPath, BD_GUID + ".xml");
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile), "utf-8"));
//
//                String s = jyDataTransferService.queryAllData(BD_GUID);
//                bw.write(s);
//                bw.flush();
//                bw.close();
//            }
//            System.out.println(i);
//
//            flag = true;
//        } catch(ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if(rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            // 关闭执行通道
//            if(pstm !=null) {
//                try {
//                    pstm.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            // 关闭连接通道
//            try {
//                if(con != null &&(!con.isClosed())) {
//                    try {
//                        con.close();
//                    } catch (SQLException e) {
//                       e.printStackTrace();
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(flag) {
//            System.out.println("执行成功！");
//        } else {
//            System.out.println("执行失败！");
//        }
//    }
//}