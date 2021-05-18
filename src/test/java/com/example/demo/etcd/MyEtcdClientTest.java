package com.example.demo.etcd;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author chengzhe yan
 * @description
 * @date 2021/4/12 11:29 上午
 */
public class MyEtcdClientTest {

	private EtcdKeysResponse response;

	@Test
	public void testClient() throws Exception {
		MyEtcdClient myEtcdClient = new MyEtcdClient();
		EtcdClient client = myEtcdClient.getEtcdClient( "ip:port","root", "123456");

		System.out.println("init success");

		String sendKey = "aaa";
		String sendValue = "aaa";

		response = client.getAll().send().get();
		String returnValue2 = response.getNode().getValue();
		System.out.println(returnValue2);

		response = client.put(sendKey, sendValue).send().get();

		response = client.get("aaa").send().get();

		String returnValue = response.getNode().getValue();
		client.delete(sendKey);

//		Assert.assertEquals(sendValue, returnValue);
	}
}