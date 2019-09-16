package com.daniel.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class DemoApplication {

	@RequestMapping("/")
    public String home() {

		String hostName = "";
		String hostIP = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
			hostIP = InetAddress.getLocalHost().getHostAddress();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Version 2.0 </br> Hello Kuberneties World!" + 
		"</br>" + "Host Name : " + hostName + ", " + 
		"Host IP : " + hostIP;

    }
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

