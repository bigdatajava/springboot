package com.itmayiedu.controller;

import com.itmayiedu.service.AtomikosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 */
@RestController
public class AtomikosController {

    @Autowired
    private AtomikosService service;

    @RequestMapping("/atomikos")
    public Integer addUser(String name,Integer age){

        return service.addUser(name,age);
    }


}