package com.servlet.lsp.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC工具类
 *
 * */
public class DBUtil {
    private static ResourceBundle bundle=ResourceBundle.getBundle("resources.jdbc");
    private static String driver =bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String user=bundle.getString("user");
    private static String password=bundle.getString("password");
    static {
        //注册驱动（驱动只需要注册一次，放在静态代码块中。BDUtil类加载时运行）
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
         return conn;
    }
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

    }
}

