package com.itmayiedu.devtools;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Devtools {

    @RequestMapping("/test1")
    public String test1(){

        return "test1";
    }

    @RequestMapping("/test2")
    public String test2(){

        return "test2";
    }

    @RequestMapping("/test3")
    public String test3(){
        return "test3";
    }


}


