package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * @ClassName: RedisTest2
 * @Description:
 * @Create by: A
 * @Date: 2021/3/23 0:10
 */
@Service
public class RedisService {
    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private JedisPool jedisPool;

    public void setStringByCluster(String key, String value) {
        jedisCluster.set(key, value);
    }

    public void setStringByJedisPool(String key, String value) {
        jedisPool.getResource().set(key, value);
    }


    public String getStringByCluster(String key) {
        return jedisCluster.get(key);
    }

    public String getStringByJedisPool(String key) {
        return jedisPool.getResource().get(key);
    }

    @Bean
    public JedisPool redisPoolFactory() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return new JedisPool(jedisPoolConfig, redisConfig.getHost(), Integer.parseInt(redisConfig.getPort()));
    }


    @Bean
    public JedisCluster redisClusterFactory() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return new JedisCluster(new HashSet<HostAndPort>() {{
            for (String node : redisConfig.getNodes()) {
                String ip = node.split(":")[0];
                int port = Integer.parseInt(node.split(":")[1]);
                add(new HostAndPort(ip, port));
            }
        }}, jedisPoolConfig);
    }
}
