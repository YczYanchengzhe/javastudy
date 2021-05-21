package com.example.demo.shell;

import com.sun.org.apache.xpath.internal.operations.String;
import lombok.SneakyThrows;

import java.io.InputStream;

/**
 * @author chengzhe yan
 * @description
 * @date 2021/5/6 1:03 下午
 */
public class ShellTest {

	@SneakyThrows
	public static void main(String[] args) {
		Process process = Runtime.getRuntime().exec("echo hello");
		InputStream inputStream = process.getInputStream();
		byte[] b = new byte[1024];
		inputStream.read(b);

		System.out.println(b.toString());
	}
}
