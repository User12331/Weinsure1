package tn.weinsure1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.weinsure1.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

	@Query(value="SELECT count(*) from Contract")
	List<Contract> RetrieveAllContracts(); 
	
	@Query(value="SELECT count(*) from Contract where id=IdContract")
	 Contract RetrieveContract(String id);
}
