package com.ccsu.servicetask.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.ccsu.servicetask.utils.ConfigUtils.getValue;

public class DBCommon {
    public static void main(String[] args) {
        System.out.println(getConn());
    }

    private DBCommon() {}
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(getValue("driver_class"));
            String url = "jdbc:mysql://" + getValue("ip") + ":" + getValue("port") + "/phone_repair?useUnicode=true&CharacterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, getValue("username"), getValue("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
