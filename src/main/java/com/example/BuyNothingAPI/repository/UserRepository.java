package com.example.BuyNothingAPI.repository;


import com.example.BuyNothingAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM  User user WHERE user.email LIKE %?1%")
    List<User> findByEmail(String email);
}

