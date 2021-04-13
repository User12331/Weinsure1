package tn.weinsure1.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.weinsure1.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

	@Query("SELECT c from Contract c WHERE c.Duration > :Duration ORDER By c.Duration asc ")
	List<Contract> findByDurationGreater(@Param("Duration") int Duration);
	
	@Query("Select "
			+ "DISTINCT SUM(cnt.Price) from Contract cnt "
			+ "where cnt.user IS NOT NULL")
	public float TotalPricing();
	
	@Query("Select c from Contract c where c.user.id=:id")
	List<Contract> RetrieveContractsByUserId(@Param("id")long id);
	
	@Modifying
    @Transactional
    @Query("UPDATE Contract c SET c.Price=:price where c.idcontract=:cntid")
    public void MAJContractPrice(@Param("price")float price, @Param("cntid")long cntid);
	
	@Modifying
    @Transactional
    @Query("UPDATE Contract c SET c.Duration=:duration where c.idcontract=:cntid")
    public void MAJContractDuration(@Param("duration")int duration, @Param("cntid")long cntid);
	
	@Modifying
    @Transactional
    @Query("DELETE from Contract c WHERE c.user.id=:id")
    public void DeleteContractsByUserId(@Param("id")long id);
	
	@Query("Select count(*) from Contract")
	public int CountContracts();
	
}
