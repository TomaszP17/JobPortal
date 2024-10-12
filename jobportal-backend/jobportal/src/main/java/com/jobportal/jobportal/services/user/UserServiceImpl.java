package com.jobportal.jobportal.services.user;

import com.jobportal.jobportal.dtos.candidate.CandidateResponseDTO;
import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.dtos.user.CurrentUserDTO;
import com.jobportal.jobportal.entities.UserFavouriteOffer;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.Authority;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.entities.user.UserAuthority;
import com.jobportal.jobportal.exceptions.authority.AuthorityDoesNotExistException;
import com.jobportal.jobportal.exceptions.offer.OfferDoesNotExistsException;
import com.jobportal.jobportal.exceptions.user.FavouriteOfferAlreadyExistsException;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.repositories.*;
import com.jobportal.jobportal.services.candidate.CandidateService;
import com.jobportal.jobportal.services.company.CompanyService;
import com.jobportal.jobportal.services.offer.OfferService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserFavouriteOfferRepository userFavouriteOfferRepository;
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final CandidateService candidateService;
    private final CompanyService companyService;

    public UserServiceImpl(UserRepository userRepository, UserFavouriteOfferRepository userFavouriteOfferRepository, OfferRepository offerRepository, OfferMapper offerMapper, AuthorityRepository authorityRepository, UserAuthorityRepository userAuthorityRepository, CandidateService candidateService, CompanyService companyService) {
        this.userRepository = userRepository;
        this.userFavouriteOfferRepository = userFavouriteOfferRepository;
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.authorityRepository = authorityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.candidateService = candidateService;
        this.companyService = companyService;
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

    //current db structure enables user to have multiple roles, however in the future we would like to restrict the user to have a single role with enums
    @Override
    public CurrentUserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        String role = authentication.getAuthorities().stream().toList().getFirst().getAuthority();

        CurrentUserDTO userDTO;

        if(role.equals("ROLE_CANDIDATE")){
           userDTO = candidateService.getCandidateByEmail(email);
        } else if (role.equals("ROLE_COMPANY")){
           userDTO = companyService.getCompanyByEmail(email);
        } else {
            throw new AuthorityDoesNotExistException("Provided authority does not exist");
        }

       return userDTO;
    };
}
