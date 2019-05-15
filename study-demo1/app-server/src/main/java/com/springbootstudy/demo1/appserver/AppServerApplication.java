package com.springbootstudy.demo1.appserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class AppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppServerApplication.class, args);
	}

}

@RestController
@RequestMapping("/printHello")
class TestResource {

	@GetMapping("")
	public String printHello() {
		return "Hello world ";
	}

	@GetMapping("/{name}")
	public String printHelloWithName(@PathVariable("name") String name) {
		return "Hello world " + name;
	}

}
