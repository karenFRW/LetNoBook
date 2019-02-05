package com.example.user.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbManagerTest {


    private static Connection getSQLConnection(String ip,String user,String pwd,String db)
    {
        Connection con =null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+":1433/"+db+";charset=utf-8",user,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

    public static String QuerySQL(){

        String result="";

        try {
            String ip = "52.246.164.133";
            String db = "LetNoBook";
            String user = "lnb";
            String pwd = "lnb";
            Connection conn = getSQLConnection(ip,user,pwd,db);
            String sql = "select top 10 * from tStudent";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String s1 = rs.getString("fClassId");
                String s2 = rs.getString("f學生姓名");
                result += s1+" - " + s2 + "\n";
                System.out.println(s1+" - "+s2);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result += "查詢數據異常！"+e.getMessage();
        }
        return result;
    }

    public static void main(String[] args){
        QuerySQL();

    }
}
