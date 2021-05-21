package com.example.demo.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @ClassName: RedisConfig
 * @Description: 注入配置信息
 * @Create by: A
 * @Date: 2021/3/22 23:53
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    /**
     * 主从域名
     */
    private String host;
    /**
     * 主从端口
     */
    private String port;
    /**
     * nodes
     */
    private List<String> nodes;

}

