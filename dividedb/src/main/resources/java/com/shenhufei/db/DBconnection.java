package com.shenhufei.db;

import com.shenhufei.exception.DBException;
import com.shenhufei.pojo.DivideTableManagerConfigPojo;

import java.sql.*;

/**
 *   
 * 
 * @Description: 数据库操作类
 * @author shenhufei  
 * @date 2019年4月30日 下午2:08:07 
 * @version V1.0   
 */
public class DBconnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ttpc?useUnicode=true&amp;characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Boolean getTableExist(String tableName) throws Exception {
        // 1.加载驱动程序
        // 2.获得数据库链接
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            st = conn.createStatement();
            rs = st.executeQuery(
                    "SELECT table_name FROM information_schema.TABLES WHERE table_name ="
                            + "'" + tableName + "'");
            Integer maxRows = rs.getStatement().getMaxRows();
            if (maxRows.equals(1)) {
                return true;
            } else {
                return false;
            }
            // 4.处理数据库的返回结果(使用ResultSet类)
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            closeResource(conn, st, rs);
        }

    }

    private static void closeResource(Connection conn, Statement st,
            ResultSet rs) throws SQLException {
        // 关闭资源
        if (null != conn) {
            conn.close();
        }
        if (null != st) {
            st.close();
        }
        if (null != rs) {
            rs.close();
        }
    }

    public static void createTable(String sql, String autoIncrementSql,
            Integer part, String tableName) throws Exception {
        // 1.加载驱动程序
        // 2.获得数据库链接
        Connection conn = null;
        Statement st = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            st = conn.createStatement();
            // 建表
            st.executeUpdate(sql);
            // 设置起始值
            st.executeUpdate(
                    "alter table " + tableName + " AUTO_INCREMENT =" + part);
            // 设置分表之后的步长
            st.executeUpdate(autoIncrementSql);
            // 4.处理数据库的返回结果(使用ResultSet类)
        } catch (Exception e) {
            // TODO 这个位置不应该抛出异常，加上日志信息
            /* throw new DBException(e.getMessage()); */
        } finally {
            closeResource(conn, st, null);
        }

    }

    public static void insertDivideTable(String sqlInsert) throws Exception {
        // 1.加载驱动程序
        // 2.获得数据库链接
        Connection conn = null;
        Statement st = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            st = conn.createStatement();
            st.executeUpdate(sqlInsert);
            // 4.处理数据库的返回结果(使用ResultSet类)
        } catch (Exception e) {

            throw new DBException(e.getMessage());
        } finally {
            closeResource(conn, st, null);
        }

    }

    public static DivideTableManagerConfigPojo getDivideTableConfig(
            String className) throws Exception {
        // 1.加载驱动程序
        // 2.获得数据库链接
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            st = conn.createStatement();
            rs = st.executeQuery(
                    "SELECT   *  FROM divide_table_manager_config WHERE pojo_name  ="
                            + "'" + className + "'");
            if (null != rs) {
                while (rs.next()) {
                    DivideTableManagerConfigPojo config = new DivideTableManagerConfigPojo();
                    config.setId(rs.getLong(1));
                    config.setTableName(rs.getString(2));
                    config.setPojoName(rs.getString(3));
                    config.setPart(rs.getInt(4));
                    config.setStep(rs.getInt(5));
                    config.setDes(rs.getString(6));
                    return config;
                }
            }
            return null;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            closeResource(conn, st, rs);
        }

    }

}
