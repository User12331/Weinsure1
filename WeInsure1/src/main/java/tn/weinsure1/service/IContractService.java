package tn.weinsure1.service;

import java.util.List;

import tn.weinsure1.entities.Contract;

public interface IContractService {

	List<Contract> RetrieveAllContracts(); 
	Contract AddContract(Contract c);
	void DeleteContract(String id);
	Contract UpdateContract(Contract c);
	Contract RetrieveContract(String id);
	List<Contract> findByDurationGreater(int year);
	float CapitalVieUnique(float C, long id, int ageClient, int k, double taux, int n);

}