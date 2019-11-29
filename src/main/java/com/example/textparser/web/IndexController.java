package com.example.textparser.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @PostMapping
    public String printText(){
        return "index";
    }

    @GetMapping
    public String index(){
        return "index";
    }

}
