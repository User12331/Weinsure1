package tn.weinsure1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.CountContract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;
import tn.weinsure1.repository.OfferRepository;
import tn.weinsure1.repository.UserRepository;


@Service
public class OfferServiceImpl implements IOfferService {

	@Autowired     
	OfferRepository OfferRepository ;
	UserRepository userRepository;
	private static final Logger L = LogManager.getLogger(OfferServiceImpl.class);

	@Override
	public Offer addOffer (Offer O) {
		Offer OfferSaved = null;
		OfferSaved = OfferRepository.save(O);
		
		return OfferSaved;
	
	}
	@Override
	public void addOffer1 (Offer O) {
		Offer OfferSaved = null;
		OfferSaved = OfferRepository.save(O);
	}
	
	@Override
	public void deleteOffer(Long id) {
		OfferRepository.deleteById(id);
		
	}
	@Override
	public Offer updateOffer(Offer O) {
		Offer OfferAdded = OfferRepository.save(O);
		return OfferAdded;
	}
	
	@Override
	public void updateOffer1(Offer O) {
		Offer OfferAdded = OfferRepository.save(O);
	}
	
	@Override
	public Offer retrieveOffer(Long id) {
		Offer O = OfferRepository.findById(id).get();
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
	
	@Override
	public void affecterUserOffer(long UserId, long OfferId) {
	Offer offer = OfferRepository.findById(OfferId).get();
	User user = userRepository.findById(UserId).get();
	
	if(offer.getUsers() == null){
		List<User> users = new ArrayList<>();
		users.add(user);
		offer.setUsers(users);
		}
	else{
		offer.getUsers().add(user);
	}
	}
	
	@Override
	public Long Old_User1() {
		List<Long> l = (List<Long>) OfferRepository.Old_User(); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);
		}
					
		return l.get(0);	
	}
	
	@Override
	public Long topnum() {
		List<Long> l = (List<Long>) OfferRepository.Top_num_Contracts(); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);

		}
					
		return l.get(0);	
	}
	@Override
	public Long test1() {
		List<Long> l = (List<Long>) OfferRepository.Top_num_Contracts(); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);

		}
					
		return l.get(0);	
	}
	
	@Override
	public List<Long> pack_3() {
		List<Long> l = (List<Long>) OfferRepository.Users_pack3(); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);
		}
					
		return l;	
	}
	
	@Override
	public List<Long> pack_2() {
		List<Long> l = (List<Long>) OfferRepository.Users_pack2(); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);
		}
					
		return l;	
	}
	
	@Override
	public List<Long> pack_1() {
		List<Long> l = (List<Long>) OfferRepository.Users_pack1(); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);
		}
					
		return l;	
	}
	
	@Override
	public List<User> pack1() {
		return OfferRepository.pack1(); 				
	}
	
	@Override
	public List<Long> Top_Users(Long num) {
		List<Long> l = (List<Long>) OfferRepository.Top_Users(num); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);

		}
					
		return l;	
	}
	
	@Override
	public int Top1(Long id) {
//		List<Integer> l = OfferRepository.Top1();
		return OfferRepository.Top1(id);

	}
	
	@Override
	public List<User> Top2() {
		return OfferRepository.Top2();

	}
	
	/*@Override
	public void FiedeliteP() {
	OfferRepository.affect();
	}*/
		
	@Override
	public void Fidele(float somme, Long idu) {
	OfferRepository.Fidelite(somme, idu);
	}
	
	@Override
	public List<User> UserFidele() {
	return OfferRepository.FideleUser();
	}

	@Override
	public List<Contract> old() {
	return OfferRepository.old();
	}
	
	@Override
	public Date old(Long id) {
	return OfferRepository.old(id);
	}
	
	@Override
	public List<User> old1() {
	return OfferRepository.old1();
	}
	
	@Override
	public void remise1(float somme, Long idc) {
	OfferRepository.remise(somme, idc);
	}

	@Override
	public Long basprix1(Long idu) {
		List<Long> l = (List<Long>) OfferRepository.basprix(idu);
	 return l.get(0);
	}
	
	@Override
	public Long basprixp1(Long idu) {
		List<Long> l = (List<Long>) OfferRepository.basprixp1(idu);
	 return l.get(0);
	}
	
	@Override
	public Long basprixp2(Long idu) {
		List<Long> l = (List<Long>) OfferRepository.basprixp2(idu);
	 return l.get(0);
	}
	
	@Override
	public Long basprixp3(Long idu) {
		List<Long> l = (List<Long>) OfferRepository.basprixp3(idu);
	 return l.get(0);
	}
	
	@Override
	public List<Long> inDB(Long idu, Long ido) {
		List<Long> l = OfferRepository.offus(idu,ido); 
		for(Long ls : l)
		{
			L.info("Offer +++ :" + ls);

		}
					
		return l;	
	}
	
	@Override
	public String typeoffer(Long ido) {
		return OfferRepository.typeoff(ido); 
	}
	
	@Override
	public float prixoffer(Long ido) {
		return (float)OfferRepository.prixoff(ido); 
	}
	
	@Override
	public void affectation(Long idu, Long ido) {
		//if(inDB(idu, ido) == null)
		{
			OfferRepository.affect(idu, ido);
			String t = typeoffer(ido);
			if(t.equals("ancien") || t.equals("fidele") || t.equals("best")){
				remise1(1- prixoffer(ido) , basprix1(idu));
			}
			if (t.equals("pack1"))
			{
				remise1(1- prixoffer(ido) , basprixp1(idu));
			}
			if (t.equals("pack2"))
			{
				remise1(1- prixoffer(ido) , basprixp2(idu));
			}
			if (t.equals("pack3"))
			{
				remise1(1- prixoffer(ido) , basprixp3(idu));
			}
			}
		}
		
	
	@Override
	public void desaffectation(Long idu, Long ido) {
		OfferRepository.desaffect(idu, ido);
		String t = typeoffer(ido);
		if(t.equals("ancien") || t.equals("fidele") || t.equals("best")){
			remise1(1/(1- prixoffer(ido)) , basprix1(idu));
		}
		if (t.equals("pack1"))
		{
			remise1(1/(1- prixoffer(ido)) , basprixp1(idu));
		}
		if (t.equals("pack2"))
		{
			remise1(1/(1- prixoffer(ido)) , basprixp2(idu));
		}
		if (t.equals("pack3"))
		{
			remise1(1/(1- prixoffer(ido)) , basprixp3(idu));
		}
		}
	
	@Override
	public void affectationauto(Offer O) {
			Offer Off = addOffer(O);
			Long id = O.getIdOffer();
			String Type = O.getType();
			
			if(Type.equals("ancien"))
			{
				List<Long> L = OfferRepository.Old_User();
				for(Long ls : L)
				{
					OfferRepository.affect(ls, id);
					remise1(1- prixoffer(id) , basprix1(ls));
				}
			}
			if(Type.equals("fidele"))
			{
				List<Long> L = OfferRepository.Fidele_User();
				for(Long ls : L)
				{
					OfferRepository.affect(ls, id);
					remise1(1- prixoffer(id) , basprix1(ls));
				}
			}
			if(Type.equals("best"))
			{
				List<Long> L = OfferRepository.Top_Users(topnum());
				for(Long ls : L)
				{
					OfferRepository.affect(ls, id);
					remise1(1- prixoffer(id) , basprix1(ls));
				}
			}
			if(Type.equals("pack1"))
			{
				List<Long> L = OfferRepository.Users_pack1();	
				for(Long ls : L)
				{
					OfferRepository.affect(ls, id);
					remise1(1- prixoffer(id) , basprixp1(ls));
				}
			}
			if(Type.equals("pack2"))
			{
				List<Long> L = OfferRepository.Users_pack2();	
				for(Long ls : L)
				{
					OfferRepository.affect(ls, id);
					remise1(1- prixoffer(id) , basprixp2(ls));
				}
			}
			if(Type.equals("pack3"))
			{
				List<Long> L = OfferRepository.Users_pack3();	
				for(Long ls : L)
				{
					OfferRepository.affect(ls, id);
					remise1(1- prixoffer(id) , basprixp3(ls));
				}
			}
	}
	@Override
	public String simulateur(Long d, int m) {
		double p = d*1.01;
		double ppm = p/m;
		return "à payer: "+p+"DT\n à payer par mois :"+ppm+" DT";
	}
	
	@Override
	public String cptendettement(double s, Long ppm) {
		double cpt = s*0.4 - ppm;
		if(cpt>0)
		{return "capacité d'endettement : "+cpt+" DT";}
		else 
		{return "capacité d'endettement dépassée";}
	}	
	
	@Override
	public String annuiteconstante(double montant, float I, int duree) {
		double AC = montant * I / (1- Math.pow(1+I,-duree));
		return "valeur de l'annuité constante : "+AC+" DT";
	}
	
}