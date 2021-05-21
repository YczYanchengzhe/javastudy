package com.example.demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengzhe yan
 * @description http 请求服务
 * @date 2021/4/7 10:14 上午
 */
@Component
@Slf4j
public class HttpService {

	/**
	 * http 发送get请求
	 *
	 * @param url
	 */
	public static String httpGet(String url) throws Exception {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);

			client = HttpClients.createDefault();
			response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			} else {
				throw new Exception("http get error : " + url + " error number :" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
	}


	/**
	 * Post发送form表单数据
	 */
	public static String httpPostForm(String url, String clusterName, String message) throws Exception {

		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 创建一个提交数据的容器
			List<BasicNameValuePair> parames = new ArrayList<>();
//            parames.add(new BasicNameValuePair("mode", "1"));
//            parames.add(new BasicNameValuePair("clusterName", clusterName));
//            parames.add(new BasicNameValuePair("message", message));
			parames.add(new BasicNameValuePair("token", message));
			parames.add(new BasicNameValuePair("cluster_id", clusterName));

			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(parames, "UTF-8"));

			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			} else {
				throw new Exception("http post error error number :" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			System.out.println("http error info: cluster :"+ clusterName + "message : " + message + " exception : " + e);
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
	}


	//Post发送json数据 未测试
	public static String httpPostJson(String url , String jsonString) {
		try {
			CloseableHttpClient client = null;
			CloseableHttpResponse response = null;
			try {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
				httpPost.setEntity(new StringEntity(jsonString,
						ContentType.create("text/json", "UTF-8")));

				client = HttpClients.createDefault();
				response = client.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
//                System.out.println(result);
				return result;
			} finally {
				if (response != null) {
					response.close();
				}
				if (client != null) {
					client.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static final RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000).setSocketTimeout(2000).build();


	public static void sendPost(String url, String body) throws Exception {
		// 创建默认的httpClient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httppost
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Content-type", "application/json; charset=utf-8");
			httppost.setConfig(requestConfig);
			// 向POST请求中添加消息实体
			StringEntity se = new StringEntity(body, "UTF-8");
			httppost.setEntity(se);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String strRes = EntityUtils.toString(entity, "UTF-8");
					System.out.println(strRes);
				}
			} finally {
				response.close();
			}
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch(IOException e) {
			}
		}
	}


}
