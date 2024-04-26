package com.ccsu.servicetask.dao;

import com.ccsu.servicetask.entity.Task;
import com.ccsu.servicetask.utils.TaskCodeUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class TaskDao {
    public static void main(String[] args) {
        List<Task> list = new TaskDao().findAllTask();
        list.forEach(System.out::println);
    }

    public List<Task> findAllTask() {
        Connection conn = null;
        List<Task> list = null;
        try {
            conn = DBCommon.getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "SELECT * FROM tb_task ORDER BY task_id DESC";
            list = qr.query(conn, sql, new BeanListHandler<>(Task.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return list;
    }

    public int removeTask(int task_id) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "DELETE FROM tb_task WHERE task_id=?";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, task_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }

    public int saveTask(Task task) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "INSERT INTO tb_task(cus_name, cus_phone, service_item, task_no, task_time, task_state) VALUES(?,?,?,?,NOW(),0)";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, task.getCus_name(), task.getCus_phone(), task.getService_item(),
                    TaskCodeUtils.createCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }

    public int updateTask(int task_id, int task_state, String service_item) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConn();
            String sql = "UPDATE tb_task SET task_state=? WHERE task_id=?";
            QueryRunner qr = new QueryRunner();
            row = qr.execute(conn, sql, task_state, task_id);
            if (task_state == 2 && row == 1) {
                row = new FittingsDao().updateFittings(service_item, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return row;
    }

    public List<Task> searchTask(String task_no_or_cus_phone) {
        Connection conn = null;
        List<Task> list = null;
        try {
            conn = DBCommon.getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "SELECT * FROM tb_task WHERE task_no=? OR cus_phone=?";
            list = qr.query(conn, sql, new BeanListHandler<>(Task.class), task_no_or_cus_phone, task_no_or_cus_phone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConn(conn);
        }
        return list;
    }
}
