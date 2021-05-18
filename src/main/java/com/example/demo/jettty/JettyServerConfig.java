package com.example.demo.jettty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@NoArgsConstructor
@Configuration
public class JettyServerConfig {
    private String host = "127.0.0.1";
    private int port = 9001;
    private String contextPath = "/";
    private int jettyMinThreads = 1;
    private int jettyMaxThreads = 200;
    private long jettyIdleTimeOut = 30000;
    private int jettyAcceptorPriorityDelta = 0;
    private int jettyAcceptQueueSize = 0;
}
