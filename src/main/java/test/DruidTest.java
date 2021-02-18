package test;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DBCPTest.class.getClassLoader().
                    getResourceAsStream("druid.properties"));

            // 通过基础数据源工厂类  来创建连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            Connection conn = getConn();
            DatabaseMetaData metaData = conn.getMetaData();

            System.out.println(metaData.getDatabaseProductName() + " " +
                    metaData.getDatabaseProductVersion());

            closeConn(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Connection getConn() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConn(Connection conn) {

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Connection conn = getConn();
            System.out.println("成功获取连接" + i);
            closeConn(conn);
        }
    }
}
