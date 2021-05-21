package com.example.demo.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author chengzhe yan
 * @description restTemplate方式发送请求
 * @date 2021/4/7 10:17 上午
 */
@Component
@Slf4j
public class RestTemplateService<T> {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 表单格式post请求
     *
     * @param url   请求url
     * @param param 参数
     * @return
     */
    public Object postForEntityByForm(String url, Map<String, String> param) {
        //设置类型 表单格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.setAll(param);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            //返回响应
            return responseEntity.getBody();
        } else {
            log.error("post message error , url is {} ,error params is {},error code is {} , error info is {}",
                    url, param, responseEntity.getStatusCodeValue(), responseEntity.getBody());
            return null;
        }
    }

    /**
     * 发送 post请求 表单格式
     *
     * @param url        请求url
     * @param param      参数
     * @param returnType 返回值类型
     * @return
     */
    public T postForObjectByJson(String url, String param, Class<T> returnType) {
        //设置类型 json 格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(param, headers);
        return restTemplate.postForObject(url, requestEntity, returnType);
    }

    /**
     * 根据url 发送get请求 ,可以对于响应头做一定处理
     *
     * @param url 请求的url
     * @return 返回信息
     */
    public Object getForEntity(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        //获取消息头
        HttpHeaders headers = responseEntity.getHeaders();
        //判断请求状态码
        if (responseEntity.getStatusCodeValue() == 200) {
            //获取消息体
            return responseEntity.getBody();
        } else {
            log.error("get message error , url is {} ,error code is {} , error info is {}",
                    url, responseEntity.getStatusCodeValue(), responseEntity.getBody());
            return null;
        }
    }

    /**
     * 根据url 发送get请求 ,只关注响应体
     *
     * @param url        请求的url
     * @param returnType 返回参数类型
     * @return 返回信息
     */
    public T getForObject(String url, Class<T> returnType) {
        return restTemplate.getForObject(url, returnType);
    }

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
