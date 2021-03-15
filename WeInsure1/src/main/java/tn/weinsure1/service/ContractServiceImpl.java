package tn.weinsure1.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.repository.ContractRepository;


@Service
public class ContractServiceImpl implements IContractService {

	@Autowired     
	ContractRepository ContractRepository ;
	private static final Logger L = LogManager.getLogger(ContractServiceImpl.class);

	@Override
	public Contract addContract (Contract C) {
		Contract ContractSaved = null;
		ContractSaved = ContractRepository.save(C);
		
		return ContractSaved;
	
	}
	@Override
	public void deleteContract(String id) {
		ContractRepository.deleteById(Long.parseLong(id));
		
	}
	@Override
	public Contract updateContract(Contract C) {
		Contract ContractAdded = ContractRepository.save(C);
		return ContractAdded;
	}
	
	@Override
	public Contract retrieveContract(String id) {
		L.info("in retrieveContract id = " + id);
		Contract C = ContractRepository.findById(Long.parseLong(id)).get();
		L.info("Contract returned = : " + C);
		return C;
			}
	
	
	@Override
	public List<Contract> retrieveAllContracts() {
		List<Contract> Contracts = (List<Contract>) ContractRepository.findAll(); 
		for(Contract cnt : Contracts)
		{
			L.info("Contract +++ :" + cnt);
		}
					
		return Contracts;
	}	
	
}
