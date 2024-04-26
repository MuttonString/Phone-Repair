package com.ccsu.servicetask.web;

import com.ccsu.servicetask.dao.FittingsDao;
import com.ccsu.servicetask.entity.AjaxResult;
import com.ccsu.servicetask.entity.Fittings;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/FittingsServlet")
public class FittingsServlet extends HttpServlet {
    Gson gson = new Gson();
    FittingsDao dao = new FittingsDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");

        List<Fittings> list = dao.findAllFittings();
        AjaxResult result;
        if ("list".equalsIgnoreCase(req.getParameter("ac"))) {
            if (list != null && !list.isEmpty()) {
                result = AjaxResult.success(list);
            } else {
                result = AjaxResult.fail("查询无数据");
            }
        } else if ("update".equalsIgnoreCase(req.getParameter("ac"))) {
            int row = dao.updateFittings(Integer.parseInt(req.getParameter("fit_id")), Integer.parseInt(req.getParameter("fit_qty")));
            if (row == 1) {
                result = AjaxResult.success("更新成功");
            } else {
                result = AjaxResult.fail("更新失败");
            }
        }
        else {
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
