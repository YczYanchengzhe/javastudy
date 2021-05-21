package com.example.demo.jettty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 一个自定义handler
 */
public class TestJettyHandler extends JettyHandler{

    @Override
    public String pathSpec() {
        return "/test";
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestJettyHandler 收到一个get");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestJettyHandler 收到一个post");
        super.doPost(req, resp);
    }

}
