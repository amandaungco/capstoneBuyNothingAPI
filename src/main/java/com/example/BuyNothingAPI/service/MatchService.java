package com.example.BuyNothingAPI.service;

import com.example.BuyNothingAPI.model.Match;
import com.example.BuyNothingAPI.model.Offer;
import com.example.BuyNothingAPI.model.Request;
import com.example.BuyNothingAPI.repository.MatchRepository;
import com.example.BuyNothingAPI.repository.OfferRepository;
import com.example.BuyNothingAPI.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Scope(value = "prototype")
@Component(value = "matchService")
public class MatchService {


    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private MatchRepository matchRepository;

    public Set<Match> findMatches(Offer offer) {
        String query = offer.getTitle();
        List<Request> matchedRequests = requestRepository.findByTitle(query);
        Set<Match> newMatches = new HashSet<>();
        for (Request request : matchedRequests) {
            Match newMatch = new Match();
            newMatch.setOffer(offer);
            newMatch.setRequest(request);
            newMatches.add(newMatch);
        }
        matchRepository.saveAll(newMatches);
        return newMatches;


    }

    public Set<Match> findMatches(Request request) {
        String query = request.getTitle();
        List<Offer> matchedOffers = offerRepository.findByTitle(query);
        Set<Match> newMatches = new HashSet<>();
        for (Offer offer : matchedOffers) {
            Match newMatch = new Match();
            newMatch.setOffer(offer);
            newMatch.setRequest(request);
            newMatches.add(newMatch);
        }
        matchRepository.saveAll(newMatches);
        return newMatches;


    }


}
