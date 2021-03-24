package tn.weinsure1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;

public interface sinisterRepository  extends CrudRepository<sinister, Long> {
	@Query("SELECT s From sinister s WHERE s.status =:status")
	List<sinister> findSinisterByStatus(@Param("status")  sinisterstatus sinstatus );
	
	@Query("SELECT s From sinister s WHERE s.description like %:description%")
	List<sinister> findSinisterByStatus(@Param("description")  String description );

}
