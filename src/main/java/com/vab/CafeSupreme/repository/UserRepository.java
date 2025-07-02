package com.vab.CafeSupreme.repository;

import com.vab.CafeSupreme.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails getUserDetailsByUsername(String username);
}
