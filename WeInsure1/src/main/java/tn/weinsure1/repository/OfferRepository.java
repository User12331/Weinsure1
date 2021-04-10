package tn.weinsure1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.weinsure1.entities.Offer;
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long > {

	@Query(value = "select a.id_user from contract a inner join contract b where a.id_user = "
			+ "b.id_user and a.type = 'Vie' and b.type = 'Décès' and a.expiration_date >"
			+ " now() and b.expiration_date > now()", nativeQuery = true)
	public List<Integer> Users_pack1();
	
	@Query(value = "select a.id_user from contract a inner join contract b where a.id_user = "
			+ "b.id_user and a.type = 'Décès' and b.type = 'Personne' and a.expiration_date >"
			+ " now() and b.expiration_date > now()", nativeQuery = true)
	public List<Integer> Users_pack2();
	
	@Query(value = "select a.id_user from contract a inner join contract b where a.id_user = "
			+ "b.id_user and a.type = 'Vie' and b.type = 'Dommages' and a.expiration_date >"
			+ " now() and b.expiration_date > now()", nativeQuery = true)
	public List<Integer> Users_pack3();
	
	@Query(value = "select count(*) from contract group by id_user order by 1 desc LIMIT 1", nativeQuery = true)
	public int Top_num_Contracts();
	
	@Query(value = "select id_user from contract group by id_user HAVING count(*) = ?1 ", nativeQuery = true)
	public List<Integer> Top_Users(int num);
	
	@Query(value = "select distinct id_user from contract where creation_date ="
			+ " (select min(creation_date) from contract) ", nativeQuery = true)
	public List<Integer> Old_User();
	
	
}
