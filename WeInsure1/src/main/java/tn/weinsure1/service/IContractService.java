package tn.weinsure1.service;

import java.util.List;

import tn.weinsure1.entities.Contract;

public interface IContractService {

	List<Contract> retrieveAllContracts(); 
	 Contract addContract(Contract c);
	 void deleteContract(String id);
	 Contract updateContract(Contract c);
	 Contract retrieveContract(String id);
	
}
