package tn.weinsure1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.weinsure1.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {
/*
	@Query("select c from contract c where cast (c.Date_Creation as string) < %:date%")
    List<Contract> findByYearGreater(@Param("date") String date);

	*/
}
