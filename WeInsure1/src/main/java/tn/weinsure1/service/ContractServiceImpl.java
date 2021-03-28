package tn.weinsure1.service;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.User;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.repository.ContractRepository;
import tn.weinsure1.repository.UserRepository;


@Service
public class ContractServiceImpl implements IContractService {
	@Autowired
	ContractRepository ContractRepository;
	@Autowired
	UserRepository UserRepository;
	
	private static final Logger L= LogManager.getLogger(ContractServiceImpl.class);
	@Override
	public List<Contract> RetrieveAllContracts(){
		List<Contract> users = (List<Contract>) ContractRepository.findAll();
		for (Contract user : users){
			L.info("user +++ :" + user);
		}
		return users;
	}
	@Override
	public Contract AddContract(Contract c) {
		Contract cnt = ContractRepository.save(c);
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
	public List<Contract> findByYearGreater(String year) {
		List<Contract> cnt = ContractRepository.findByYearGreater(year);
		L.info("Contract +++ :" + cnt) ;
		return cnt;
	}
	 
}
