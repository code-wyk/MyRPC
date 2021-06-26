package com.wyk.MyRPC.jdbc;

import java.sql.*;

public class DbConnect {
//    private static String url="jdbc:mysql://localhost:3306/rpc?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static String url="jdbc:mysql://localhost:3306/rpc?&useSSL=false&serverTimezone=Asia/Shanghai";
    private static String userName="root";
    private static String password="root";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getNameById(Integer id){
        String sql="select username from user where id="+id;
        try{
            Connection conn= DriverManager.getConnection(url,userName,password);
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            String result="";
            while(rs.next()){
                result=rs.getString(1);
            }
            rs.close();
            statement.close();
            conn.close();
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Boolean getSexById(Integer id){
        String sql="select sex from user where id="+id;
        try{
            Connection conn= DriverManager.getConnection(url,userName,password);
            PreparedStatement statement=conn.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            if(rs.next()){
                return rs.getBoolean("sex");
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }
    

}
