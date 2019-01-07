package com.example.BuyNothingAPI.controller;


import com.example.BuyNothingAPI.exception.ResourceNotFoundException;
import com.example.BuyNothingAPI.model.Exchange;
import com.example.BuyNothingAPI.repository.OfferRepository;
import com.example.BuyNothingAPI.repository.RequestRepository;
import com.example.BuyNothingAPI.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ExchangeController {

//    @Autowired
//    private OfferRepository offerRepository;
//
//    @Autowired
//    private RequestRepository requestRepository;
//    
//    @Autowired
//    private ExchangeRepository exchangeRepository;
//
//
//
//    @GetMapping("/offers/{offerId}/exchanges")
//    public List<Exchange> getExchangesByOfferId(@PathVariable Long offerId) {
//        return exchangeRepository.findByOfferId(offerId);
//    }
//
//    @GetMapping("/requests/{requestId}/exchanges")
//    public List<Exchange> getExchangesByRequestId(@PathVariable Long requestId) {
//        return exchangeRepository.findByRequestId(requestId);
//    }
//    
//    @PostMapping("/exchanges")
//    public Exchange addExchangeForOffer(@PathVariable Long offerId,
//                            @Valid @RequestBody Exchange exchange) {
//        return offerRepository.findById(offerId)
//                .map(offer -> {
//                    exchange.setOffer(offer);
//                    return exchangeRepository.save(exchange);
//                }).orElseThrow(() -> new ResourceNotFoundException("Offer not found with id " + offerId));
//    }
//
//    @PutMapping("/offers/{offerId}/exchanges/{exchangeId}")
//    public Exchange updateExchangeFromOffer(@PathVariable Long offerId,
//                               @PathVariable Long exchangeId,
//                               @Valid @RequestBody Exchange exchangeRequest) {
//        if(!exchangeRepository.existsById(offerId)) {
//            throw new ResourceNotFoundException("Offer not found with id " + offerId);
//        }
//
//        return exchangeRepository.findById(exchangeId)
//                .map(exchange -> {
//                    exchange.setStatus(exchangeRequest.getStatus());
//                    exchange.setDistance(exchangeRequest.getDistance());
//                    return exchangeRepository.save(exchange);
//                }).orElseThrow(() -> new ResourceNotFoundException("Exchange not found with id " + exchangeId));
//    }
//
//    @DeleteMapping("/offers/{offerId}/exchanges/{exchangeId}")
//    public ResponseEntity<?> deleteExchangeFromOffer(@PathVariable Long offerId,
//                                          @PathVariable Long exchangeId) {
//        if(!offerRepository.existsById(offerId)) {
//            throw new ResourceNotFoundException("User not found with id " + offerId);
//        }
//
//        return exchangeRepository.findById(exchangeId)
//                .map(exchange -> {
//                    exchangeRepository.delete(exchange);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("Exchange not found with id " + exchangeId));
//
//    }
//    
//   
//
//    @PostMapping("/requests/{requestId}/exchanges")
//    public Exchange addExchangeFromRequest(@PathVariable Long requestId,
//                            @Valid @RequestBody Exchange exchange) {
//        return requestRepository.findById(requestId)
//                .map(request -> {
//                    exchange.setRequest(request);
//                    return exchangeRepository.save(exchange);
//                }).orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + requestId));
//    }
//
//    @PutMapping("/requests/{requestId}/exchanges/{exchangeId}")
//    public Exchange updateExchangeFromRequest(@PathVariable Long requestId,
//                               @PathVariable Long exchangeId,
//                               @Valid @RequestBody Exchange exchangeRequest) {
//        if(!exchangeRepository.existsById(requestId)) {
//            throw new ResourceNotFoundException("Request not found with id " + requestId);
//        }
//
//        return exchangeRepository.findById(exchangeId)
//                .map(exchange -> {
//                    exchange.setStatus(exchangeRequest.getStatus());
//                    exchange.setDistance(exchangeRequest.getDistance());
//                    return exchangeRepository.save(exchange);
//                }).orElseThrow(() -> new ResourceNotFoundException("Exchange not found with id " + exchangeId));
//    }
//
//    @DeleteMapping("/requests/{requestId}/exchanges/{exchangeId}")
//    public ResponseEntity<?> deleteExchange(@PathVariable Long requestId,
//                                          @PathVariable Long exchangeId) {
//        if(!requestRepository.existsById(requestId)) {
//            throw new ResourceNotFoundException("User not found with id " + requestId);
//        }
//
//        return exchangeRepository.findById(exchangeId)
//                .map(exchange -> {
//                    exchangeRepository.delete(exchange);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("Exchange not found with id " + exchangeId));
//
//    }
}