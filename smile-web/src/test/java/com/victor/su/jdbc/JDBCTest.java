package com.victor.su.jdbc;

import org.junit.Test;

import java.sql.*;

public class JDBCTest {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String USER = "root";
    private static transient String PSW = "root";
    //MySQL 8.0以上版本（MySQL连接驱动和版本都是8.0以上）的时候出现的问题错误，
    // 我们需要在访问数据库的Url后面加上以下的语句即可?serverTimezone=GMT%2B8：
    private static String URL = "jdbc:mysql://127.0.0.1:3306/mysql?serverTimezone=GMT%2B8";


    @Test
    public void testConnection () throws SQLException {
        Connection connection = null;
        String sql = "select * from user where User = 'root'";
        PreparedStatement preparedStatement;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PSW);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String host = resultSet.getString("Host");
                System.out.println(host);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
