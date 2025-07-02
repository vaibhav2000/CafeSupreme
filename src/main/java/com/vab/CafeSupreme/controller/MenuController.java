package com.vab.CafeSupreme.controller;

import com.vab.CafeSupreme.dto.ItemDto;
import com.vab.CafeSupreme.entity.ItemDetails;
import com.vab.CafeSupreme.service.ItemDetailsService;
import com.vab.CafeSupreme.util.FileManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MenuController {
    @Autowired
    private ItemDetailsService itemDetailsService;

    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("itemList", itemDetailsService.getAllItems());
        return "homepage.html";
    }

    @GetMapping("/item/add")
    public String addItem(Model model) {
        model.addAttribute("itemDto", new ItemDto());
        return "additem.html";
    }

    @PostMapping("/item/add")
    public String addItemPost(Model model, @RequestParam("image") MultipartFile file, @ModelAttribute("itemDto") ItemDto itemDto) {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemCalories(itemDto.getItemCalories());
        itemDetails.setItemName(itemDto.getItemName());
        itemDetails.setItemEnabled(true);
        itemDetails.setItemDescription(itemDto.getItemDescription());
        itemDetails.setItemPrice(itemDto.getItemPrice());

        ItemDetails savedItem = itemDetailsService.addItem(itemDetails);
        FileManagerUtil.saveFile(file, savedItem.getItemId() + "");

        return "redirect:/home";
    }
}
