package com.personal.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsPersonal {

	public static void main(String[] args) {
		SpringApplication.run(MsPersonal.class, args);
	}

}
