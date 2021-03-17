package tn.weinsure1.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Offer;
import tn.weinsure1.repository.OfferRepository;


@Service
public class OfferServiceImpl implements IOfferService {

	@Autowired     
	OfferRepository OfferRepository ;
	private static final Logger L = LogManager.getLogger(OfferServiceImpl.class);

	@Override
	public Offer addOffer (Offer O) {
		Offer OfferSaved = null;
		OfferSaved = OfferRepository.save(O);
		
		return OfferSaved;
	
	}
	@Override
	public void deleteOffer(String id) {
		OfferRepository.deleteById(Long.parseLong(id));
		
	}
	@Override
	public Offer updateOffer(Offer O) {
		Offer OfferAdded = OfferRepository.save(O);
		return OfferAdded;
	}
	
	@Override
	public Offer retrieveOffer(String id) {
		L.info("in retrieveOffer id = " + id);
		Offer O = OfferRepository.findById(Long.parseLong(id)).get();
		L.info("Offer returned = : " + O);
		return O;
			}
	
	
	@Override
	public List<Offer> retrieveAllOffers() {
		List<Offer> Offers = (List<Offer>) OfferRepository.findAll(); 
		for(Offer ofr : Offers)
		{
			L.info("Offer +++ :" + ofr);
		}
					
		return Offers;
	}	
	
}
