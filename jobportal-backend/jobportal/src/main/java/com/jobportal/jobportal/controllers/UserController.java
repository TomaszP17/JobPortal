package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.candidate.CurrentUserCandidateDTO;
import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.dtos.user.UserDTO;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.enums.UserType;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.services.user.UserService;
import com.jobportal.jobportal.services.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/favourite-offers")
    public ResponseEntity<List<OfferResponseDTO>> getFavouriteOffers(@PathVariable long userId){
        return new ResponseEntity(userService.getFavouriteOffers(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/favourite-offers")
    public ResponseEntity<String> addFavouriteOffer(@PathVariable long userId, @RequestParam long offerId){
        userService.addFavouriteOffer(userId, offerId);
        return new ResponseEntity<>("Created Favourite Offer Successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/favourite-offers/{offerId}")
    public ResponseEntity<String> deleteFavouriteOffer(@PathVariable long userId, @PathVariable long offerId){
        userService.deleteFavouriteOffer(userId, offerId);
        return new ResponseEntity<>("Deleted Favourite Offer Successfully!", HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(){
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }
}
