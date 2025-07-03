package com.vab.CafeSupreme.controller;

import com.vab.CafeSupreme.dto.ItemDto;
import com.vab.CafeSupreme.entity.ItemDetails;
import com.vab.CafeSupreme.entity.UserDetails;
import com.vab.CafeSupreme.enums.UserRole;
import com.vab.CafeSupreme.service.ItemDetailsService;
import com.vab.CafeSupreme.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/user/login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("itemList", itemDetailsService.getAllItems());
        return "homepage.html";
    }

    @GetMapping("/item/add")
    public String addItem(Model model) {

        UserDetails userDetails = userService.getLoggedInUser();
        if (userDetails == null || !userDetails.getRole().equals(UserRole.ROLE_ADMIN.name())) {
            return "redirect:/home";
        }

        model.addAttribute("itemDto", new ItemDto());
        return "additem.html";
    }

    @PostMapping("/item/add")
    public String addItemPost(Model model, @RequestParam("image") MultipartFile file, @ModelAttribute("itemDto") ItemDto itemDto) throws Exception {

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemCalories(itemDto.getItemCalories());
        itemDetails.setItemName(itemDto.getItemName());
        itemDetails.setItemEnabled(true);
        itemDetails.setItemDescription(itemDto.getItemDescription());
        itemDetails.setItemPrice(itemDto.getItemPrice());
        itemDetailsService.saveItem(itemDetails, file);
        return "redirect:/home";
    }

    @PostMapping("/item/delete")
    public String deleteItem(@RequestParam("itemId") long itemId) {
        itemDetailsService.deleteItem(itemId);
        return "redirect:/home";
    }
}
