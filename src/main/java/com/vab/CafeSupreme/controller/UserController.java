package com.vab.CafeSupreme.controller;

import com.vab.CafeSupreme.dto.UserDTO;
import com.vab.CafeSupreme.entity.UserDetails;
import com.vab.CafeSupreme.enums.UserRole;
import com.vab.CafeSupreme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerUser(Model model) {

        if (userService.getLoggedInUser() != null)
            return "redirect:/home";

        model.addAttribute("userDto", new UserDTO());
        return "register.html";
    }

    @PostMapping("/register")
    public String registerUserPost(Model model, @ModelAttribute("userDto") UserDTO userDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDTO.getFirstName());
        userDetails.setLastName(userDTO.getLastName());
        userDetails.setEmail(userDTO.getEmail());
        userDetails.setUsername(userDTO.getUsername());
        userDetails.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDetails.setRole(UserRole.ROLE_USER.name());
        userDetails.setMobileNumber(userDTO.getMobileNumber());
        userService.addUser(userDetails);

        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginUser(Model model, @ModelAttribute("message") String message) {
        if (userService.getLoggedInUser() != null)
            return "redirect:/home";

        model.addAttribute("message", message);
        return "login.html";
    }
}
