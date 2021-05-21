package com.example.demo;

import com.example.demo.excelhelp.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ExcelService excelService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run(String... args) throws Exception {
		System.out.println("s");
	}

}
