package com.operacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/operacao/")
public class apirestcontroller {

    @GetMapping("/")
    public Integer soma(){
        return 3 + 3;
    }
    
}
