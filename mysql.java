import java.sql.*;
import java.util.Arrays;
 class mysql {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        // 加载 JDBC 驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 创建数据库连接
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_sys", "root", "123456");

        // 创建 SQL 语句
        String sql = "SELECT (MONTH(outDate) + (YEAR(outDate) - 2019) * 12 + 12) AS month, SUM(quantity) AS count FROM sheet1 WHERE sparepartsName = '发电机轴承' GROUP BY month";

        // 创建 SQL 执行对象
        stmt = conn.createStatement();

        // 执行 SQL 语句
        rs = stmt.executeQuery(sql);

         // 将结果转换为 ArrayList
         ArrayList<Integer> list = new ArrayList<>();
         while (rs.next()) {
             int count = rs.getInt("count");
             list.add(count);
         }

         // 将 ArrayList 转换为 int 数组
         int[] data = new int[list.size()];
         for (int i = 0; i < list.size(); i++) {
             data[i] = list.get(i);
         }

         // 输出结果
         System.out.println(Arrays.toString(data));
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // 关闭连接和资源
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
