package org.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/post")
    public String post(@RequestBody String requestBody){
        return requestBody;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        return "deleted:" + id;
    }
}
