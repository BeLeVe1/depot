package com.hd.microblog.util;

import java.io.FileWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Mysql2Tempsheet {

	public static void sql2temp(String  sparepartsNum,String  sparepartsName)  {
      String jdbcURL = "jdbc:mysql://localhost/bd_sys?useUnicode=true&characterEncoding=utf-8";
      String username = "root";
      String password = "123456";



        // 定义SQL语句
        String query = "SELECT sparepartsNum, sparepartsName FROM fd_out";

        // 连接数据库并执行SQL语句
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {


            while (rs.next()) {
                    int num = rs.getInt("sparepartsNum");
                    String name = rs.getString("sparepartsName");
                    System.out.println("num: " + num + ", name: " + name);

                String truncateTableSQL = "DELETE From fd_outtemp where sparepartnum = "+num;

                String sql =
                        "INSERT into fd_outtemp \n" +
                                "SELECT "+ num+",'"+name+"', DATE_FORMAT(dayTb.cday,'%Y/%m') as mouth  " +
                                "    ,IFNULL(sum(tNumTb.num),0) 'data' " +
                                "FROM (\n" +
                                "    SELECT @cdate := DATE_ADD(@cdate, INTERVAL +1 DAY) cday " +
                                "        FROM( \n" +
                                "\t\t\t\t       SELECT \n" +
                                "\t\t\t\t             @cdate := DATE_ADD('2019-01-01', INTERVAL -1 DAY)  \n" +
                                "\t\t\t\t\t\t\t\t\t\t FROM fd_out \n" +
                                "\t\t\t\t\t\t) t0\n" +
                                "        WHERE date(@cdate) < DATE_ADD(                                    \n" +
                                "\t\t\t\t                               (select max(outdate) \n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    from fd_out)  \n" +
                                "\t\t\t\t                                , INTERVAL -1 DAY\n" +
                                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t)\n" +
                                ") dayTb   \n" +
                                "LEFT JOIN(\n" +
                                "    SELECT t.outdate cday, sum( t.quantity ) num \n" +
                                "\t\t    FROM fd_out t\n" +
                                "        where sparepartsNum='"+num+"' \n" +
                                "        GROUP BY cday\n" +
                                ") tNumTb  \n" +
                                "ON tNumTb.cday = dayTb.cday \n" +
                                "GROUP BY mouth \n" +
                                "order by mouth  ASC";
                System.out.println(sql);
                stmt.executeUpdate(truncateTableSQL);

                // 插入新数据
                stmt.executeUpdate(sql);

                System.out.println("数据已成功插入fd_outtemp表。");



            }

            // 清空表

        } catch (SQLException e) {
            System.out.println("数据插入fd_outtemp表时发生错误。");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        sql2temp( "1","1");
    }
}



