package com.example.BuyNothingAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BuyNothingAPI.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByOfferId(Long offerId);
    List<Match> findByRequestId(Long requestId);
//    List<Match> findByMatchId(Long matchId);
}