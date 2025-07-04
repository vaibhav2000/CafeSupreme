package com.vab.CafeSupreme.service;

import com.vab.CafeSupreme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.vab.CafeSupreme.entity.UserDetails;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void addUser(UserDetails userDetails)
    {
        userRepository.save(userDetails);
    }

    public UserDetails getLoggedInUser()
    {
       return userRepository.getUserDetailsByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
