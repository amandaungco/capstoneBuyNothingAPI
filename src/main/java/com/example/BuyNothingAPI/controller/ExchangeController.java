package com.example.BuyNothingAPI.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.example.BuyNothingAPI.model.Exchange;
import com.example.BuyNothingAPI.model.Offer;
import com.example.BuyNothingAPI.model.Request;
import com.example.BuyNothingAPI.repository.ExchangeRepository;
import com.example.BuyNothingAPI.repository.OfferRepository;
import com.example.BuyNothingAPI.repository.RequestRepository;
import com.example.BuyNothingAPI.view.ExchangeAPI;

@RestController
public class ExchangeController {


	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private ExchangeRepository exchangeRepository;

	@GetMapping("/offers/{offerId}/exchanges")
	public List<Exchange> getexchangesByOfferId(@PathVariable Long offerId) {
		return exchangeRepository.findByOfferId(offerId);
	}
	@GetMapping("/requests/{requestId}/exchanges")
	public List<Exchange> getexchangesByRequestId(@PathVariable Long requestId) {
		return exchangeRepository.findByRequestId(requestId);
	}

	@GetMapping("/exchanges")
	public Page<Exchange> getExchanges(Pageable pageable) {
		Page <Exchange> exchanges = exchangeRepository.findAll(pageable);
		for (Exchange exchange: exchanges) {
			exchange.prepareForJSON(exchange);
		}
		return exchanges;
	
	}

	@PostMapping("/exchanges")
	public Exchange createExchange(@RequestBody ExchangeAPI exchangeApi) throws Exception {
		Optional<Offer> newOffer = offerRepository.findById(exchangeApi.getOffer_id());
		Optional<Request> newRequest = requestRepository.findById(exchangeApi.getRequest_id());
		if(newOffer.isPresent()&&newRequest.isPresent()) {
			Exchange newExchange = new Exchange();
			newExchange.setOffer(newOffer.get());
			newExchange.setRequest(newRequest.get());
			newExchange.setDistance(exchangeApi.getDistance());
			newExchange.setStatus(exchangeApi.getStatus());
			exchangeRepository.save(newExchange);
			newExchange.getOffer().setExchanges(null);
			newExchange.getRequest().setExchanges(null);
			newExchange.getRequest().setMatches(null);
			newExchange.getOffer().setMatches(null);
			

			return newExchange;
			}		else 		{
			throw new Exception("Uh-oh!");
			}
	}

	@PutMapping("/exchanges/{exchangeId}")
	public Exchange updateExchange(@PathVariable Long exchangeId, @Valid @RequestBody Exchange exchangeRequest) {
		return exchangeRepository.findById(exchangeId).map(exchange -> {
			exchange.setDistance(exchangeRequest.getDistance());
			return exchangeRepository.save(exchange);
		}).orElseThrow(() -> new ResourceNotFoundException("Exchange not found with id " + exchangeId));
	}

	@DeleteMapping("/exchanges/{exchangeId}")
	public ResponseEntity<?> deleteExchange(@PathVariable Long exchangeId) {
		return exchangeRepository.findById(exchangeId).map(exchange -> {
			exchangeRepository.delete(exchange);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Exchange not found with id " + exchangeId));
	}
}