package tn.weinsure1.service;

import java.util.Date;
import java.util.List;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.CountContract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;

public interface IOfferService {

	List<Offer> retrieveAllOffers(); 
	 Offer addOffer(Offer O);
	 void addOffer1(Offer O);
	 //void deleteOffer(String id);
	 Offer updateOffer(Offer O);
	 //Offer retrieveOffer(String id);
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
	void deleteOffer(Long id);
	String simulateur(Long d, int m);
	String cptendettement(double s, Long ppm);
	String annuiteconstante(double montant, float I, int duree);
	Offer retrieveOffer(Long id);
	void updateOffer1(Offer O);
	List<User> pack1();
	int Top1(Long id);
	List<User> Top2();
	List<User> UserFidele();
	List<Contract> old();
	Date old(Long id);
	List<User> old1();
	List<User> pack2();
	List<User> pack3();
	List<User> allusers();
	List<Offer> offernot(Long id);
	List<Offer> offerin(Long id);
	List<String> Top3();
	int[] Top4();
	List<String> fidele1();
	float[] fidele2();
	String simulateur2(Long d, int m);
	
}