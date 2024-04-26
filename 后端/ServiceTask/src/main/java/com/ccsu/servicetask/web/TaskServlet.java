package com.ccsu.servicetask.web;

import com.ccsu.servicetask.dao.TaskDao;
import com.ccsu.servicetask.entity.AjaxResult;
import com.ccsu.servicetask.entity.Task;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
    Gson gson = new Gson();
    TaskDao dao = new TaskDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String action = req.getParameter("ac");
        AjaxResult result;
        if ("list".equalsIgnoreCase(action)) {
            List<Task> list = dao.findAllTask();
            if (list != null && !list.isEmpty()) {
                result = AjaxResult.success(list);
            } else {
                result = AjaxResult.fail("查询无数据");
            }
        } else if ("del".equalsIgnoreCase(action)) {
            String task_id = req.getParameter("task_id");
            int row = dao.removeTask(Integer.parseInt(task_id));
            if (row == 1) {
                result = AjaxResult.success("删除成功");
            } else {
                result = AjaxResult.fail("删除失败");
            }
        } else if ("save".equalsIgnoreCase(action)) {
            Task task = new Task();
            task.setCus_name(req.getParameter("cus_name"));
            task.setCus_phone(req.getParameter("cus_phone"));
            task.setService_item(req.getParameter("service_item"));
            int row = dao.saveTask(task);
            if (row == 1) {
                result = AjaxResult.success("保存成功");
            } else {
                result = AjaxResult.fail("保存失败");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            int row = dao.updateTask(Integer.parseInt(req.getParameter("task_id")), Integer.parseInt(req.getParameter("task_state")), req.getParameter("service_item"));
            if (row == 1) {
                result = AjaxResult.success("更新成功");
            } else {
                result = AjaxResult.fail("更新失败");
            }
        } else if ("check".equalsIgnoreCase(action)) {
            // 到手机端查询相关模块
            result = new TaskServlet_lzy().check(req);

// 弃用：
//            if ("search".equalsIgnoreCase(action)) {
//            List<Task> list = dao.searchTask(req.getParameter("task_no_or_cus_phone"));
//            if (list != null && !list.isEmpty()) {
//                result = AjaxResult.success(list);
//            } else {
//                result = AjaxResult.fail("查询无数据");
//            }
        } else {
            result = AjaxResult.fail("ac操作符无效");
        }

        String json = gson.toJson(result);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
