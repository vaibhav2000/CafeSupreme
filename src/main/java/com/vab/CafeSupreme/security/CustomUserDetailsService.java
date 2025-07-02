package com.vab.CafeSupreme.security;

import com.vab.CafeSupreme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.vab.CafeSupreme.entity.UserDetails userDetails = userRepository.getUserDetailsByUsername(username);
        if(userDetails == null)
            throw new UsernameNotFoundException("USER NOT PRESENT IN THE DATABASE");

        return new CustomUserDetails(userDetails);
    }
}
