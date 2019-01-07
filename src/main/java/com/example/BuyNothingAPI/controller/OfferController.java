package com.example.BuyNothingAPI.controller;


import com.example.BuyNothingAPI.exception.ResourceNotFoundException;
import com.example.BuyNothingAPI.model.Offer;
import com.example.BuyNothingAPI.repository.OfferRepository;
import com.example.BuyNothingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/offers")
    public List<Offer> getOffersByUserId(@PathVariable Long userId) {
        return offerRepository.findByUserId(userId);
    }

    @PostMapping("/users/{userId}/offers")
    public Offer addOffer(@PathVariable Long userId,
                            @Valid @RequestBody Offer offer) {
        return userRepository.findById(userId)
                .map(user -> {
                    offer.setUser(user);
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