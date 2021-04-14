package tn.weinsure1.service;

import java.util.List;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;

public interface IOfferService {

	List<Offer> retrieveAllOffers(); 
	 Offer addOffer(Offer O);
	 //void deleteOffer(String id);
	 Offer updateOffer(Offer O);
	 Offer retrieveOffer(String id);
	void affecterUserOffer(long UserId,long OfferId);
	Long Old_User1(); 
	Long test1();
	Long topnum();
	List<Long> pack_3();
	List<Long> pack_2();
	List<Long> pack_1();
	List<Long> Top_Users(Long topnum);
	//User FiedeliteP(float prix, Long idu);
	//void FiedeliteP();
	void affectation(Long idu, Long ido);
	void Fidele(float somme, Long idu);
	void remise1(float somme, Long idu);
	Long basprix1(Long idu);
	Long basprixp1(Long idu);
	Long basprixp2(Long idu);
	Long basprixp3(Long idu);
	List<Long> inDB(Long idu, Long ido);
	String typeoffer(Long ido);
	float prixoffer(Long ido);
	void desaffectation(Long idu, Long ido);
	void affectationauto(Offer O);
	String simulateur(Long d, int m);
	String cptendettement(double s, Long ppm);
	void deleteOffer(Long ido);
	
}