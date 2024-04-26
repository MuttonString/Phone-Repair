package com.ccsu.servicetask.web;

import com.ccsu.servicetask.entity.AjaxResult;
import com.ccsu.servicetask.utils.JwtUtil;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String token = req.getParameter("token");
        boolean bool = JwtUtil.verifyToken(token);
        AjaxResult result = AjaxResult.success(1, bool);
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
