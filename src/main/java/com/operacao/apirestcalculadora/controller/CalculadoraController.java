package com.operacao.apirestcalculadora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    @RequestMapping("/")
	private String Soma(){
		return "A soma e 8";
	}
    
}
