package com.vab.CafeSupreme.repository;

import com.vab.CafeSupreme.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
}
