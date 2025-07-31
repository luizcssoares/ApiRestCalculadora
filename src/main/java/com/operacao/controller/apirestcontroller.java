package com.operacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apirestcontroller {
    @GetMapping("/")
    public String soma(){
        return "O Valor da Soma e 3";
    }
    
}
