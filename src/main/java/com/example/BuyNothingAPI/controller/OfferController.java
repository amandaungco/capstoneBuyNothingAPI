package com.example.BuyNothingAPI.controller;


import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.example.BuyNothingAPI.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BuyNothingAPI.exception.ResourceNotFoundException;
import com.example.BuyNothingAPI.model.Offer;
import com.example.BuyNothingAPI.repository.OfferRepository;
import com.example.BuyNothingAPI.repository.UserRepository;
import com.example.BuyNothingAPI.service.MatchService;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private MatchService matchService;


    @GetMapping("/users/{userId}/offers")
    public List<Offer> getOffersByUserId(@PathVariable Long userId) {
        return offerRepository.findByUserId(userId);
    }

    @GetMapping("/offers")
    public Page<Offer> getOffers(Pageable pageable) {
        Page <Offer> offers = offerRepository.findAll(pageable);
        return offers;

    }
//    @GetMapping("/users/{userId}/offers/{offerId}")
//    public List<Offer> getSingleOfferByOfferId(@PathVariable Long userId) {
//        return offerRepository.findByOfferId(offer);
//    }

//    public List getOffers(@RequestParam (value = "title", required = false) String title) {
//    	return offerRepository.findBy
//    }

    @PostMapping("/users/{userId}/offers")
    public Offer addOffer(@PathVariable Long userId,
                            @Valid @RequestBody Offer offer) {
        return userRepository.findById(userId)
                .map(user -> {
                    offer.setUser(user);
                    offer.setMatches(matchService.findMatches(offer));
                    Set<Match> offerMatches = offer.getMatches();
                    for (Match match : offerMatches) {
                        Match newMatch = new Match();
                        newMatch.setRequest(null);
                        newMatch.setOffer(null);
                    }
                    return offerRepository.save(offer);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PutMapping("/users/{userId}/offers/{offerId}")
    public Offer updateOffer(@PathVariable Long userId,
                               @PathVariable Long offerId,
                               @Valid @RequestBody Offer offerRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return offerRepository.findById(offerId)
                .map(offer -> {
                    offer.setTitle(offerRequest.getTitle());
                    offer.setStatus(offerRequest.getStatus());
                    offer.setCategory(offerRequest.getCategory());
                    offer.setQuantity(offerRequest.getQuantity());
                    offer.setDistance(offerRequest.getDistance());
                    offer.setDescription(offerRequest.getDescription());
                    return offerRepository.save(offer);
                }).orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + offerId));
    }

    @DeleteMapping("/users/{userId}/offers/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long userId,
                                          @PathVariable Long offerId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return offerRepository.findById(offerId)
                .map(offer -> {
                    offerRepository.delete(offer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + offerId));

    }
}