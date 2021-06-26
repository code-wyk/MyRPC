package com.wyk.MyRPC.jdbc;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {


        //1、加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2、用户信息和url
        String dbName = "rpc";
        String password = "root";
        String userName = "root";
        String url = "jdbc:mysql://localhost:3306/rpc?&useSSL=false&serverTimezone=Asia/Shanghai";
        //3、连接成功，数据库对象coon代表数据库
        Connection conn = DriverManager.getConnection(url, userName, password);
        //4、执行SQL的对象 PreparedStatement
        String sql = "select * from user";
        PreparedStatement ps = conn.prepareStatement(sql);
        //5、执行SQL,返回结果
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("id:" + rs.getInt(1) + "name:" + rs.getString(2) + "password:" + rs.getString(3));
        }
        rs.close();
        ps.close();
        conn.close();
    }
}
