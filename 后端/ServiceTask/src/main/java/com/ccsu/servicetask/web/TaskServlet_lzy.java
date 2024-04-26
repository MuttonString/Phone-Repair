package com.ccsu.servicetask.web;

import com.ccsu.servicetask.dao.TaskDao;
import com.ccsu.servicetask.entity.AjaxResult;
import com.ccsu.servicetask.entity.Task;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/***
 * 通过手机号或任务编号，从数据库中查找任务
 * @author 刘征宇
 */
public class TaskServlet_lzy {
    TaskDao dao = new TaskDao();


    public AjaxResult check(HttpServletRequest request) {
        AjaxResult result;


        String cus_phone = request.getParameter("cus_phone");
        List<Task> list = dao.findAllTaskByPhone(cus_phone);
        if (list != null && list.size() != 0) {
            result = AjaxResult.success(1, list);
        } else {
            list = dao.findAllTaskByNo(cus_phone);
            if (list != null && list.size() != 0) {
                result = AjaxResult.success(1, list);
            } else {
                result = AjaxResult.fail(0, "查询无数据");
            }
        }


        return result;
    }
}
