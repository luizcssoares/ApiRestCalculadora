package com.operacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operacao/")
public class apirestcontroller {

    @GetMapping("soma/{num1}/{num2}")
    public Integer soma(@PathVariable Integer num1, @PathVariable Integer num2){
        return num1 + num2;
    }
    
}
