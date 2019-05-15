package com.springbootstudy.demo1.appclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient	
@SpringBootApplication
@ComponentScan
public class AppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppClientApplication.class, args);
	}

}

@Configuration
class Config {
	@LoadBalanced
	@Bean
	public RestTemplate restTemplateLoadBalanced() {
		return new RestTemplate(); // single instance for the whole application. easy to manipulate
	}

	@Primary
	@Bean
	public RestTemplate restTemplatePrimary() {
		return new RestTemplate(); // single instance for the whole application. easy to manipulate
	}

}

@RestController
@RequestMapping("/test")
class ClientController {

	@Autowired
	private RestTemplate restTemplatePrimary;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplateLoadBalanced;

	@GetMapping("/1")
	public String printHello1() {
		return "Hello world from client";
	}

	@GetMapping("/2")
	public String printHello2() {
		// client-server
		System.out.println("inclient");
		String url = "http://docker-app-server-container:8010/printHello";
		return restTemplateLoadBalanced.getForObject(url, String.class);
	}
	
	@GetMapping("/3")
	public String printHello3() {
		// client-zuul-server
		System.out.println("inclient");
		String url = "http://docker-app-zuul-container:8030/test/printHello";
		return restTemplateLoadBalanced.getForObject(url, String.class);
	}

//	@GetMapping("/printHello")
//	public String printHello() {
//		// direct hit with host name
//		String url = "http://localhost:8030/test/printHello";
//		return restTemplatePrimary.getForObject(url, String.class);
//	}
//
//	@GetMapping("/printHelloName")
//	public String printHelloWithName() {
//		// use zuul as a gatweway which redirects hit to service based on eureka name
//		String url = "http://APP-ZUUL/test/printHello/aish";
//		return restTemplateLoadBalanced.getForObject(url, String.class);
//	}
//
//	@GetMapping("/getMessageFromNodeService")
//	public String getMesssageFromNodeService() {
//		String url = "http://APP-ZUUL/node/getMessage";
//		return restTemplateLoadBalanced.getForObject(url, String.class);
//	}

}
