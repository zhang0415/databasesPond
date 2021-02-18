package test;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class DBCPTest {

    public static DataSource dataSource;
    static String sql = "select * from guest";
    static {
        Properties properties = new Properties();
        try {
//            properties.load(DBCPTest.class.getClassLoader().
//                    getResourceAsStream("dbcp.properties"));
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties"));
            // 通过基础数据源工厂类  来创建连接池
            dataSource = BasicDataSourceFactory.createDataSource(properties);
            Connection conn = getConn();
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while (rs.next()){
                System.out.print(rs.getString("name")+" \t ");
                System.out.println(rs.getString("role"));
            }
            closeConnection(conn);
//            DatabaseMetaData metaData = conn.getMetaData();
//
//            System.out.println(metaData.getDatabaseProductName() + " " +
//                    metaData.getDatabaseProductVersion());
//
//            closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConnection(Connection connection){
        try {
            if (connection !=null && !connection.isClosed()){
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 35; i++) {
            Connection conn = getConn();
            System.out.println("成功获取连接" + i);
            closeConnection(conn);
        }
    }
}
