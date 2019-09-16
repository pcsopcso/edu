package org.daniel.netflix.distributedtracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedTracingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTracingServerApplication.class, args);
	}
}
