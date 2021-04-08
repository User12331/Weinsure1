package tn.weinsure1.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.weinsure1.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

	// Find by year greater than X sorted asc
	@Query("SELECT c from Contract c WHERE c.Duration > :Duration ORDER By c.Duration asc ")
	List<Contract> findByDurationGreater(@Param("Duration") int Duration);
	
	@Query("Select "
			+ "DISTINCT SUM(cnt.Price) from Contract cnt "
			+ "where cnt.user IS NOT NULL")
	public float TotalPricing();
	
	@Query("Select c from Contract c where c.user.id=:id")
	List<Contract> RetrieveContractsByUserId(@Param("id")long id);
	
}
