package com.example.BuyNothingAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BuyNothingAPI.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByUserId(Long userId);


    @Query("SELECT offer FROM  Offer offer WHERE offer.title LIKE %?1% AND offer.status = 'ACTIVE'")
    List<Offer>findByTitle(String title);

//    @Query("SELECT offer FROM  Offer offer WHERE offer.id LIKE %?1% ")
//    List<Offer>findByOfferId(int id);
}