package certus.edu.pe.msadminregistryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class MsadminregistryserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsadminregistryserverApplication.class, args);
	}

}
