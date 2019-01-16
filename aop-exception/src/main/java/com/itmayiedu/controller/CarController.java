package com.itmayiedu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csc/springboot/aop")
public class CarController {


    @RequestMapping("/exception")
    public String query(){

        int i =1/0;
        return null;
    }


}
