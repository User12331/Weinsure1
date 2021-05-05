package tn.weinsure1.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.CountContract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long > {

//Get USER  	
	@Query("select a.user.id from Contract a "
			+ "join Contract b "
			+ "on a.user.id = b.user.id"
			+ " where a.Type like 'Vie' and b.Type like 'Décès'"
			+ "and a.Expiration_date > now() "
			+ "and b.Expiration_date > now()")
	public List<Long> Users_pack1();
	
	@Query("select distinct a.user from Contract a "
			+ "join Contract b "
			+ "on a.user.id = b.user.id"
			+ " where a.Type like 'Vie' and b.Type like 'Décès'"
			+ "and a.Expiration_date > now() "
			+ "and b.Expiration_date > now()")
	public List<User> pack1();
	
	@Query("select a.user.id from Contract a "
			+ "join Contract b "
			+ "on a.user.id = b.user.id"
			+ " where a.Type like 'Personne' and b.Type like 'Décès'"
			+ "and a.Expiration_date > now() "
			+ "and b.Expiration_date > now()")
	public List<Long> Users_pack2();
	
	@Query(value = "select * from offer a where a.idoffer "
			+ "not in (select o.idoffer from offer_user"
			+ " u inner join offer o on o.idoffer = u.id_offer "
			+ "where id_user = ?1) and a.expiration_date > now()", nativeQuery = true)
	public List<Offer> offernot(Long id);
	
	@Query(value = "select * from offer a where a.idoffer in "
			+ "(select o.idoffer from offer_user u inner join offer "
			+ "o on o.idoffer = u.id_offer where id_user = ?1) "
			+ "and a.expiration_date > now()", nativeQuery = true)
	public List<Offer> offerin(Long id);
	
	@Query(value = "select o.id_user from offer_user u inner join "
			+ "user o on o.id_user = u.id_user where u.id_offer = ?1)", nativeQuery = true)
	public List<Long> userin(Long id);
	
	@Query("select distinct a.user from Contract a "
			+ "join Contract b "
			+ "on a.user.id = b.user.id"
			+ " where a.Type like 'Personne' and b.Type like 'Décès'"
			+ "and a.Expiration_date > now() "
			+ "and b.Expiration_date > now()")
	public List<User> pack2();
	
	@Query("select a.user.id from Contract a "
			+ "join Contract b "
			+ "on a.user.id = b.user.id"
			+ " where a.Type like 'Vie' and b.Type like 'Dommages'"
			+ "and a.Expiration_date > now() "
			+ "and b.Expiration_date > now()")
	public List<Long> Users_pack3();
	
	@Query("select distinct a.user from Contract a "
			+ "join Contract b "
			+ "on a.user.id = b.user.id"
			+ " where a.Type like 'Vie' and b.Type like 'Dommages'"
			+ "and a.Expiration_date > now() "
			+ "and b.Expiration_date > now()")
	public List<User> pack3();
	
	@Query("select a from User a")
	public List<User> allusers();
	
	@Query("select count(*) from Contract c"
			+ " group by c.user.id"
			+ " order by 1 desc")
	public List<Long> Top_num_Contracts();
	
	@Query("select c.user.id from Contract c"
			+ " group by c.user.id"
			+" HAVING count(*) = ?1 ")
	public List<Long> Top_Users(Long num);
	
	@Query("select count(c)"
			+ " from Contract c"
			+ " where c.user.id = ?1 order by 1 desc")
	public int Top1(Long id);
	
	@Query("select c.user"
			+ " from Contract c"
			+ " group by c.user.id order by count(c) desc")
	public List<User> Top2();
	
	@Query("select c.user.username"
			+ " from Contract c"
			+ " group by c.user.id order by count(c) desc")
	public List<String> Top3();
	
	@Query("select count(c)"
			+ " from Contract c"
			+ " group by c.user.id order by count(c) desc")
	public int[] Top4();
	
	@Query("select u.username"
			+ " from User u"
			+ " order by u.PointsF desc")
	public List<String> fidele1();
	
	@Query("select u.PointsF"
			+ " from User u"
			+ " order by u.PointsF desc")
	public float[] fidele2();
	
	@Query("select distinct c.user.id from Contract c "
			+ "where c.Creation_date = "
			+ "(select min(b.Creation_date) "
			+ "from Contract b)")
	public List<Long> Old_User();
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.PointsF = u.PointsF + ?1 WHERE u.id = ?2")
	public void Fidelite(float somme, Long idu);	
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO offer_user (id_user, id_offer)"
			+ " VALUES (?1 , ?2)", nativeQuery = true)
	public void affect(Long idu, Long ido);
	
	@Modifying
	@Transactional
	@Query("UPDATE Contract c SET c.Price = c.Price * ?1 "
			+ "where c.idcontract = ?2 ")
	public void remise(float prix, Long idc);
	
	@Query("SELECT c.idcontract from Contract c where c.user.id = ?1"
			+ " and c.Expiration_date > now() order by c.Price")
	public List<Long> basprix(Long idu);	
	
	@Query("SELECT c.idcontract from Contract c where c.user.id = ?1"
			+ " and c.Expiration_date > now() "
			+ "and( c.Type like 'Vie' or c.Type like 'Décès' ) order by c.Price")
	public List<Long> basprixp1(Long idu);	
	
	@Query("SELECT c.idcontract from Contract c where c.user.id = ?1"
			+ " and c.Expiration_date > now() "
			+ "and( c.Type like 'Personne' or c.Type like 'Décès' ) order by c.Price")
	public List<Long> basprixp2(Long idu);	
	
	@Query("SELECT c.idcontract from Contract c where c.user.id = ?1"
			+ " and c.Expiration_date > now() "
			+ "and( c.Type like 'Vie' or c.Type like 'Dommages' ) order by c.Price")
	public List<Long> basprixp3(Long idu);	
	
	@Query(value = "Select id_user from offer_user where id_user = ?1 and id_offer = ?2", nativeQuery = true)
	public List<Long> offus(Long idu, Long ido);
	
	
	@Query("Select o.Type from Offer o where o.IdOffer = ?1")
	public String typeoff(Long ido);
	
	@Query("Select o.Prix from Offer o where o.IdOffer = ?1")
	public float prixoff(Long ido);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE from offer_user where id_user = ?1 and id_offer= ?2", nativeQuery = true)
	public void desaffect(Long idu, Long ido);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE from offer_user where id_offer = ?1", nativeQuery = true)
	public void safeclean(Long id);
	
	@Query("select u.id from User u where u.PointsF = (Select max(u.PointsF) from User u)")
	public List<Long> Fidele_User();
	
	@Query("select u from User u order by u.PointsF desc")
	public List<User> FideleUser();
	
	@Query("select c from Contract c order by c.Creation_date")
	public List<Contract> old();
	
	@Query("select min(c.Creation_date) from Contract c where c.user.id = ?1")
	public Date old(Long id);
	
	@Query("select DISTINCT c.user from Contract c order by c.Creation_date")
	public List<User> old1();
}


