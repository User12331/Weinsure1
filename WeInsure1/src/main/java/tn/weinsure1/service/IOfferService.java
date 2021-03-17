package tn.weinsure1.service;

import java.util.List;

import tn.weinsure1.entities.Offer;

public interface IOfferService {

	List<Offer> retrieveAllOffers(); 
	 Offer addOffer(Offer O);
	 void deleteOffer(String id);
	 Offer updateOffer(Offer O);
	 Offer retrieveOffer(String id);
	
}
