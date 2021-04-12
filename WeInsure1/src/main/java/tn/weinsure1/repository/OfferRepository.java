package tn.weinsure1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long > {

	@Query(value = "select a.id_user from contract a inner join contract b where a.id_user = "
			+ "b.id_user and a.type = 'Vie' and b.type = 'Décès' and a.expiration_date >"
			+ " now() and b.expiration_date > now()", nativeQuery = true)
	public List<Long> Users_pack1();
	
	@Query(value = "select a.id_user from contract a inner join contract b where a.id_user = "
			+ "b.id_user and a.type = 'Décès' and b.type = 'Personne' and a.expiration_date >"
			+ " now() and b.expiration_date > now()", nativeQuery = true)
	public List<Long> Users_pack2();
	
	@Query(value = "select a.id_user from contract a inner join contract b where a.id_user = "
			+ "b.id_user and a.type = 'Vie' and b.type = 'Dommages' and a.expiration_date >"
			+ " now() and b.expiration_date > now()", nativeQuery = true)
	public List<Long> Users_pack3();
	
	@Query("select count(*) from Contract c"
			+ " group by c.user.id"
			+ " order by 1 desc")
	public List<Long> Top_num_Contracts();
	
	@Query("select c.user.id from Contract c"
			+ " group by c.user.id"
			+" HAVING count(*) = ?1 ")
	public List<Long> Top_Users(int num);
	
	@Query("select distinct c.user.id from Contract c "
			+ "where c.Creation_date = "
			+ "(select min(b.Creation_date) "
			+ "from Contract b)")
	public List<Long> Old_User();
	
	
}
