package com.jobportal.jobportal.services.user;

import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.entities.UserFavouriteOffer;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.exceptions.offer.OfferDoesNotExistsException;
import com.jobportal.jobportal.exceptions.user.FavouriteOfferAlreadyExistsException;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.repositories.OfferRepository;
import com.jobportal.jobportal.repositories.UserFavouriteOfferRepository;
import com.jobportal.jobportal.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserFavouriteOfferRepository userFavouriteOfferRepository;

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    public UserServiceImpl(UserRepository userRepository, UserFavouriteOfferRepository userFavouriteOfferRepository, OfferRepository offerRepository, OfferMapper offerMapper) {
        this.userRepository = userRepository;
        this.userFavouriteOfferRepository = userFavouriteOfferRepository;
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException("User with id: " + id + " has not been found"));
    }

    @Override
    public void softDeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistException("User with id: " + id + " has not been found"));

        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<OfferResponseDTO> getFavouriteOffers(Long userId) {
        userRepository
                .findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("User with id: " + userId + " has not been found"));

        List<Long> allOfferIdsByUserId = userFavouriteOfferRepository.findAllOfferIdsByUserId(userId);

        return offerRepository
                .findAllById(allOfferIdsByUserId)
                .stream()
                .map(offerMapper::toOfferResponseFromOffer)
                .toList();
    }

    @Override
    @Transactional
    public void addFavouriteOffer(Long userId, Long offerId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("User with id: " + userId + " has not been found"));

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer with id: " + offerId + " has not been found"));

        UserFavouriteOffer userFavouriteOffer = userFavouriteOfferRepository.findByUserAndOffer(user, offer);
        if (userFavouriteOffer != null){
            throw new FavouriteOfferAlreadyExistsException("Favourite Offer already exists");
        }

        UserFavouriteOffer favouriteOffer = new UserFavouriteOffer();
        favouriteOffer.setUser(user);
        favouriteOffer.setOffer(offer);

        userFavouriteOfferRepository.save(favouriteOffer);
    }

    @Override
    @Transactional
    public void deleteFavouriteOffer(Long userId, Long offerId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("User with id: " + userId + " has not been found"));

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer with id: " + offerId + " has not been found"));

        UserFavouriteOffer userFavouriteOffer = userFavouriteOfferRepository.findByUserAndOffer(user, offer);
        if (userFavouriteOffer == null){
            throw new FavouriteOfferAlreadyExistsException("Favourite Offer does not exists");
        }

        userFavouriteOfferRepository.delete(userFavouriteOffer);
    }
}
