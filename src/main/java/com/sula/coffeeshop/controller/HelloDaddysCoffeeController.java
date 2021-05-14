package com.sula.coffeeshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloDaddysCoffeeController {


    @RequestMapping("/")
    public String getHelloWorld(){
        return "<center><h1>Hello World Daddy's Coffee.......</h1></center>";
    }


}
