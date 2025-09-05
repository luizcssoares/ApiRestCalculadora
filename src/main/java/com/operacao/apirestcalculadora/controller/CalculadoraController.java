package com.operacao.apirestcalculadora.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class CalculadoraController {

    @RequestMapping("/soma/{num1}/{num2}")
	private Integer Soma(@PathVariable Integer num1, @PathVariable Integer num2){
		return num1 + num2;
	}
    
}
