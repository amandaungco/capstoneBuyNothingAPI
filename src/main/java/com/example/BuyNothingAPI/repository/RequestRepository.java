package com.example.BuyNothingAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BuyNothingAPI.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByUserId(Long userId);
    
    @Query("SELECT request FROM Requests requests WHERE request.title LIKE %?1% AND request.status = 'ACTIVE'")
    List<Request>findActiveRequestsByTitle(String title);
    
}