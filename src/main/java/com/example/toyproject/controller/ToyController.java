package com.example.toyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
First Toy Project
 */


@Controller
public class ToyController {

    @RequestMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello");

        return "hello";
    }

    @RequestMapping("hello-mvc")
    public String helloMvc(@RequestParam("name2") String name2, Model model){
        model.addAttribute("name2",name2);

        return "hello";
    }

}
