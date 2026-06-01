package com.ps.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PsExcelSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsExcelSystemApplication.class, args);
	}

}
