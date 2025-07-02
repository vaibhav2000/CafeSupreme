package com.vab.CafeSupreme.service;

import com.vab.CafeSupreme.entity.ItemDetails;
import com.vab.CafeSupreme.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDetailsService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemDetails addItem(ItemDetails itemDetails) {
        return itemRepository.save(itemDetails);
    }

    public List<ItemDetails> getAllItems()
    {
        return itemRepository.findAll();
    }
}
