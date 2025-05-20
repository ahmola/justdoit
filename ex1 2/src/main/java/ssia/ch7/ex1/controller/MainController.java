package ssia.ch7.ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/page")
    public String main(){
        return "main";
    }
}
