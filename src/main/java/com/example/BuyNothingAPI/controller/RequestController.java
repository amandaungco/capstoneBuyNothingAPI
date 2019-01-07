package com.example.BuyNothingAPI.controller;


import com.example.BuyNothingAPI.exception.ResourceNotFoundException;
import com.example.BuyNothingAPI.model.Request;
import com.example.BuyNothingAPI.repository.RequestRepository;
import com.example.BuyNothingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/requests")
    public List<Request> getRequestsByUserId(@PathVariable Long userId) {
        return requestRepository.findByUserId(userId);
    }

    @PostMapping("/users/{userId}/requests")
    public Request addRequest(@PathVariable Long userId,
                            @Valid @RequestBody Request request) {
        return userRepository.findById(userId)
                .map(user -> {
                    request.setUser(user);
                    return requestRepository.save(request);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PutMapping("/users/{userId}/requests/{requestId}")
    public Request updateRequest(@PathVariable Long userId,
                               @PathVariable Long requestId,
                               @Valid @RequestBody Request requestRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return requestRepository.findById(requestId)
                .map(request -> {
                    request.setTitle(requestRequest.getTitle());
                    request.setStatus(requestRequest.getStatus());
                    request.setCategory(requestRequest.getCategory());
                    request.setQuantity(requestRequest.getQuantity());
                    request.setDistance(requestRequest.getDistance());
                    return requestRepository.save(request);
                }).orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + requestId));
    }

    @DeleteMapping("/users/{userId}/requests/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long userId,
                                          @PathVariable Long requestId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return requestRepository.findById(requestId)
                .map(request -> {
                    requestRepository.delete(request);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + requestId));

    }
}