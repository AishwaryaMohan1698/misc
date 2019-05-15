package com.springcloudproject.appconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
public class AppConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppConfigClientApplication.class, args);
	}

}

@RefreshScope
@RequestMapping("/test")
@RestController
class ClientController {

	@Value("${message: Default message}")
	private String message;

	@GetMapping("/message")
	public String message() {
		System.out.println(message);
		return message;
	}
}
