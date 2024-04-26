package com.ccsu.servicetask.web;

import com.ccsu.servicetask.dao.AccountDao;
import com.ccsu.servicetask.entity.Account;
import com.ccsu.servicetask.entity.AjaxResult;
import com.ccsu.servicetask.entity.Task;
import com.ccsu.servicetask.utils.JwtUtil;
import com.ccsu.servicetask.utils.MD5Utils;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @author 马家骏"
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    AccountDao dao = new AccountDao();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String admin_name = req.getParameter("admin_name");
        String admin_pwd = req.getParameter("admin_pwd");
        AjaxResult result;
        if ("check".equalsIgnoreCase(req.getParameter("ac"))) { // 检查账户和密码是否匹配
            Account acc = dao.findAccountByNameAndPwd(admin_name, MD5Utils.md5(admin_pwd)); // 先对密码进行MD5加密再到数据库验证
            if (acc != null) {
                if (acc.getAdmin_state() == 1) {
                    result = AjaxResult.fail("账户已锁定");
                } else if (acc.getAdmin_state() == 2) {
                    result = AjaxResult.fail("账户已注销");
                } else {
                    HashMap map = new HashMap();
                    map.put("admin_name", acc.getAdmin_name());
                    map.put("token", JwtUtil.createToken(map));
                    map.put("role", acc.getAdmin_role()); // 角色
                    result = AjaxResult.success(map);
                }
            } else {
                result = AjaxResult.fail("账户名或密码错误");
            }
        } else if ("list".equalsIgnoreCase(req.getParameter("ac"))) { // 列出所有账户
            List<Account> list = dao.findAllAccount();
            if (list != null && !list.isEmpty()) {
                result = AjaxResult.success(list);
            } else {
                result = AjaxResult.fail("查询无数据");
            }
        } else if ("reg".equalsIgnoreCase(req.getParameter("ac"))) { // 注册账户
            Account acc = new Account();
            acc.setAdmin_name(req.getParameter("admin_name"));
            acc.setAdmin_pwd(MD5Utils.md5(req.getParameter("admin_pwd")));
            String role = req.getParameter("admin_role");
            if ("管理员".equals(role)) {
                acc.setAdmin_role(0);
            } else if ("前台接待".equals(role)) {
                acc.setAdmin_role(1);
            } else if ("维修人员".equals(role)) {
                acc.setAdmin_role(2);
            } else if ("检测人员".equals(role)) {
                acc.setAdmin_role(3);
            }
            int row = dao.regist(acc);
            if (row == 1) {
                result = AjaxResult.success("注册成功");
            } else {
                result = AjaxResult.fail("注册失败");
            }
        } else if ("update".equalsIgnoreCase(req.getParameter("ac"))) { // 更改账户状态或密码
            Account acc = new Account();
            acc.setAid(Integer.parseInt(req.getParameter("aid")));
            acc.setAdmin_state(Integer.parseInt(req.getParameter("admin_state")));
            acc.setAdmin_pwd(MD5Utils.md5(req.getParameter("admin_pwd")));
            int row = dao.updateAccount(acc);
            if (row == 1) {
                result = AjaxResult.success("更新成功");
            } else {
                result = AjaxResult.fail("更新失败");
            }
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