package com.springbootstudy.demo1.appconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class AppConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppConfigClientApplication.class, args);
	}

}

@RefreshScope
@RequestMapping("/config")
@RestController
class ClientController {
	
	@Value("${message}")
	private String message;
	
	@GetMapping("/message")
	public String message() {
		System.out.println(message);
		return message;
	}
}
