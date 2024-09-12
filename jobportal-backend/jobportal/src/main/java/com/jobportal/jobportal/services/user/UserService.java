package com.jobportal.jobportal.services.user;

import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.entities.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void softDeleteUser(Long id);
    List<OfferResponseDTO> getFavouriteOffers(Long userId);
    void addFavouriteOffer(Long userId, Long offerId);
    void deleteFavouriteOffer(Long userId, Long offerId);
}
