package com.vab.CafeSupreme.service;

import com.vab.CafeSupreme.entity.ItemDetails;
import com.vab.CafeSupreme.entity.UserDetails;
import com.vab.CafeSupreme.enums.UserRole;
import com.vab.CafeSupreme.repository.ItemRepository;
import com.vab.CafeSupreme.util.FileManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ItemDetailsService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserService userService;

    public void saveItem(ItemDetails itemDetails, MultipartFile file) throws Exception {

        UserDetails userDetails = userService.getLoggedInUser();
        if (userDetails == null || !userDetails.getRole().equals(UserRole.ROLE_ADMIN.name())) {
            throw new Exception("Not enough authority to make this change");
        }

        ItemDetails savedItem = itemRepository.save(itemDetails);
        FileManagerUtil.saveFile(file, savedItem.getItemId() + "");
    }

    public List<ItemDetails> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemDetails getSpecificItem(long itemId) {
        return itemRepository.findById(itemId).get();
    }

    public void deleteItem(long itemId) {
        FileManagerUtil.deleteFile(itemId + "");
        itemRepository.deleteById(itemId);
    }

    public void updateItemRating(ItemDetails itemDetails, Integer newRating) {
        itemDetails.setRatingCounter(itemDetails.getRatingCounter()+1);
        Double calculatedRating = (itemDetails.getRating() + newRating) / itemDetails.getRatingCounter();
        itemDetails.setRating(calculatedRating);
        itemRepository.save(itemDetails);
    }
}
