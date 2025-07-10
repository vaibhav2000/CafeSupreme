package com.vab.CafeSupreme.controller;

import com.vab.CafeSupreme.dto.ReviewDto;
import com.vab.CafeSupreme.entity.OrderDetails;
import com.vab.CafeSupreme.enums.OrderStatus;
import com.vab.CafeSupreme.service.ItemDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vab.CafeSupreme.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemDetailsService itemDetailsService;

    @PostMapping("/update")
    public String updateOrderStatusPost(@RequestParam("orderId") long orderId) {
        orderService.updateOrderStatus(orderId);
        return "redirect:/user/orders";
    }

    @GetMapping("/review")
    public String reviewOrder(@RequestParam("orderId") long orderId, Model model) {
        OrderDetails orderDetails = orderService.getSpecificOrder(orderId);

        if (!orderDetails.getStatus().equals(OrderStatus.FINISHED)) {
            return "redirect:/user/orders";
        }

        model.addAttribute("orderDetails",orderDetails);
        model.addAttribute("reviewDto",new ReviewDto());

        return "reviewpage.html";
    }

    @PostMapping("/review")
    public String reviewOrderPost(@RequestParam("orderId") long orderId, @ModelAttribute("reviewDto") ReviewDto reviewDto)
    {
        OrderDetails orderDetails = orderService.getSpecificOrder(orderId);
        orderDetails.setRating(reviewDto.getRating());
        orderDetails.setRewiewComment(reviewDto.getReviewComment());

        //update the rating of the item
        itemDetailsService.updateItemRating(orderDetails.getItemDetails(), orderDetails.getRating());

        return "redirect:/user/orders";
    }
}
