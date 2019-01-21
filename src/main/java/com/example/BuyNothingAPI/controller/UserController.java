package com.example.BuyNothingAPI.controller;



import com.example.BuyNothingAPI.exception.ResourceNotFoundException;
import com.example.BuyNothingAPI.model.User;
import com.example.BuyNothingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @ResponseBody

    public List<User> getUser(@RequestParam(required = false) String email) {



        if (email !=null) {
          return userRepository.findByEmail(email);

        } else {
           return userRepository.findAll();
        }

    }



    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    //if the user signs in with an existing user, new user doesnt get created, find instance of user based on email in database and return user information in the database

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId,
                                   @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setName(userRequest.getName());
                    user.setLocation(userRequest.getLocation());
                    user.setEmail(userRequest.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                  	userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }
}