package com.example.BuyNothingAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BuyNothingAPI.model.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findByOfferId(Long offerId);
    List<Exchange> findByRequestId(Long requestId);
}