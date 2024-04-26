package com.ccsu.servicetask.dao;

import com.ccsu.servicetask.entity.Fittings;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class FittingsDao {
    public static void main(String[] args) {
        List<Fittings> list = new FittingsDao().findAllFittings();
        list.forEach(System.out::println);
    }

    public List<Fittings> findAllFittings() {
        Connection conn = null;
        List<Fittings> list = null;
        try {
            conn = DBCommon.getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "SELECT * FROM tb_fittings";
            list = qr.query(conn, sql, new BeanListHandler<>(Fittings.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return list;
    }

    public int updateFittings(int fit_id, int fit_qty) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "UPDATE tb_fittings SET fit_qty=fit_qty+? where fit_id=?";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, fit_qty, fit_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }

    public int updateFittings(String fit_name, int fit_qty) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "UPDATE tb_fittings SET fit_qty=fit_qty+? where fit_name=?";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, fit_qty, fit_name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }
}
