package tn.weinsure1.repository;

import org.springframework.data.repository.CrudRepository;
import tn.weinsure1.entities.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long > {

}
