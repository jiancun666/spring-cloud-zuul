package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @GetMapping("/hello")
    public String hello(){
        return "hello,world.";
    }

    @GetMapping("add")
    public int add(){return 10;}

    @GetMapping("/SS")
    public String SS(){return "PJL是骚狗";}
}
