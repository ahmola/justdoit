package com.example.demo.controllers;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.model.TacoOrder;
import com.example.demo.model.Type;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
//
//    @ModelAttribute
//    public void addIngredientsToModel(Model model){
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LECT", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
//
//        Type[] types = Type.values();
//        for (Type type : types){
//            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//        }
//    }
//
//    @ModelAttribute(name = "tacoOrder")
//    public TacoOrder order(){
//        return new TacoOrder();
//    }
//
//    @ModelAttribute(name = "taco")
//    public Taco taco(){
//        return new Taco();
//    }
//
//    @GetMapping
//    public String showDesignForm(){
//
//        log.info("Taco Design Home page Open");
//
//        return "design";
//    }
//
//    @PostMapping
//    public String processTaco(
//            @Valid Taco taco, Errors errors,
//            @ModelAttribute TacoOrder tacoOrder){
//
//        if(errors.hasErrors()){
//            return "design";
//        }
//
//        tacoOrder.addTaco(taco);
//        log.info("Processing Taco: {}", taco);
//
//        return "redirect:/orders/current";
//    }
//
//    private Iterable<Ingredient> filterByType(
//            List<Ingredient> ingredients, Type type
//    ){
//        return ingredients
//                .stream()
//                .filter(x -> x.getType().equals(type))
//                .collect(Collectors.toList());
//    }
}