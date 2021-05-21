package com.example.demo.http;


import com.example.demo.DemoApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: OkHttpTest
 * @Description: 测试
 * @Create by: A
 * @Date: 2021/4/9 1:28
 */
@Component
public class OkHttpTest extends DemoApplicationTests {

    @Autowired
    private OkHttp okHttp;

    @Test
    public void doGet() {
        String response = (String) okHttp.doGet("http://www.baidu.com");
        Assert.assertNotNull(response);
    }

    @Test
    public void doPostByJson() {
        String response = (String) okHttp.doGet("http://www.baidu.com");
        Assert.assertNotNull(response);
    }
}