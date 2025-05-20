package com.example.demo.Controller;

import com.example.demo.DTO.GoodsDTO;
import com.example.demo.Repository.GoodsRepository;
import com.example.demo.Service.GoodsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsRepository goodsRepository;

    @GetMapping("/goods/new")
    public String addGoods(Model model) {
        model.addAttribute("GoodsDTO", new GoodsDTO());
        return "item/itemForm";
    }

    @PostMapping("/goods/new")
    public String goodsNew(@Validated GoodsDTO goodsDTO, BindingResult bindingResult,
                           Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        // check if there is no default image
        if(itemImgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage", "First Image is needed");
            return "item/itemForm";
        }

        // delete empty object in list
        for(int i = itemImgFileList.size() - 1; i >= 0; i--){
            if(itemImgFileList.get(i).isEmpty())
                itemImgFileList.remove(i);
        }

        // save
        try{
            goodsService.saveItem(goodsDTO, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "Error occur while adding Goods");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping("/goods/{goodsName}")
    public String itemDTL(@PathVariable("goodsName") String goodsName, Model model){
        try {
            Long item_id = goodsRepository.findByGoodsName(goodsName).get(0).getId();
            GoodsDTO goodsDTO = goodsService.getGoodsDTL(item_id);
            model.addAttribute("GoodsDTO", goodsDTO);
        }catch (EntityNotFoundException e){
            model.addAttribute("There is no Goods");
            model.addAttribute("GoodsDTO", new GoodsDTO());
            return "item/itemForm";
        }

        return "item/itemForm";
    }

    @PostMapping("/goods/{goodsName}")
    public String itemUpdate(@Validated GoodsDTO goodsDTO, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
                             @RequestParam("itemImgIds") List<Long> itemImgIdsList,
                             Model model){
        System.out.println(goodsDTO.toString());
        if(bindingResult.hasErrors()){

            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage", "First Image is Essential");
            return "item/itemForm";
        }

        try{
            goodsService.updateItem(goodsDTO, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "Error occur while Updating");
            return "item/itemForm";
        }

        return "redirect:/";
    }

}
