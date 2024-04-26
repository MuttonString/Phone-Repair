package com.ccsu.servicetask.dao;

import com.ccsu.servicetask.entity.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class AccountDao {
    public Account findAccountByNameAndPwd(String username, String password) {
        Account acc = null;
        Connection conn = null;
        try {
            conn = DBCommon.getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "SELECT * FROM tb_account WHERE admin_name=? AND admin_pwd=?";
            acc = qr.query(conn, sql, new BeanHandler<>(Account.class), username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return acc;
    }

    public List<Account> findAllAccount() {
        Connection conn = null;
        List<Account> list = null;
        try {
            conn = DBCommon.getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "SELECT * FROM tb_account";
            list = qr.query(conn, sql, new BeanListHandler<>(Account.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return list;
    }

    /***
     * 注册账户
     * @param acc 账户的实体类
     * @return 数据库改变的代码行数，1成功，0失败
     */
    public int regist(Account acc) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "INSERT INTO tb_account(admin_name, admin_pwd, admin_role, admin_state) VALUES(?,?,?,0)";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, acc.getAdmin_name(), acc.getAdmin_pwd(), acc.getAdmin_role());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }

    /***
     * 更新账户
     * @param acc 账户的实体类
     * @return 数据库改变的代码行数，1成功，0失败
     */
    public int updateAccount(Account acc) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "UPDATE tb_account SET admin_state=?, admin_pwd=? WHERE aid=?";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, acc.getAdmin_state(), acc.getAdmin_pwd(), acc.getAid());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }
}
