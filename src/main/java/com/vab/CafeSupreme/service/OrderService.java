package com.vab.CafeSupreme.service;

import com.vab.CafeSupreme.entity.ItemDetails;
import com.vab.CafeSupreme.entity.OrderDetails;
import com.vab.CafeSupreme.entity.UserDetails;
import com.vab.CafeSupreme.enums.OrderStatus;
import com.vab.CafeSupreme.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.vab.CafeSupreme.enums.UserRole.ROLE_ADMIN;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemDetailsService itemDetailsService;

    public void orderItem(long itemId) {
        UserDetails loggedUser = userService.getLoggedInUser();

        if (ROLE_ADMIN.name().equals(loggedUser.getRole()))
            return;

        ItemDetails itemDetails = itemDetailsService.getSpecificItem(itemId);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUserDetails(loggedUser);
        orderDetails.setItemDetails(itemDetails);
        orderDetails.setTimeStamp(LocalDateTime.now());
        orderDetails.setStatus(OrderStatus.INITIATED);

        orderRepository.save(orderDetails);
    }

    public List<OrderDetails> getAllOrders() {
        return orderRepository.findAll();
    }


    public OrderDetails getSpecificOrder(long orderId) {
        return orderRepository.getReferenceById(orderId);
    }

    public void updateOrderStatus(long orderId) {
        OrderDetails orderDetails = getSpecificOrder(orderId);

        if (orderDetails.getStatus().equals(OrderStatus.INITIATED)) {
            orderDetails.setStatus(OrderStatus.PROCESSING);
        } else if (orderDetails.getStatus().equals(OrderStatus.PROCESSING)) {
            orderDetails.setStatus(OrderStatus.FINISHED);
        }

        orderRepository.save(orderDetails);
    }
}
