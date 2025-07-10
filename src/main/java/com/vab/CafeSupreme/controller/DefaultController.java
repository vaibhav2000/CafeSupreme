package com.vab.CafeSupreme.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vab.CafeSupreme.entity.ItemDetails;
import com.vab.CafeSupreme.service.ItemDetailsService;

@Controller
public class DefaultController { 
    @Autowired
    private ItemDetailsService itemDetailsService;
	
    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/user/login";
    }

    @GetMapping("/home")
    public String homePage(@RequestParam(name = "query", required = false) String query, Model model) {

        List<ItemDetails> itemDetailsList = itemDetailsService.getAllItems();

        if (!Objects.isNull(query)) {
            String finalQuery = query.toLowerCase();
            itemDetailsList = itemDetailsList.stream().filter(item -> item.getItemName().toLowerCase().contains(finalQuery) ||
                    item.getItemDescription().toLowerCase().contains(finalQuery)).toList();
            
            model.addAttribute("query", query);
        }

        model.addAttribute("itemList", itemDetailsList);
        return "homepage.html";
    }

}
