package com.ccsu.servicetask.web;

import com.ccsu.servicetask.dao.FactoryDao;
import com.ccsu.servicetask.entity.AjaxResult;
import com.ccsu.servicetask.entity.Factory;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/FactoryServlet")
public class FactoryServlet extends HttpServlet {
    FactoryDao dao = new FactoryDao();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        //跨域允许
        resp.addHeader("Access-Control-Allow-Origin", "*");
        //动作标识
        String action = request.getParameter("ac");
        AjaxResult result = null;
        if ("list".equalsIgnoreCase(action)) {
            //查询数据
            List<Factory> list = dao.findAllFactory();

            if (list != null && list.size() != 0) {
                result = AjaxResult.success(list);
            } else {
                result = AjaxResult.fail("查无此结果");
            }
        } else if ("save".equalsIgnoreCase(action)) {
            //取得任务id
            String fac_name = request.getParameter("fac_name");
            String fac_phone = request.getParameter("fac_phone");
            String fac_item = request.getParameter("fac_item");
            //组装
            Factory fac = new Factory(fac_name, fac_phone, fac_item);
            //保存
            int row = dao.saveFac(fac);
            if (row == 1) {
                result = AjaxResult.success(1, "保存成功");
            } else {
                result = AjaxResult.fail(0, "保存失败");
            }
        } else if ("update".equalsIgnoreCase(request.getParameter("ac"))) {
            int row = dao.updateFactory(Integer.parseInt(request.getParameter("fac_id")), Integer.parseInt(request.getParameter("fac_state")));
            if (row == 1) {
                result = AjaxResult.success("更新成功");
            } else {
                result = AjaxResult.fail("更新失败");
            }
        } else {
            result = AjaxResult.fail("ac操作符无效");
        }

        //返回响应数据json
        String json = gson.toJson(result);
        //输出
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
