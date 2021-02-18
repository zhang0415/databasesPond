package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {
    public static ComboPooledDataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource("myC3p0");
        String name = dataSource.getDataSourceName();
        System.out.println(name);
    }

    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new C3P0Test();
    }
}
