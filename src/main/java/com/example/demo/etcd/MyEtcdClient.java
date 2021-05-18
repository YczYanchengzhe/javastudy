package com.example.demo.etcd;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeysResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengzhe yan
 * @description
 * @date 2021/4/12 10:51 上午
 */
public class MyEtcdClient {

	public EtcdClient getEtcdClient(String connectConfig) {
		List<URI> uris = parse(connectConfig);
		return new EtcdClient(uris.toArray(new URI[]{}));
	}

	public EtcdClient getEtcdClient(String connectConfig, String userName, String password) {
		List<URI> uris = parse(connectConfig);
		return new EtcdClient(userName, password, uris.toArray(new URI[]{}));
	}

	/**
	 * 进行ip：port ： parse
	 *
	 * @param config 10.148.10.26:2379,10.135.5.78:2379,10.135.7.169:2379,10.135.2.232:2379,10.148.13.92:2379
	 * @return
	 */
	public static List<URI> parse(String config) {
		List<URI> uris = new ArrayList<>();
		String[] ipPorts = config.split(",");
		for (String address : ipPorts) {
			String ip = address.split(":")[0];
			String port = address.split(":")[1];
			uris.add(URI.create(new StringBuilder("http://").append(ip)
					.append(":")
					.append(port)
					.toString()));
		}
		return uris;
	}

}
