package com.mantenimiento.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsSede {

	public static void main(String[] args) {
		SpringApplication.run(MsSede.class, args);
	}

}
