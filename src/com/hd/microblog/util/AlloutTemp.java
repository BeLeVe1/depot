package com.hd.microblog.util;
import java.sql.*;
import com.hd.microblog.util.time;
public class AlloutTemp {



        public static void main(String[] args) {
            long startTime = System.currentTimeMillis(); // record start time
            String jdbcURL = "jdbc:mysql://localhost/bd_sys?useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "123456";

            // 连接数据库
            try (Connection conn = DriverManager.getConnection(jdbcURL, username, password)) {
                // 查询所有的num和name
                String query = "SELECT sparepartsNum, sparepartsName FROM fd_out where sparepartsNum is NOT NUll group by sparepartsNum ";
                System.out.println(query);
                try (PreparedStatement pstmt = conn.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int num = rs.getInt("sparepartsNum");
                        String name = rs.getString("sparepartsName");
                        System.out.println("num: " + num + ", name: " + name);
                        // 根据num执行查询
                        String truncateTableSQL = "DELETE From fd_outtemp where sparepartnum = " + num;

                        String sql =
                                "INSERT into fd_outtemp \n" +
                                        "SELECT " + num + ",'" + name + "', DATE_FORMAT(dayTb.cday,'%Y/%m') as mouth  " +
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
                                        "        where sparepartsNum='" + num + "' \n" +
                                        "        GROUP BY cday\n" +
                                        ") tNumTb  \n" +
                                        "ON tNumTb.cday = dayTb.cday \n" +
                                        "GROUP BY mouth \n" +
                                        "order by mouth  ASC";

                        try ( Statement stmt = conn.createStatement()
                              ;) {
                            stmt.executeUpdate(truncateTableSQL);;
                            stmt.executeUpdate(sql);





                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis(); // record end time
            String runtime = time.getTimeString( startTime,endTime);
            System.out.println("Runtime: " + runtime ); // print runtime
        }
    }


