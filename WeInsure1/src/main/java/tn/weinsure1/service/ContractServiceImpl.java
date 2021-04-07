package tn.weinsure1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.User;
import tn.weinsure1.repository.ContractRepository;
import tn.weinsure1.repository.TableMortalitéRepository;
import tn.weinsure1.repository.UserRepository;


@Service
public class ContractServiceImpl implements IContractService {
	@Autowired
	ContractRepository ContractRepository;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	TableMortalitéRepository tr ;

	private static final Logger L= LogManager.getLogger(ContractServiceImpl.class);
	@Override
	public List<Contract> RetrieveAllContracts(){
		List<Contract> cnts = (List<Contract>) ContractRepository.findAll();
		for (Contract cnt : cnts){
			L.info("user +++ :" + cnt);
		}
		return cnts;
	}
	@Override
	public Contract AddContract(Contract c) {
		Contract cnt = null;
		cnt = ContractRepository.save(c);
		return cnt;
	}
	@Override
	public void DeleteContract(String id) {
		ContractRepository.deleteById(Long.parseLong(id));
	}
	@Override
	public Contract UpdateContract(Contract c) {
		Contract ContractUpdated = ContractRepository.save(c);
		return ContractUpdated;
	}
	
	@Override
	public Contract RetrieveContract(String id) {
		L.info("in RetrieveContract id = " + id);
		Contract c = ContractRepository.findById(Long.parseLong(id)).get();
		L.info("Contract returned = : " + c);
		return c;	
	}
	
	@Override
	public List<Contract> findByDurationGreater(int year) {
		List<Contract> cnt = ContractRepository.findByDurationGreater(year);
		L.info("Contract +++ :" + cnt) ;
		return cnt;
	}
	 
	
	@Override
	public float CapitalVieUnique (float C, int ageClient, double taux, int duree ){
		float prime =0;
		float lxn = tr.findBySurvivantsLx(ageClient+duree);
		L.info("lxn=" + lxn);
		float lx = tr.findBySurvivantsLx(ageClient);
		double v = Math.pow( 1/ (1+(taux/100)) ,duree);
		prime = (float) (C*(lxn)/lx * v);
		L.info("PRIME =" + prime) ;
		return prime;
	}
	
	public void ContractToUser(long cntID, long userID){
	Contract cnt = ContractRepository.findById(cntID).get();
	User user = UserRepository.findById(userID).get();
	cnt.setUser(user);
	ContractRepository.save(cnt);
	}
	
	@Override
	public float TotalPricing() {
		float total =0;
		total = ContractRepository.TotalPricing();
		return total;
	}
	
}
