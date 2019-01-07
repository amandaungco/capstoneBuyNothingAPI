package com.example.BuyNothingAPI.controller;


import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BuyNothingAPI.exception.ResourceNotFoundException;
import com.example.BuyNothingAPI.model.Match;
import com.example.BuyNothingAPI.repository.MatchRepository;
import com.example.BuyNothingAPI.model.Offer;
import com.example.BuyNothingAPI.model.Request;
import com.example.BuyNothingAPI.repository.MatchRepository;
import com.example.BuyNothingAPI.repository.OfferRepository;
import com.example.BuyNothingAPI.repository.RequestRepository;

@RestController
public class MatchController {


	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private MatchRepository matchRepository;

	@GetMapping("/offers/{offerId}/matches")
	public List<Match> getMatchesByOfferId(@PathVariable Long offerId) {
		return matchRepository.findByOfferId(offerId);
	}
	@GetMapping("/requests/{requestId}/matches")
	public List<Match> getMatchesByRequestId(@PathVariable Long requestId) {
		return matchRepository.findByRequestId(requestId);
	}

	@GetMapping("/matches")
	public Page<Match> getMatches(Pageable pageable) {
		return matchRepository.findAll(pageable);
	}

	@PostMapping("/matches")
	public Match createMatch(@RequestParam Long offer_id, @RequestParam Long request_id) throws Exception {
		Optional<Offer> newOffer = offerRepository.findById(offer_id);
		Optional<Request> newRequest = requestRepository.findById(request_id);
		if(newOffer.isPresent()&&newRequest.isPresent()) {
			Match newMatch = new Match();
			newMatch.setOffer(newOffer.get());
			newMatch.setRequest(newRequest.get());
			matchRepository.save(newMatch);
			return newMatch;
			}
		else 		{
			throw new Exception("Uh-oh!");
			}
	}

	@PutMapping("/matches/{matchId}")
	public Match updateMatch(@PathVariable Long matchId, @Valid @RequestBody Match matchRequest) {
		return matchRepository.findById(matchId).map(match -> {
			match.setDistance(matchRequest.getDistance());
			return matchRepository.save(match);
		}).orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + matchId));
	}

	@DeleteMapping("/matches/{matchId}")
	public ResponseEntity<?> deleteMatch(@PathVariable Long matchId) {
		return matchRepository.findById(matchId).map(match -> {
			matchRepository.delete(match);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Match not found with id " + matchId));
	}
}