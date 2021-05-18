package com.example.demo.jettty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 封装一个jetty处理的父类，想要自定义处理时候只需要继承这个类实现特定方法即可
 */
public abstract class JettyHandler  extends HttpServlet {
    public abstract String pathSpec();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到一个get");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到一个post");
        super.doPost(req, resp);
    }
}
