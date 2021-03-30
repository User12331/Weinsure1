package tn.weinsure1.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.weinsure1.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract,Long> {

}
