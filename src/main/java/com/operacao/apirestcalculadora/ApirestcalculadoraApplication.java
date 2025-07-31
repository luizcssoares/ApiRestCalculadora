package com.operacao.apirestcalculadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApirestcalculadoraApplication  extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(ApirestcalculadoraApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ApirestcalculadoraApplication.class, args);
	}
	@RequestMapping("/")
	private String Soma(){
		return "Soma e 20";
	}
}
