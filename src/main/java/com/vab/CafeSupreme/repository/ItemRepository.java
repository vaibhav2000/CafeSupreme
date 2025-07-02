package com.vab.CafeSupreme.repository;

import com.vab.CafeSupreme.entity.ItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemDetails, Long> {
}
