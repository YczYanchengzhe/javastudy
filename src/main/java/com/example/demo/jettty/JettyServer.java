package com.example.demo.jettty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JettyServer {

    private Server server;

    private ServletContextHandler servletContextHandler;

    @Autowired
    private JettyServerConfig jettyServerConfig;

    /**
     * 初始化jetty serve ： 第一步
     */
    public void initialize() {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMinThreads(jettyServerConfig.getJettyMinThreads());
        threadPool.setMaxThreads(jettyServerConfig.getJettyMaxThreads());

        server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setHost(jettyServerConfig.getHost());
        connector.setPort(jettyServerConfig.getPort());
        connector.setIdleTimeout(jettyServerConfig.getJettyIdleTimeOut());
        connector.setAcceptorPriorityDelta(jettyServerConfig.getJettyAcceptorPriorityDelta());
        connector.setAcceptQueueSize(jettyServerConfig.getJettyAcceptQueueSize());
        server.setConnectors(new Connector[]{connector});

        servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        //标识这个handler要处理的路径
        servletContextHandler.setContextPath(jettyServerConfig.getContextPath());
        //将其交给jetty的server
        server.setHandler(servletContextHandler);
    }

    /**
     * 对其添加处理的handler ： 第二步
     * @param handler 处理handler
     */
    public void addHandler(JettyHandler handler) {
        ServletHolder servletHolder = new ServletHolder();
        servletHolder.setServlet(handler);
        servletContextHandler.addServlet(servletHolder, handler.pathSpec());
    }

    /**
     * 启动jetty服务 ： 第三步
     * @throws Exception
     */
    public void startJettyServe() throws Exception {
        // 启动 serve
        server.start();
    }


}
