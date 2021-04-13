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
	void ContractToUser(long cntID, long userID);
	float TotalPricing();
	List<Contract> RetrieveContractsByUserId(long id);
	float CapitalVieUnique(float C, long userid, int duree);
	float PrimeVieUnique(float prime, long userid, int duree);
	double RITP(double prime,long userid);
	double RITC(double d,long userid);
	void MAJContractPrice(float price,long cntid);
	float CapitalMixte(double prime,long userid,int n);
	void DeleteContractsByUserId(long id);
	int CountCntracts();
	void MAJContractDuration(int duration,long cntid);
}