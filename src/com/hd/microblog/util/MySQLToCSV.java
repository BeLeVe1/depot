package com.hd.microblog.util;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;
public class MySQLToCSV {
	
	public static String sql2csv(String  sparepartsName)  {
      String jdbcURL = "jdbc:mysql://localhost/bd_sys?useUnicode=true&characterEncoding=utf-8";
      String username = "root";
      String password = "123456";
      String csvFilePath = "D:\\train.csv";

        String sql =
                "SELECT DATE_FORMAT(dayTb.cday,'%Y/%m') as mouth  " +
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
                "        where sparepartsName='"+sparepartsName+"' \n" +
                "        GROUP BY cday\n" +
                ") tNumTb  \n" +
                "ON tNumTb.cday = dayTb.cday \n" +
                "GROUP BY mouth \n" +
                "order by mouth  ASC";
        System.out.println(sql);
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            FileWriter csvWriter = new FileWriter("output.csv");
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Write column headers to CSV file
            for (int i = 1; i <= columnCount; i++) {
                csvWriter.append(resultSet.getMetaData().getColumnLabel(i));
                if (i != columnCount) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            // Write data to CSV file
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(resultSet.getString(i));
                    if (i != columnCount) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println("csv文件获取成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("csv文件获取失败");
        }
        return csvFilePath;
    }

    public static void main(String[] args) {
        //sql2csv();
    }
}



