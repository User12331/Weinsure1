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
	//float CapitalVieUnique(float C, int ageClient, double taux, int duree);
	void ContractToUser(long cntID, long userID);
	float TotalPricing();
	List<Contract> RetrieveContractsByUserId(long id);
	float CapitalVieUnique(float C, long userid, int duree);
	float PrimeVieUnique(float prime, long userid, int duree);
	float RITP(float prime,long userid, int duree);
}