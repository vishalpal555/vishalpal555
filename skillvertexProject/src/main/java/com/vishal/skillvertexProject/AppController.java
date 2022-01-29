package com.vishal.skillvertexProject;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class AppController {
    
    public String  start(){
        return "index";
    }   
}
