package com.example.demo.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: OkHttp
 * @Description: okhttp实现get 和post
 * @Create by: A
 * @Date: 2021/4/9 1:19
 */
@Service
@Slf4j
public class OkHttp {

    @Autowired
    private OkHttpClient client;

    public Object doGet(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // ... handle failed request
                log.error("request error url is {}", url);
                return null;
            }
            // ... do something with response
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            // ... handle IO exception
            log.error("request error url is {},error info is {}", url, e.getMessage(), e);
            return null;
        }
    }


    public Object doPostByJson(String url, String jsonParams) {
        MediaType JSON = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, jsonParams);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("request error url is {}", url);
                return null;
            }
            // ... do something with response
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            // ... handle IO exception
            log.error("request error url is {},params is {},error info is {}", url, jsonParams, e.getMessage(), e);
            return null;
        }
    }


    @Bean
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }
}
