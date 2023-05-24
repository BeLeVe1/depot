import e

# 连接到MySQL数据库
conn = mysql.connector.connect(
    host="localhost_3306",
    user="root",
    password="123456",
    database="bd_sys"
)
c = conn.cursor()

# 执行 SQL 查询语句
c.execute("SELECT quantity FROM fd_outtemp WHERE sparepartnum='4010144'")

# 从查询结果中获取所有的quantity并存储在列表中
result = []
for row in c:
    result.append(row[0])

# 打印结果列表
print(result)