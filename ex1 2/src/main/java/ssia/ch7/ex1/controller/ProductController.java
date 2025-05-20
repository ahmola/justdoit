package ssia.ch7.ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger logger = Logger.getLogger(ProductController.class.getName());

    @PostMapping("/add")
    public String add(@RequestParam String name){
        logger.info("Adding product " + name);
        return "main";
    }
}
