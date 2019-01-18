package com.itmayiedu.undertor;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UndertorController {

    @RequestMapping("/undertor")
    public String test1(){

        return "test1";
    }
}


