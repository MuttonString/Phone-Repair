package com.ccsu.servicetask.dao;

import com.ccsu.servicetask.entity.Factory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class FactoryDao {
    public static void main(String[] args) {
        List<Factory> list = new FactoryDao().findAllFactory();
        list.forEach(System.out::println);
    }

    public int saveFac(Factory fac) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "insert into tb_factory(fac_name,fac_phone,fac_item,fac_state)" +
                    "values (?,?,?,0)";
            QueryRunner qr = new QueryRunner();

            row = qr.execute(conn, sql, fac.getFac_name(), fac.getFac_phone(), fac.getFac_item());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);

        }

        return row;
    }

    /**
     * 查询所有账户
     */
    public List<Factory> findAllFactory() {
        Connection conn = null;
        List<Factory> list = null;
        try {
            conn = DBCommon.getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "select * from tb_factory order by fac_id desc";
            list = qr.query(conn, sql, new BeanListHandler<Factory>(Factory.class));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);

        }

        return list;
    }

    public int updateFactory(int fac_id, int fac_state) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "UPDATE tb_factory SET fac_state=? WHERE fac_id=?";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, fac_state, fac_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }
}
