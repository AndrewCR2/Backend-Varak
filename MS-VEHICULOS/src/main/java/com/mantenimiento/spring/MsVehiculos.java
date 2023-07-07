package com.mantenimiento.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsVehiculos {

	public static void main(String[] args) {
		SpringApplication.run(MsVehiculos.class, args);
	}

}
