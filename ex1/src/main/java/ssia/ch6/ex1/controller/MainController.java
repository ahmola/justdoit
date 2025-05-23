package ssia.ch6.ex1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ssia.ch6.ex1.service.ProductService;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    public String main(Authentication a, Model model){
        model.addAttribute("username", a.getName());
        model.addAttribute("products", productService.findAll());
        return "main";

    }
}
