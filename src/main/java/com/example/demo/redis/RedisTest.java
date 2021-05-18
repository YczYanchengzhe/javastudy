package com.example.demo.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RedisTest
 * @Description:
 * @Create by: A
 * @Date: 2021/3/22 23:55
 */
@Service
@Slf4j
public class RedisTest {
    @Autowired
    private RedisService redisService;

    public void test() {
        redisService.setStringByCluster("aa", "123");
        redisService.setStringByJedisPool("aa", "1234");

        String clusterResult = redisService.getStringByCluster("aa");

        String poolResult = redisService.getStringByJedisPool("aa");

        log.info("clusterResult is {}", clusterResult);
        log.info("poolResult is {}", poolResult);

    }


}
