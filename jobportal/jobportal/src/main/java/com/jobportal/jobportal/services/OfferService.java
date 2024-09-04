package com.jobportal.jobportal.services;

import com.jobportal.jobportal.dtos.offer.OfferResponseModel;

import java.util.List;

public interface OfferService {

    List<OfferResponseModel> getAllOffers();

    OfferResponseModel getOffer(long offerId);
}
